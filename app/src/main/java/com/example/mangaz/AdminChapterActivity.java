package com.example.mangaz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdminChapterActivity extends AppCompatActivity {
    private ListView listViewChapter;
    private Button buttonAddChapter, buttonGoToAddCategory;
    private TextView textViewMangaName, textViewMangaCategory;
    private String mMangaName, mCategory;
    private Database db;
    private ArrayList<String> arrayListChapter;
    private ArrayAdapter adapterChapter;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_chapter);
        //Ánh xạ
        listViewChapter = findViewById(R.id.listViewChapter);
        buttonAddChapter = findViewById(R.id.buttonAddChapter);
        buttonGoToAddCategory = findViewById(R.id.buttonGoToAddCategory);
        textViewMangaName = findViewById(R.id.textViewMangaName);
        textViewMangaCategory = findViewById(R.id.textViewMangaCategory);
        // Khai báo
        db = new Database(this);
        Intent intent = getIntent();
        arrayListChapter = new ArrayList<>();
        // Xu ly
        mMangaName = intent.getStringExtra("mangaName");
        textViewMangaCategory.setText(mCategory);
        textViewMangaName.setText(mMangaName);
//        Toast.makeText(AdminChapterActivity.this, a, Toast.LENGTH_SHORT).show();
        cursor = db.GetChapterByMangaName(mMangaName);
        while (cursor.moveToNext()) {
            arrayListChapter.add(cursor.getString(0));
        }
        adapterChapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayListChapter);
        listViewChapter.setAdapter(adapterChapter);
        // sk Click listViewChapter item
        listViewChapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AdminChapterActivity.this, AdminAddImageChapterActivity.class);
                intent.putExtra("idChapter", arrayListChapter.get(position));
                startActivity(intent);
            }
        });
        // sk Click buttonAddChapter
        buttonAddChapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminChapterActivity.this, AdminAddChapterActivity.class);
                intent.putExtra("mangaName", mMangaName);
                startActivity(intent);
            }
        });
        // sk Click buttonGoToAddCategory
        buttonGoToAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminChapterActivity.this, AdminAddMangaCategoryActivity.class);
                intent.putExtra("mangaName", mMangaName);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        arrayListChapter.removeAll(arrayListChapter);
        mCategory = "";
        cursor = db.GetChapterByMangaName(mMangaName);
        while (cursor.moveToNext()) {
            arrayListChapter.add(cursor.getString(0));
        }
        adapterChapter.notifyDataSetChanged();
        cursor = db.GetListCategoryByMangaName(mMangaName);
        while (cursor.moveToNext()) {
            if (mCategory.isEmpty()) {
                mCategory = cursor.getString(0);
            } else {
                mCategory += "/" + cursor.getString(0);

            }
        }
        textViewMangaCategory.setText(mCategory);
        super.onResume();
    }
}