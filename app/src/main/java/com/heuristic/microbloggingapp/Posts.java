package com.heuristic.microbloggingapp;

public class Posts {
    private String user_name, user_id, post_id, likes, post_description, timestamp;

    public Posts(String user_name, String user_id, String post_id, String likes, String post_description, String timestamp) {
        this.user_name = user_name;
        this.user_id = user_id;
        this.post_id = post_id;
        this.likes = likes;
        this.post_description = post_description;
        this.timestamp = timestamp;
    }

    public Posts() {
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getPost_description() {
        return post_description;
    }

    public void setPost_description(String post_description) {
        this.post_description = post_description;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}