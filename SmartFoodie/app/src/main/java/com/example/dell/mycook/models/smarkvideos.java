package com.example.dell.mycook.models;

/**
 * Created by dell on 4/24/2017.
 */

public class smarkvideos {
    private String title;
    private String url;

    public smarkvideos(String title, String url) {
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
        return "UU0-vwPmp-nmu_Huza_nq0AA"+getVideoId()+"/0.jpg";
    }
}

