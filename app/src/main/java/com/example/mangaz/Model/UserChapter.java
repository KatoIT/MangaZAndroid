package com.example.mangaz.Model;

public class UserChapter {
    private String TimeReading;
    private Boolean IsLike;
    private String User_UserName;
    private String Chapter_IdChapter;
    private User mUser;
    private Chapter mChapter;

    public UserChapter(String timeReading, Boolean isLike, String user_UserName, String chapter_IdChapter, User mUser, Chapter mChapter) {
        TimeReading = timeReading;
        IsLike = isLike;
        User_UserName = user_UserName;
        Chapter_IdChapter = chapter_IdChapter;
        this.mUser = mUser;
        this.mChapter = mChapter;
    }

    public UserChapter(String timeReading, Boolean isLike, String user_UserName, String chapter_IdChapter) {
        TimeReading = timeReading;
        IsLike = isLike;
        User_UserName = user_UserName;
        Chapter_IdChapter = chapter_IdChapter;
    }

    public String getTimeReading() {
        return TimeReading;
    }

    public void setTimeReading(String timeReading) {
        TimeReading = timeReading;
    }

    public Boolean getLike() {
        return IsLike;
    }

    public void setLike(Boolean like) {
        IsLike = like;
    }

    public String getUser_UserName() {
        return User_UserName;
    }

    public void setUser_UserName(String user_UserName) {
        User_UserName = user_UserName;
    }

    public String getChapter_IdChapter() {
        return Chapter_IdChapter;
    }

    public void setChapter_IdChapter(String chapter_IdChapter) {
        Chapter_IdChapter = chapter_IdChapter;
    }

    public User getmUser() {
        return mUser;
    }

    public void setmUser(User mUser) {
        this.mUser = mUser;
    }

    public Chapter getmChapter() {
        return mChapter;
    }

    public void setmChapter(Chapter mChapter) {
        this.mChapter = mChapter;
    }
}
