package com.example.mangaz.Model;

public class UserManga {
    private Boolean IsFollow;
    private Integer Vote;
    private String User_UserName;
    private String Manga_MangaName;
    private User mUser;
    private Manga mManga;

    public UserManga(Boolean isFollow, Integer vote, String user_UserName, String manga_MangaName, User mUser, Manga mManga) {
        IsFollow = isFollow;
        Vote = vote;
        User_UserName = user_UserName;
        Manga_MangaName = manga_MangaName;
        this.mUser = mUser;
        this.mManga = mManga;
    }

    public UserManga(Boolean isFollow, Integer vote, String user_UserName, String manga_MangaName) {
        IsFollow = isFollow;
        Vote = vote;
        User_UserName = user_UserName;
        Manga_MangaName = manga_MangaName;
    }

    public Boolean getFollow() {
        return IsFollow;
    }

    public void setFollow(Boolean follow) {
        IsFollow = follow;
    }

    public Integer getVote() {
        return Vote;
    }

    public void setVote(Integer vote) {
        Vote = vote;
    }

    public String getUser_UserName() {
        return User_UserName;
    }

    public void setUser_UserName(String user_UserName) {
        User_UserName = user_UserName;
    }

    public String getManga_MangaName() {
        return Manga_MangaName;
    }

    public void setManga_MangaName(String manga_MangaName) {
        Manga_MangaName = manga_MangaName;
    }

    public User getmUser() {
        return mUser;
    }

    public void setmUser(User mUser) {
        this.mUser = mUser;
    }

    public Manga getmManga() {
        return mManga;
    }

    public void setmManga(Manga mManga) {
        this.mManga = mManga;
    }
}
