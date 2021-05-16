package com.example.mangaz.nomination;

import com.example.mangaz.manga.Manga;

import java.util.List;

public class Nomination {
    private List<Manga> mangaList;
    private String NominationName;
    private Integer Type;

    public Nomination(Integer type) {
        Type = type;
    }

    public Nomination(List<Manga> mangaList, String nominationName) {
        this.mangaList = mangaList;
        NominationName = nominationName;
    }

    public Nomination(List<Manga> mangaList, String nominationName, Integer type) {
        this.mangaList = mangaList;
        NominationName = nominationName;
        Type = type;
    }

    public List<Manga> getMangaList() {
        return mangaList;
    }

    public void setMangaList(List<Manga> mangaList) {
        this.mangaList = mangaList;
    }

    public String getNominationName() {
        return NominationName;
    }

    public void setNominationName(String nominationName) {
        NominationName = nominationName;
    }

    public Integer getType() {
        return Type;
    }

    public void setType(Integer type) {
        Type = type;
    }
}
