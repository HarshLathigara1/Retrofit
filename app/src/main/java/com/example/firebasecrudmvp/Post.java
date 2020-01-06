package com.example.firebasecrudmvp;

import com.google.gson.annotations.SerializedName;

public class Post {
        // get  = @GET

    private int userId;
    private int id;
    private String title;


    @SerializedName("body") // this will be name in your json file
    private String text;

    public Post(int userId, String title, String text) {
        this.userId = userId;
        this.title = title;
        this.text = text;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }



}
