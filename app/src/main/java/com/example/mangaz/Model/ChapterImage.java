package com.example.mangaz.Model;

import android.graphics.Bitmap;

public class ChapterImage {
    private Integer ImageNumber;
    private Bitmap Image;
    private String UrlImage;
    private String Chapter_IdChapter;
    private Chapter mChapter;

    public ChapterImage(Integer imageNumber, Bitmap image, String urlImage, String chapter_IdChapter, Chapter mChapter) {
        ImageNumber = imageNumber;
        Image = image;
        UrlImage = urlImage;
        Chapter_IdChapter = chapter_IdChapter;
        this.mChapter = mChapter;
    }

    public ChapterImage(Integer imageNumber, Bitmap image, String urlImage, String chapter_IdChapter) {
        ImageNumber = imageNumber;
        Image = image;
        UrlImage = urlImage;
        Chapter_IdChapter = chapter_IdChapter;
    }

    public Integer getImageNumber() {
        return ImageNumber;
    }

    public void setImageNumber(Integer imageNumber) {
        ImageNumber = imageNumber;
    }

    public Bitmap getImage() {
        return Image;
    }

    public void setImage(Bitmap image) {
        Image = image;
    }

    public String getUrlImage() {
        return UrlImage;
    }

    public void setUrlImage(String urlImage) {
        UrlImage = urlImage;
    }

    public String getChapter_IdChapter() {
        return Chapter_IdChapter;
    }

    public void setChapter_IdChapter(String chapter_IdChapter) {
        Chapter_IdChapter = chapter_IdChapter;
    }

    public Chapter getmChapter() {
        return mChapter;
    }

    public void setmChapter(Chapter mChapter) {
        this.mChapter = mChapter;
    }
}
