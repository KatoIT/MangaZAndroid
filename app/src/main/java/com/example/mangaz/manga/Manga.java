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
    private String listCategory;
    private Integer countChapter;


    public Manga(String mangaName, String author, Boolean isManga, String introduce, Boolean accept, String urlImage, Bitmap avatar) {
        MangaName = mangaName;
        Author = author;
        IsManga = isManga;
        Introduce = introduce;
        Accept = accept;
        UrlImage = urlImage;
        Avatar = avatar;
    }

    public Manga(String mangaName, String urlImage) {
        MangaName = mangaName;
        UrlImage = urlImage;
    }

    public Manga(String mangaName, String urlImage, String listCategory, Integer countChapter) {
        MangaName = mangaName;
        UrlImage = urlImage;
        this.listCategory = listCategory;
        this.countChapter = countChapter;
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

    public String getListCategory() {
        return listCategory;
    }

    public void setListCategory(String listCategory) {
        this.listCategory = listCategory;
    }

    public Integer getCountChapter() {
        return countChapter;
    }

    public void setCountChapter(Integer countChapter) {
        this.countChapter = countChapter;
    }
}
