/*
 * Coder: Nguyen Van An
 * Date: 11-5-2021
 * Content:
 *
 *
 *
 * */
package com.example.mangaz;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mangaz.category.ViewPager2Adapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PhanLoaiFragment extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager2 viewPager2;
    private Database db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phan_loai, container, false);
        //
        mTabLayout = view.findViewById(R.id.tabLayoutCategory);
        viewPager2 = view.findViewById(R.id.viewPager2Category);
        db = new Database(getActivity());
        ViewPager2Adapter adapter = new ViewPager2Adapter(getActivity(), getListCatrgory());
        viewPager2.setAdapter(adapter);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(mTabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull @NotNull TabLayout.Tab tab, int position) {
                tab.setText(getListCatrgory().get(position));
            }
        });
        tabLayoutMediator.attach();
        return view;
    }

    private List<String> getListCatrgory() { // truyền dữ liệu vào RecyclerView
        List<String> listCategory = new ArrayList<>();
        Cursor cursor = db.GetListCategory();
        listCategory.add("ALL");
        while (cursor.moveToNext()){
            listCategory.add(cursor.getString(0));
        }
        return listCategory;
    }
}