package com.example.mangaz.nomination;

import com.example.mangaz.manga.Manga;

import java.util.List;

public class Nomination {
    private List<Manga> mangaList;
    private String NominationName;

    public Nomination(List<Manga> mangaList, String nominationName) {
        this.mangaList = mangaList;
        NominationName = nominationName;
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
}
