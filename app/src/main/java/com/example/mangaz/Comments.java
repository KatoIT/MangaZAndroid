package com.example.mangaz;

import java.time.LocalDateTime;

public class Comments {
    private int Avatar;
    private String UserName;
    private String CommentContent;
    private String Date;

    public Comments(int avatar, String userName, String commentContent, String date) {
        Avatar = avatar;
        UserName = userName;
        CommentContent = commentContent;
        Date = date;
    }

    public Comments(int avatar, String userName, String commentContent) {
        Avatar = avatar;
        UserName = userName;
        CommentContent = commentContent;
    }

    public int getAvatar() {
        return Avatar;
    }

    public void setAvatar(int avatar) {
        Avatar = avatar;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getCommentContent() {
        return CommentContent;
    }

    public void setCommentContent(String commentContent) {
        CommentContent = commentContent;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
