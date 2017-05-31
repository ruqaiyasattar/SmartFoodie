package com.example.dell.mycook;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.dell.mycook.Activities.VIdeoVIewActivity;

public class ShareHandlerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_handler);
        Uri data = this.getIntent().getData();
        if (data != null) {
            if (data.getQueryParameter("videoId") != null) {
                String videoId = data.getQueryParameter("videoId");
                Intent i = new Intent(this, VIdeoVIewActivity.class);
                i.putExtra("videoId",videoId);
                startActivity(i);
                finish();
                // do some stuff
            }
        }
    }
}
