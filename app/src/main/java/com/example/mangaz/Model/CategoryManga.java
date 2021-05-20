package com.example.mangaz.Model;

public class CategoryManga {
    private String Category_CategoryName;
    private String Manga_MangaName;
    private Category mCategory;
    private Manga mManga;

    public CategoryManga(String category_CategoryName, String manga_MangaName, Category mCategory, Manga mManga) {
        Category_CategoryName = category_CategoryName;
        Manga_MangaName = manga_MangaName;
        this.mCategory = mCategory;
        this.mManga = mManga;
    }

    public CategoryManga(String category_CategoryName, String manga_MangaName) {
        Category_CategoryName = category_CategoryName;
        Manga_MangaName = manga_MangaName;
    }

    public String getCategory_CategoryName() {
        return Category_CategoryName;
    }

    public void setCategory_CategoryName(String category_CategoryName) {
        Category_CategoryName = category_CategoryName;
    }

    public String getManga_MangaName() {
        return Manga_MangaName;
    }

    public void setManga_MangaName(String manga_MangaName) {
        Manga_MangaName = manga_MangaName;
    }

    public Category getmCategory() {
        return mCategory;
    }

    public void setmCategory(Category mCategory) {
        this.mCategory = mCategory;
    }

    public Manga getmManga() {
        return mManga;
    }

    public void setmManga(Manga mManga) {
        this.mManga = mManga;
    }
}
