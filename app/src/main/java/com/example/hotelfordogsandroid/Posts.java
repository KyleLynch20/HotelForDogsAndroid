package com.example.hotelfordogsandroid;

public class Posts {
    final private int postType;
    final private Object postObject;

    public Posts (int postType, Object postObject) {
        this.postType = postType;
        this.postObject = postObject;
    }

    public int getPostType() {
        return postType;
    }

    public Object getPostObject() {
        return postObject;
    }
}
