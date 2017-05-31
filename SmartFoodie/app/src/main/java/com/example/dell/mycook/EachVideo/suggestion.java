package com.example.dell.mycook.EachVideo;

/**
 * Created by dell on 3/25/2017.
 */

public class suggestion {

    private String name;
    private String msg;

    public suggestion() {
    }

    public suggestion(String name, String msg) {
        this.name = name;
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public String getMsg() {
        return msg;
    }
}
