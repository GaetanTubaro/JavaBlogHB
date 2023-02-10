package com.hb.blog.models;

import java.time.LocalDateTime;
import java.util.Date;

public class Post {
    private Integer id;
    private String title;
    private String content;
    private String user;
    private Date date;


    public Post() {
    }

    public Post(Integer id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Post(Integer id, String title, String content, String user, Date date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
