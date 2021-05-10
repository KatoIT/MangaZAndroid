package com.example.mangaz.Model;

import android.graphics.Bitmap;
import android.text.TextUtils;

public class Users {


    private String UserName;
    private String PassWord;
    private String Nickname;
    private Integer Age;
    private Integer Level;
    private String UrlImage;
    private Bitmap Avatar;
    private Boolean IsAdmin;

    public Users(String userName, String passWord) {
        UserName = userName;
        PassWord = passWord;
    }

    public Users(String userName, String passWord, String nickname, Integer age) {
        UserName = userName;
        PassWord = passWord;
        Nickname = nickname;
        Age = age;
    }

    public Users(String userName, String passWord, String nickname, Integer age, Integer level, String urlImage, Bitmap avatar, Boolean isAdmin) {
        UserName = userName;
        PassWord = passWord;
        Nickname = nickname;
        Age = age;
        Level = level;
        UrlImage = urlImage;
        Avatar = avatar;
        IsAdmin = isAdmin;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        Age = age;
    }

    public Integer getLevel() {
        return Level;
    }

    public void setLevel(Integer level) {
        Level = level;
    }

    public String getUrlImage() {
        return UrlImage;
    }

    public void setUrlImage(String urlImage) {
        UrlImage = urlImage;
    }

    public Bitmap getAvatar() {
        return Avatar;
    }

    public void setAvatar(Bitmap avatar) {
        Avatar = avatar;
    }

    public Boolean getAdmin() {
        return IsAdmin;
    }

    public void setAdmin(Boolean admin) {
        IsAdmin = admin;
    }

    public Boolean ísValidUserName() {
        return !TextUtils.isEmpty(UserName) && UserName.length() >= 4;
    }

    public Boolean ísValidPassWord() {
        return !TextUtils.isEmpty(PassWord) && PassWord.length() >= 6;
    }

    public Boolean ísValidAge() {
        return Age > 0;
    }
}
