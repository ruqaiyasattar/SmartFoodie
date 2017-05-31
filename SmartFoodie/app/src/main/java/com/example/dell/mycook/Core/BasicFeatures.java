package com.example.dell.mycook.Core;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;

/**
 * Created by dell on 3/24/2017.
 */

public class BasicFeatures {
    public static final String YOUTUBE_API_KEY = "AIzaSyB4mROcMUIfeuknEnZNQF0KHtj_J_-DShE";

    public static void uploadToYoutube(String title, String description, File video, ResponseHandlerInterface responseHandlerInterface){
        RequestParams params = new RequestParams();
        params.put("title",title);
        params.put("description",description);
        try {
            params.put("video",video);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        (new AsyncHttpClient()).post("http://cyberavanza.com/ruq/youtubeAPI/uploader.php", params, responseHandlerInterface);
    }

    public static String getRealPathFromURI(Uri contentUri, Activity context) {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = context.managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    public static Calendar millsToCalender(long mills){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mills);
        return calendar;
    }
}
