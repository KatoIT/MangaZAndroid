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
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaz.login.LoginActivity;
import com.example.mangaz.manga.Manga;
import com.example.mangaz.nomination.Nomination;
import com.example.mangaz.nomination.NominationAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerViewHomeFragment;
    private NominationAdapter nominationAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // ánh xạ
        recyclerViewHomeFragment = view.findViewById(R.id.recyclerViewHomeFragment);
        nominationAdapter = new NominationAdapter(getActivity(), new NominationAdapter.IClickItem() {
            @Override
            public void onClick(Manga manga) {
                Toast.makeText(getActivity(), manga.getMangaName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);

            }
        });
        // set recyclerViewHomeFragment
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerViewHomeFragment.setLayoutManager(linearLayoutManager);

        nominationAdapter.setData(getListNomination());
        recyclerViewHomeFragment.setAdapter(nominationAdapter);

        return view;

    }

    private List<Nomination> getListNomination() { // truyền dữ liệu vào RecyclerView
        List<Nomination> listNominations = new ArrayList<>();
        List<Manga> mangaList = new ArrayList<>();
        mangaList.add(new Manga("Manga 1", true, ""));
        mangaList.add(new Manga("Manga 2", true, ""));
        mangaList.add(new Manga("Manga 3", true, ""));
        mangaList.add(new Manga("Manga 4", true, ""));
        mangaList.add(new Manga("Manga 5", true, ""));
        mangaList.add(new Manga("Manga 6", true, ""));

        listNominations.add(new Nomination(mangaList, "Mới cập nhật"));
        listNominations.add(new Nomination(mangaList, "Hot"));
        listNominations.add(new Nomination(mangaList, "Top tuần"));
        listNominations.add(new Nomination(mangaList, "Top tháng"));
        listNominations.add(new Nomination(mangaList, "Top lượt xem"));
        listNominations.add(new Nomination(mangaList, "Đề cử 6"));
        listNominations.add(new Nomination(mangaList, "Đề cử 7"));
        listNominations.add(new Nomination(mangaList, "Đề cử 8"));
        return listNominations;
    }


}