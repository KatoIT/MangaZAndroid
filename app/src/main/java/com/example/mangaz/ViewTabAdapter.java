package com.example.mangaz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewTabAdapter extends FragmentPagerAdapter {
    public ViewTabAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new FragmentMangaInfo();
            case 1:
                return new FragmentListChapter();
            default:
                return new FragmentMangaInfo();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title="";
        switch (position){
            case 0:
                title="Chi Tiết";
                break;
            case 1:
                title="Chapter";
                break;
        }
        return title;
    }
}
