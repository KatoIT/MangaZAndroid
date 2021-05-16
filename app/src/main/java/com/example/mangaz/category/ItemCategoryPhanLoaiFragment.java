package com.example.mangaz.category;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaz.Database;
import com.example.mangaz.R;
import com.example.mangaz.manga.Manga;
import com.example.mangaz.manga.MangaAdapter;
import com.example.mangaz.nomination.Nomination;

import java.util.ArrayList;
import java.util.List;

public class ItemCategoryPhanLoaiFragment extends Fragment {
    private RecyclerView recyclerViewCategory;
    private CategoryAdapter mCategoryAdapter;
    private String categoryName;
    private Database db;

    public ItemCategoryPhanLoaiFragment(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_category_phan_loai_fragment, container, false);
        db = new Database(getActivity());
        mCategoryAdapter = new CategoryAdapter(new MangaAdapter.IClickItemListener() {
            @Override
            public void onClickItemManga(Manga manga) {
                Toast.makeText(getActivity(), manga.getMangaName(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerViewCategory = view.findViewById(R.id.recyclerViewCategory);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerViewCategory.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        recyclerViewCategory.addItemDecoration(itemDecoration);

        mCategoryAdapter.setData(getListManga());
        recyclerViewCategory.setAdapter(mCategoryAdapter);
        return view;
    }

    private List<Manga> getListManga() {
        List<Manga> mangaList = new ArrayList<>();
        Cursor cursor = db.GetListMangaByCategory(categoryName);
        while (cursor.moveToNext()){
            String category = "";
            Cursor cursor1 = db.GetListCategoryByMangaName(cursor.getString(0));
            while (cursor1.moveToNext()){
                category += cursor1.getString(0) + "   ";
            }
            cursor1 = db.GetChapterByMangaName(cursor.getString(0));
            mangaList.add(new Manga(cursor.getString(0), cursor.getString(4), category, cursor1.getCount()) );
        }

        return mangaList;
    }


}
