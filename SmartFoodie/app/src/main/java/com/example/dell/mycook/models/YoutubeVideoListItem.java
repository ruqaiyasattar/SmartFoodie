package com.example.dell.mycook.models;

/**
 * Created by dell on 3/16/2017.
 */

public class YoutubeVideoListItem {
    private String title;
    private String url;

    public YoutubeVideoListItem(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVideoId(){
        return (getUrl().split("v=")[1]);
    }

    public String getImageUrl(){
        return "http://img.youtube.com/vi/"+getVideoId()+"/0.jpg";
    }
}
