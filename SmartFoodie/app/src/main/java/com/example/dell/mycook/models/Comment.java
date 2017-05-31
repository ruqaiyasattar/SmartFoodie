package com.example.dell.mycook.models;

/**
 * Created by DELL on 5/10/2017.
 */

public class Comment {
    String comment;
    String uid;
    String name;
    long time;
    String pic;

    public Comment() {
    }

    public Comment(String comment, String uid, String name, long time, String pic) {
        this.comment = comment;
        this.uid = uid;
        this.name = name;
        this.time = time;
        this.pic = pic;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
