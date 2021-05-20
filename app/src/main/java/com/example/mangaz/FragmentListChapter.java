package com.example.mangaz;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mangaz.Model.Chapter;
import com.example.mangaz.Model.Manga;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentListChapter extends Fragment {

    private RecyclerView rcvChap;
    private View mview;
    private Database database;
    private String MgName, IdChap;
    private MangaZSharedPreferences mMangaZSharedPreferences;
    private VarFinal mVarFinal;

    public FragmentListChapter() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mview = inflater.inflate(R.layout.fragment_list_chapter, container, false);

        database = new Database(getActivity());
        mMangaZSharedPreferences = new MangaZSharedPreferences(getActivity());
        mVarFinal = new VarFinal();
        MgName = mMangaZSharedPreferences.GetStringValue(mVarFinal.MANGA_NAME);

        rcvChap = mview.findViewById(R.id.rcv_listchap);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rcvChap.setLayoutManager(linearLayoutManager);

        ChapDetailAdapter chapadapter = new ChapDetailAdapter(getListChap(), new ChapDetailAdapter.OnClickChapListener() {
            @Override
            public void OnClickChap(Chapter chapter) {
                Intent i = new Intent(getContext(), ActivityRead.class);
                i.putExtra("IdChap", chapter.getIdChapter());
                startActivity(i);
            }
        });
        //chapadapter.setdata(getListChap());
        rcvChap.setAdapter(chapadapter);


        return mview;
    }

    public List<Chapter> getListChap() {
        List<Chapter> list = new ArrayList<>();
        Cursor c = database.GetData("SELECT * FROM Chapter WHERE Manga_MangaName='" + MgName + "'");
        while (c.moveToNext()) {
            Manga manga = database.GetManga(MgName);
            list.add(new Chapter(c.getString(0),c.getInt(1), c.getString(2), c.getString(3), c.getString(4),manga));
        }
        return list;
    }
}