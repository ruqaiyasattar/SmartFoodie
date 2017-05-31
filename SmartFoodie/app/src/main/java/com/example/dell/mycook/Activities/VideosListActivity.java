package com.example.dell.mycook.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.mycook.R;
import com.example.dell.mycook.models.StaticDataHolder;
import com.example.dell.mycook.models.YoutubeVideoListItem;
import com.google.android.youtube.player.YouTubeStandalonePlayer;

import java.util.ArrayList;
import java.util.Arrays;

public class VideosListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videos_list_activity);

        RecyclerView rcv_videos_list = (RecyclerView) findViewById(R.id.rcv_videos_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        int videosCategoryId = getIntent().getIntExtra("categoryId", 0);

        RecyclerView.Adapter adapter = new VideosListAdapter(StaticDataHolder.videos.get(videosCategoryId));
        rcv_videos_list.setLayoutManager(layoutManager);
        rcv_videos_list.setAdapter(adapter);
    }

    public class VideosListAdapter extends RecyclerView.Adapter<VideosListAdapter.ViewHolder> {
        ArrayList<YoutubeVideoListItem> videos = new ArrayList<>();

        VideosListAdapter(YoutubeVideoListItem[] videos) {
            this.videos.addAll(Arrays.asList(videos));
        }

        @Override
        public VideosListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item_layout, parent, false));
        }

        @Override
        public void onBindViewHolder(VideosListAdapter.ViewHolder holder, int position) {
            final YoutubeVideoListItem video = videos.get(position);
            Glide.with(holder.itemView.getContext()).load(video.getImageUrl()).skipMemoryCache(true).into(holder.video_thumbnail_image);
            holder.video_title.setText(video.getTitle());

            holder.video_thumbnail_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(YouTubeStandalonePlayer.createVideoIntent(VideosListActivity.this,
                            "AIzaSyB4mROcMUIfeuknEnZNQF0KHtj_J_-DShE", video.getVideoId(), 0, true, true));
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
