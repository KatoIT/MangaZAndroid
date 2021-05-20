/*
 * Coder: Nguyen Van An
 * Date: 11-5-2021
 * Content: Xử lý hiển thị list manga (RecyclerView)
 *
 *
 *
 * */
package com.example.mangaz;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import com.example.mangaz.Model.Manga;
import com.example.mangaz.nomination.Nomination;
import com.example.mangaz.nomination.NominationAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerViewHomeFragment;
    private NominationAdapter nominationAdapter;
    private Database db;
    private Cursor cursor;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // ánh xạ
        recyclerViewHomeFragment = view.findViewById(R.id.recyclerViewHomeFragment);
        //
        db = new Database(getActivity());

        nominationAdapter = new NominationAdapter(getActivity(), new NominationAdapter.IClickItem() {
            @Override
            public void onClick(Manga manga) {
                Toast.makeText(getActivity(), manga.getMangaName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MangaDetailActivity.class);
                String MangaName = manga.getMangaName();
                intent.putExtra("MangaName", MangaName);
                startActivity(intent);

            }

            @Override
            public void onClickImageButton() {
//                Toast.makeText(getActivity(), "Search", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }

        });
        // set recyclerViewHomeFragment
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerViewHomeFragment.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        recyclerViewHomeFragment.addItemDecoration(itemDecoration);
        nominationAdapter.setData(getListNomination());
        recyclerViewHomeFragment.setAdapter(nominationAdapter);

        return view;

    }

    private List<Nomination> getListNomination() { // truyền dữ liệu vào RecyclerView
        List<Nomination> listNominations = new ArrayList<>();
        //
        cursor = db.GetListManga();
        List<Manga> mangaList = GetMangaList(cursor);
        //
        cursor = db.GetListMangaNewUpdate();
        List<Manga> mangaList1 = GetMangaList(cursor);
        //
        cursor = db.GetListMangaTopLike();
        List<Manga> mangaList2 = GetMangaList(cursor);
        //
        cursor = db.GetListMangaTopView();
        List<Manga> mangaList3 = GetMangaList(cursor);
        //
        cursor = db.GetListMangaTopFollow();
        List<Manga> mangaList4 = GetMangaList(cursor);
        //
        cursor = db.GetListMangaTopComment();
        List<Manga> mangaList5 = GetMangaList(cursor);

        listNominations.add(new Nomination(mangaList, " ", 2));
        listNominations.add(new Nomination(mangaList1, "Mới cập nhật", 1));
        listNominations.add(new Nomination(mangaList2, "Được yêu thích nhất", 1));
        listNominations.add(new Nomination(mangaList3, "Nhiều lượt xem nhất", 1));
        listNominations.add(new Nomination(mangaList4, "Nhiều người theo dõi nhất", 1));
        listNominations.add(new Nomination(mangaList5, "Nhiều bình luận nhất", 1));
        return listNominations;
    }

    public List<Manga> GetMangaList(Cursor c) {
        List<Manga> mangaList = new ArrayList<>();
        String mName = " ";
        while (c.moveToNext()) {
            if (!mName.equals(c.getString(0))) {
                mName = c.getString(0);
                mangaList.add(db.GetManga(mName));
                if (mangaList.size() > 4) {
                    break;
                }
            }

        }
        return mangaList;
    }

    public Bitmap covertBytesToBitmap(byte[] bytes) {
        // byte[] --> bitmat
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }


}