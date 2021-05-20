package com.example.mangaz.Model;

public class Chapter {
    private String IdChapter;
    private Integer Chap;
    private String ContentChapter;
    private String DateUpdate;
    private String Manga_MangaName;
    private Manga mManga;


    public Chapter(String idChapter, Integer chap, String contentChapter, String dateUpdate, String manga_MangaName, Manga mManga) {
        IdChapter = idChapter;
        Chap = chap;
        ContentChapter = contentChapter;
        DateUpdate = dateUpdate;
        Manga_MangaName = manga_MangaName;
        this.mManga = mManga;
    }

    public Chapter(String idChapter, Integer chap, String contentChapter, String dateUpdate, String manga_MangaName) {
        IdChapter = idChapter;
        Chap = chap;
        ContentChapter = contentChapter;
        DateUpdate = dateUpdate;
        Manga_MangaName = manga_MangaName;
    }

    public String getIdChapter() {
        return IdChapter;
    }

    public void setIdChapter(String idChapter) {
        IdChapter = idChapter;
    }

    public Integer getChap() {
        return Chap;
    }

    public void setChap(Integer chap) {
        Chap = chap;
    }

    public String getContentChapter() {
        return ContentChapter;
    }

    public void setContentChapter(String contentChapter) {
        ContentChapter = contentChapter;
    }

    public String getDateUpdate() {
        return DateUpdate;
    }

    public void setDateUpdate(String dateUpdate) {
        DateUpdate = dateUpdate;
    }

    public String getManga_MangaName() {
        return Manga_MangaName;
    }

    public void setManga_MangaName(String manga_MangaName) {
        Manga_MangaName = manga_MangaName;
    }

    public Manga getmManga() {
        return mManga;
    }

    public void setmManga(Manga mManga) {
        this.mManga = mManga;
    }
}
