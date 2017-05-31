package com.example.dell.mycook.models;

/**
 * Created by dell on 3/6/2017.
 */
public class Item {

    private String name;
    private String pic;

    public Item(String name, String pic) {
        this.name = name;
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}