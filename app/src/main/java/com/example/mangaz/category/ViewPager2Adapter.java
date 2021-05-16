package com.example.mangaz.category;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ViewPager2Adapter extends FragmentStateAdapter {
    private List<String> mCategoreList;

    public ViewPager2Adapter(@NonNull @NotNull FragmentActivity fragmentActivity, List<String> mCategoreList) {
        super(fragmentActivity);
        this.mCategoreList = mCategoreList;
    }

    @NotNull
    @Override
    public Fragment createFragment(int position) {
        return new ItemCategoryPhanLoaiFragment(mCategoreList.get(position));
    }

    @Override
    public int getItemCount() {
        if (mCategoreList == null) {
            return 0;
        }
        return mCategoreList.size();
    }
}
