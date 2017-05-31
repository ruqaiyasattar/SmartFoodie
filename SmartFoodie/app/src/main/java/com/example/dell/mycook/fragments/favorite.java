package com.example.dell.mycook.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
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
import com.example.dell.mycook.R;
import com.example.dell.mycook.models.Favorite;
import com.example.dell.mycook.models.youtube.ChannelItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import cz.msebera.android.httpclient.Header;

/**
 * A simple {@link Fragment} subclass.
 */
public class favorite extends BaseFragment {



    RecyclerView mRecycler;
    private RecyclerView.LayoutManager mLayoutManager;
    public favorite() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setContentView(inflater.inflate(R.layout.fragment_smart_cook, container, false));

        initfeilds();

        return getContentView();
    }
    favorite.VideosListAdapter adapter;

    public void initfeilds() {
        mRecycler = (RecyclerView) findViewById(R.id.for_uploaded_video);
        mLayoutManager = new LinearLayoutManager(getActivity());
        final ArrayList<Favorite> videos = new ArrayList<>();
        adapter = new favorite.VideosListAdapter(videos);
        mRecycler.setLayoutManager(mLayoutManager);
        mRecycler.setAdapter(adapter);

        final ProgressDialog pd = new ProgressDialog(getContext());
        pd.setCancelable(false);
        pd.setMessage("Loading Favorite Videos...");
        pd.show();
        DatabaseReference videosRef =  FirebaseDatabase.getInstance().getReference().child("favorites").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        videosRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pd.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        videosRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Favorite favorite = dataSnapshot.getValue(Favorite.class);
                videos.add(favorite);
                adapter.notifyItemInserted(videos.size()-1);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    //this one
    public class VideosListAdapter extends RecyclerView.Adapter<favorite.VideosListAdapter.ViewHolder> {
        ArrayList<Favorite> videos = new ArrayList<>();

        VideosListAdapter(Favorite[] videos) {
            this.videos.addAll(Arrays.asList(videos));
        }
        VideosListAdapter(ArrayList<Favorite> videos) {
            this.videos = videos;
        }

        @Override
        public favorite.VideosListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new favorite.VideosListAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item_layout, parent, false));
        }

        @Override
        public void onBindViewHolder(favorite.VideosListAdapter.ViewHolder holder, int position) {
            final Favorite video = videos.get(position);
            Log.e("Item "+position+" Title",video.getTitle()+"");
            Log.e("Item "+position+" Image",video.getImage()+"");
            Glide.with(holder.itemView.getContext()).load(video.getImage()).skipMemoryCache(true).into(holder.video_thumbnail_image);
            holder.video_title.setText(video.getTitle());

            holder.video_thumbnail_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), VIdeoVIewActivity.class);
                    i.putExtra("videoId",video.getId());
                    i.putExtra("videoTitle",video.getTitle());
                    i.putExtra("videoImage",video.getImage());
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
