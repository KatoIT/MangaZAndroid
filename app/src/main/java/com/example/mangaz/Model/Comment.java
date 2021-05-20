package com.example.mangaz.Model;

public class Comment {
    private String IdComment;
    private String ContentComment;
    private String AnswerIdComment;
    private String DateComment;
    private String User_UserName;
    private String Chapter_IdChapter;
    private User mUser;
    private Chapter mChapter;

    public Comment(String idComment, String contentComment, String answerIdComment, String dateComment, String user_UserName, String chapter_IdChapter, User mUser, Chapter mChapter) {
        IdComment = idComment;
        ContentComment = contentComment;
        AnswerIdComment = answerIdComment;
        DateComment = dateComment;
        User_UserName = user_UserName;
        Chapter_IdChapter = chapter_IdChapter;
        this.mUser = mUser;
        this.mChapter = mChapter;
    }

    public Comment(String idComment, String contentComment, String answerIdComment, String dateComment, String user_UserName, String chapter_IdChapter, User mUser) {
        IdComment = idComment;
        ContentComment = contentComment;
        AnswerIdComment = answerIdComment;
        DateComment = dateComment;
        User_UserName = user_UserName;
        Chapter_IdChapter = chapter_IdChapter;
        this.mUser = mUser;
    }

    public Comment(String idComment, String contentComment, String answerIdComment, String dateComment, String user_UserName, String chapter_IdChapter) {
        IdComment = idComment;
        ContentComment = contentComment;
        AnswerIdComment = answerIdComment;
        DateComment = dateComment;
        User_UserName = user_UserName;
        Chapter_IdChapter = chapter_IdChapter;
    }


    public String getIdComment() {
        return IdComment;
    }

    public void setIdComment(String idComment) {
        IdComment = idComment;
    }

    public String getContentComment() {
        return ContentComment;
    }

    public void setContentComment(String contentComment) {
        ContentComment = contentComment;
    }

    public String getAnswerIdComment() {
        return AnswerIdComment;
    }

    public void setAnswerIdComment(String answerIdComment) {
        AnswerIdComment = answerIdComment;
    }

    public String getDateComment() {
        return DateComment;
    }

    public void setDateComment(String dateComment) {
        DateComment = dateComment;
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
