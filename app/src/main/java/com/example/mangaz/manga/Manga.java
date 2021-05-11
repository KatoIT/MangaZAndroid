/*
 * Coder: Nguyen Van An
 * Date: 11-5-2021
 * Content: Đối tượng Manga (Truyện)
 *
 *
 * */
package com.example.mangaz.manga;

import android.graphics.Bitmap;

public class Manga {
    private String MangaName;
    private String Author;
    private Boolean IsManga;
    private String Introduce;
    private Boolean Accept;
    private String UrlImage;
    private Bitmap Avatar;
    private Integer Type;

    public Manga(String mangaName, String author, Boolean isManga, String introduce, Boolean accept, String urlImage, Bitmap avatar) {
        MangaName = mangaName;
        Author = author;
        IsManga = isManga;
        Introduce = introduce;
        Accept = accept;
        UrlImage = urlImage;
        Avatar = avatar;
    }

    public Manga(String mangaName, Boolean accept, String urlImage) {
        MangaName = mangaName;
        Accept = accept;
        UrlImage = urlImage;
    }

    public Manga(String mangaName, Boolean accept, String urlImage, Integer type) {
        MangaName = mangaName;
        Accept = accept;
        UrlImage = urlImage;
        Type = type;
    }

    public String getMangaName() {
        return MangaName;
    }

    public void setMangaName(String mangaName) {
        MangaName = mangaName;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public Boolean getManga() {
        return IsManga;
    }

    public void setManga(Boolean manga) {
        IsManga = manga;
    }

    public String getIntroduce() {
        return Introduce;
    }

    public void setIntroduce(String introduce) {
        Introduce = introduce;
    }

    public Boolean getAccept() {
        return Accept;
    }

    public void setAccept(Boolean accept) {
        Accept = accept;
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
}
