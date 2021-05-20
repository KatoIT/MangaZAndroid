package com.example.mangaz.category;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaz.Database;
import com.example.mangaz.MangaDetailActivity;
import com.example.mangaz.Model.Manga;
import com.example.mangaz.R;
import com.example.mangaz.manga.MangaAdapter;

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

        mCategoryAdapter = new CategoryAdapter(getActivity(),new MangaAdapter.IClickItemListener() {
            @Override
            public void onClickItemManga(Manga manga) {
//                Toast.makeText(getActivity(), manga.getMangaName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MangaDetailActivity.class);
                String MangaName = manga.getMangaName();
                intent.putExtra("MangaName", MangaName);
                startActivity(intent);
            }
        });
        recyclerViewCategory = view.findViewById(R.id.recyclerViewCategory);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerViewCategory.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        recyclerViewCategory.addItemDecoration(itemDecoration);

        mCategoryAdapter.setData(GetMangaList());
        recyclerViewCategory.setAdapter(mCategoryAdapter);
        return view;
    }

    public List<Manga> GetMangaList() {
        List<Manga> mangaList = new ArrayList<>();
        Cursor cursor = null;
        if(categoryName.equals("ALL")){
            cursor = db.GetListManga();
        }else {
            cursor = db.GetListMangaByCategory(categoryName);
        }
        while (cursor.moveToNext()) {
            mangaList.add(db.GetManga(cursor.getString(0)));
        }
        return mangaList;
    }

    public Bitmap covertBytesToBitmap(byte[] bytes) {
        // byte[] --> bitmat
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }


}
