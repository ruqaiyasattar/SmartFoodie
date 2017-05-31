package com.example.dell.mycook.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.mycook.Core.BasicFeatures;
import com.example.dell.mycook.Core.FirebaseRecyclerAdapter;
import com.example.dell.mycook.R;
import com.example.dell.mycook.models.Comment;
import com.example.dell.mycook.models.Favorite;
import com.example.dell.mycook.models.youtube.ChannelItem;
import com.example.dell.mycook.models.youtube.ChannelItemSingleVideo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import cz.msebera.android.httpclient.Header;

public class VIdeoVIewActivity extends YouTubeBaseActivity implements
        YouTubePlayer.OnInitializedListener {
    static private final String DEVELOPER_KEY = BasicFeatures.YOUTUBE_API_KEY;
    private String VIDEO = "";
    private String Uid;
    private String Uname;
    String favoritesByStr = "";
    ArrayList<String> favoritesByArr = new ArrayList<>();
    boolean isFavorite = false;
    String videoDataApi = "https://www.googleapis.com/youtube/v3/videos?key=AIzaSyB4mROcMUIfeuknEnZNQF0KHtj_J_-DShE&part=snippet&id=";
    ChannelItemSingleVideo videoObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        VIDEO = getIntent().getStringExtra("videoId");
        youTubeView.initialize(DEVELOPER_KEY, this);
        favoritesByArr = new ArrayList<>();

        final View ratingBox = findViewById(R.id.ratingBox);

        final RatingBar ratingBar = (RatingBar) findViewById(R.id.videoRatingInput);
        Drawable progress = ratingBar.getProgressDrawable();
        ratingBar.setIsIndicator(false);
        DrawableCompat.setTint(progress, getResources().getColor(R.color.colorPrimary));
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                //Toast.makeText(getApplicationContext(),"Your Selected Ratings  : " + String.valueOf(rating),Toast.LENGTH_LONG).show();
            }
        });

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setCancelable(false);
        pd.setMessage("Loading Video Data...");
        (new AsyncHttpClient()).get(videoDataApi+VIDEO,new JsonHttpResponseHandler(){
            @Override
            public void onStart() {
                pd.show();
            }

            @Override
            public void onFinish() {
                pd.dismiss();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    videoObject = (new Gson()).fromJson(response.getJSONArray("items").toString().trim(),ChannelItemSingleVideo[].class)[0];
                } catch (Exception e) {
                    Log.e("Parsing Error",e.getMessage());
                    e.printStackTrace();
                }
            }
        });
        /*(new AsyncHttpClient()).get(videoDataApi+VIDEO,new AsyncHttpResponseHandler(){
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
                Log.e("Video Response",res+"");
                try {
                    JSONObject response = new JSONObject(res);
                    videoObject = (new Gson()).fromJson(response.getJSONArray("items").toString(),ChannelItem[].class)[0];
                    Log.e("Video Object Title",videoObject.getSnippet().getTitle()+"");
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
        });*/

        final ProgressDialog addToFavoritespd = new ProgressDialog(this);
        addToFavoritespd.setCancelable(false);
        addToFavoritespd.setMessage("Getting Favorites...");
        addToFavoritespd.show();
        FirebaseDatabase.getInstance().getReference()
                .child("videos").child(VIDEO).child("favoritesBy").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                addToFavoritespd.dismiss();
                favoritesByStr = dataSnapshot.getValue(String.class);
                if (favoritesByStr != null && favoritesByStr.length() > 0) {
                    favoritesByArr.addAll(Arrays.asList(favoritesByStr.split(",")));
                    if (favoritesByArr.contains(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                        isFavorite = true;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Uname = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

        FirebaseDatabase.getInstance().getReference()
                .child("videos").child(VIDEO).child("ratings")
                .child(Uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Float existing = dataSnapshot.getValue(Float.class);
                if (existing != null) {
                    ratingBox.setVisibility(View.GONE);
                } else {
                    ratingBox.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Button btn = (Button) findViewById(R.id.ratingSaveBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference()
                        .child("videos").child(VIDEO).child("ratings")
                        .child(Uid)
                        .setValue(ratingBar.getRating())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    ratingBox.setVisibility(View.GONE);
                                    Toast.makeText(VIdeoVIewActivity.this, "Thanks for your review!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Log.e("Failed To Update Rating", task.getException().getMessage());
                                    Toast.makeText(VIdeoVIewActivity.this, "There was an error!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        final ProgressBar commentsLoading = (ProgressBar) findViewById(R.id.commentsLoading);
        final View commentEtBox = findViewById(R.id.commentEtBox);
        final EditText commentEt = (EditText) findViewById(R.id.commentEt);
        Button commentPostBtn = (Button) findViewById(R.id.commentPostBtn);
        commentPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String commentStr = commentEt.getText().toString();
                if (commentStr.isEmpty()) {
                    commentEt.setError("PLease enter valid comment");
                    return;
                }
                commentEt.setError(null);
                Comment comment = new Comment(commentStr, Uid, Uname, System.currentTimeMillis(), FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl().toString());
                FirebaseDatabase.getInstance().getReference()
                        .child("videos").child(VIDEO).child("comments")
                        .push()
                        .setValue(comment);
                commentEtBox.setVisibility(View.GONE);
            }
        });

        RecyclerView allComments = (RecyclerView) findViewById(R.id.allComments);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        ArrayList comnts = new ArrayList<Comment>();
        ArrayList<String> keys = new ArrayList<String>();
        DatabaseReference commentsRef = FirebaseDatabase.getInstance().getReference()
                .child("videos").child(VIDEO).child("comments");
        //commentsLoading
        commentsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                commentsLoading.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        MyAdapter adapter = new MyAdapter(commentsRef.orderByChild("time"), comnts, keys);
        allComments.setLayoutManager(layoutManager);
        allComments.setAdapter(adapter);


        View shareBtn = findViewById(R.id.shareBtn);
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/plain");
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

                //share.putExtra(Intent.EXTRA_SUBJECT, getIntent().getStringExtra("videoTitle"));
                share.putExtra(Intent.EXTRA_TEXT, "http://smartfoodie.com/videos/?videoId=" + VIDEO);

                startActivity(Intent.createChooser(share, "Share Recipe via..."));
            }
        });





        findViewById(R.id.addToFavorites).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFavorite) {
                    addToFavoritespd.setMessage("Adding To Favorites...");
                    addToFavoritespd.show();
                    String vt = "";
                    String vi = "";

                    if(videoObject != null){
                        vt = videoObject.getSnippet().getTitle();
                        vi = videoObject.getSnippet().getThumbnails().getDefaultThumbnail().getUrl();
                    }

                    FirebaseDatabase.getInstance().getReference()
                            .child("videos").child(VIDEO).child("favoritesBy").setValue(((favoritesByStr != null && favoritesByStr.length() > 0) ? "," : "")+FirebaseAuth.getInstance().getCurrentUser().getUid());
                    FirebaseDatabase.getInstance().getReference()
                            .child("favorites").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .push().setValue(new Favorite(VIDEO,vt,vi)).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            addToFavoritespd.dismiss();
                            if (task.isSuccessful()) {
                                isFavorite = true;
                                Toast.makeText(VIdeoVIewActivity.this, "Added to Favorites!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(VIdeoVIewActivity.this, "Error while adding to favorites, try again later!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(VIdeoVIewActivity.this, "Already in favorites!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult error) {
        Toast.makeText(this, "Oh no! " + error.toString(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        player.loadVideo(VIDEO);
    }


    class MyAdapter extends FirebaseRecyclerAdapter<MyAdapter.ViewHolder, Comment> {

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView name;
            TextView comment;
            TextView time;

            public ViewHolder(View view) {
                super(view);
                name = (TextView) view.findViewById(R.id.name);
                comment = (TextView) view.findViewById(R.id.comment);
                time = (TextView) view.findViewById(R.id.time);
            }
        }

        public MyAdapter(Query query, @Nullable ArrayList<Comment> items, @Nullable ArrayList<String> keys) {
            super(query, items, keys);
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.comment_layout, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
            Comment item = getItem(position);
            holder.name.setText(item.getName());
            Calendar cal = BasicFeatures.millsToCalender(item.getTime());
            String timeStr = cal.get(Calendar.DAY_OF_MONTH) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.YEAR) + " " + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE);
            holder.time.setText(timeStr);
            holder.comment.setText(item.getComment());
        }

        @Override
        protected void itemAdded(Comment item, String key, int position) {
            Log.d("MyAdapter", "Added a new item to the adapter.");
        }

        @Override
        protected void itemChanged(Comment oldItem, Comment newItem, String key, int position) {
            Log.d("MyAdapter", "Changed an item.");
        }

        @Override
        protected void itemRemoved(Comment item, String key, int position) {
            Log.d("MyAdapter", "Removed an item from the adapter.");
        }

        @Override
        protected void itemMoved(Comment item, String key, int oldPosition, int newPosition) {
            Log.d("MyAdapter", "Moved an item.");
        }
    }
}
