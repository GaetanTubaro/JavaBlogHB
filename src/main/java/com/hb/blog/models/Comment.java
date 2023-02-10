package com.hb.blog.models;

import java.util.Date;

public class Comment {
    private int id;
    private int postId;
    private String content;
    private String userCreator;
    private Date date;

    public Comment() {
    }

    public Comment(int id,int postId, String content, String userCreator, Date date) {
        this.id = id;
        this.postId =postId;
        this.content = content;
        this.userCreator = userCreator;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserCreator() {
        return userCreator;
    }

    public void setUserCreator(String userCreator) {
        this.userCreator = userCreator;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
