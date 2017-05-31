package com.example.dell.mycook.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.mycook.Activities.VIdeoVIewActivity;
import com.example.dell.mycook.Activities.VideosListActivity;
import com.example.dell.mycook.Core.BasicFeatures;
import com.example.dell.mycook.R;
import com.example.dell.mycook.models.YoutubeVideoListItem;
import com.example.dell.mycook.models.youtube.ChannelItem;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;

import cz.msebera.android.httpclient.Header;


/**
 * A simple {@link Fragment} subclass.
 */
public class smartCook extends BaseFragment {


    RecyclerView mRecycler;
    private RecyclerView.LayoutManager mLayoutManager;
    String allVideosUrl = "https://www.googleapis.com/youtube/v3/search?key=AIzaSyB4mROcMUIfeuknEnZNQF0KHtj_J_-DShE&channelId=UC3MLT1qVpci5v--q2hsNFAg&part=snippet,id&order=date&maxResults=20";
    public smartCook() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setContentView(inflater.inflate(R.layout.fragment_smart_cook, container, false));

        initfeilds();

        return getContentView();
    }
    VideosListAdapter adapter;

    public void initfeilds() {
        mRecycler = (RecyclerView) findViewById(R.id.for_uploaded_video);
        mLayoutManager = new LinearLayoutManager(getActivity());
        final ArrayList<ChannelItem> videos = new ArrayList<>();
        adapter = new VideosListAdapter(videos);
        mRecycler.setLayoutManager(mLayoutManager);
        mRecycler.setAdapter(adapter);

        final ProgressDialog pd = new ProgressDialog(getContext());
        pd.setCancelable(false);
        pd.setMessage("Loading Channel Videos...");
        if(getArguments() != null && getArguments().getString("searchQuery","").length() > 0){
            try {
                allVideosUrl += "&q="+ URLEncoder.encode(getArguments().getString("searchQuery"), "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Log.e("Url TO get",allVideosUrl);
        (new AsyncHttpClient()).get(allVideosUrl,new AsyncHttpResponseHandler(){
            @Override
            public void onStart() {
                pd.show();
            }

            @Override
            public void onFinish() {
                pd.dismiss();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String res = new String(responseBody);
                try {
                    JSONObject response = new JSONObject(res);
                    Log.e("Items",response.getJSONArray("items").toString());
                    ChannelItem[] channelItems = (new Gson()).fromJson(response.getJSONArray("items").toString(), ChannelItem[].class);
                    videos.addAll(Arrays.asList(channelItems));
                    Log.e("channels Items",channelItems.length+"");
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    Log.e("Parsing Error",e.getMessage());
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("onFailure", new String(responseBody) + "");
                Log.e("onFailure", "");
            }
        });

    }
//this one
    public class VideosListAdapter extends RecyclerView.Adapter<VideosListAdapter.ViewHolder> {
        ArrayList<ChannelItem> videos = new ArrayList<>();

        VideosListAdapter(ChannelItem[] videos) {
            this.videos.addAll(Arrays.asList(videos));
        }
        VideosListAdapter(ArrayList<ChannelItem> videos) {
            this.videos = videos;
        }

        @Override
        public VideosListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new VideosListAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item_layout, parent, false));
        }

        @Override
        public void onBindViewHolder(VideosListAdapter.ViewHolder holder, int position) {
            final ChannelItem video = videos.get(position);
            //Log.e("Item "+position+" Title",video.getSnippet().getTitle()+"");
            //Log.e("Item "+position+" Image",video.getSnippet().getThumbnails().getDefaultThumbnail().getUrl()+"");
            Glide.with(holder.itemView.getContext()).load(video.getSnippet().getThumbnails().getDefaultThumbnail().getUrl()).skipMemoryCache(true).into(holder.video_thumbnail_image);
            holder.video_title.setText(video.getSnippet().getTitle());

            holder.video_thumbnail_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), VIdeoVIewActivity.class);
                    i.putExtra("videoId",video.getId().getVideoId());
                    i.putExtra("videoTitle",video.getSnippet().getTitle());
                    i.putExtra("videoImage",video.getSnippet().getThumbnails().getDefaultThumbnail().getUrl());
                    startActivity(i);
                    /*startActivity(YouTubeStandalonePlayer.createVideoIntent(getActivity(),
                            BasicFeatures.YOUTUBE_API_KEY, video.getId().getVideoId(), 0, true, true));*/
                }
            });
        }
        @Override
        public int getItemCount() {
            return videos.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView video_thumbnail_image;
            TextView video_title;

            public ViewHolder(View itemView) {
                super(itemView);
                video_thumbnail_image = (ImageView) itemView.findViewById(R.id.video_thumbnail_image);
                video_title = (TextView) itemView.findViewById(R.id.video_title);
            }
        }
    }
}
