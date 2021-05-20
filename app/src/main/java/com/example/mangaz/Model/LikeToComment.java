package com.example.mangaz.Model;

public class LikeToComment {
    private Boolean IsLike;
    private String User_UserName;
    private String Comment_IdComment;
    private User mUser;
    private Comment mComment;

    public LikeToComment(Boolean isLike, String user_UserName, String comment_IdComment, User mUser, Comment mComment) {
        IsLike = isLike;
        User_UserName = user_UserName;
        Comment_IdComment = comment_IdComment;
        this.mUser = mUser;
        this.mComment = mComment;
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

    public String getComment_IdComment() {
        return Comment_IdComment;
    }

    public void setComment_IdComment(String comment_IdComment) {
        Comment_IdComment = comment_IdComment;
    }

    public User getmUser() {
        return mUser;
    }

    public void setmUser(User mUser) {
        this.mUser = mUser;
    }

    public Comment getmComment() {
        return mComment;
    }

    public void setmComment(Comment mComment) {
        this.mComment = mComment;
    }
}
