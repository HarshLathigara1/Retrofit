package com.example.firebasecrudmvp;

import com.google.gson.annotations.SerializedName;

public class Comment {

    private int postid;
    private int id;
    private String name;
    private  String email;

    @SerializedName("body")
    private String text;


    public int getPostid() {
        return postid;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getText() {
        return text;
    }
}
