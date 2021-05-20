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

import java.util.ArrayList;

public class AdminMangaActivity extends AppCompatActivity {

    private ListView listViewAdminManga;
    private Button buttonAdminAddManga, buttonAdminAddCatgory;
    private Database db;
    private ArrayList<String> arrayList;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manga);
        // Ánh xạ
        listViewAdminManga = findViewById(R.id.listViewAdminManga);
        buttonAdminAddManga = findViewById(R.id.buttonAdminAddManga);
        buttonAdminAddCatgory = findViewById(R.id.buttonAdminAddCatgory);
        // khai báo
        db = new Database(this);
        arrayList = new ArrayList<String>();
        // lấy data
        Cursor cursor = db.GetListManga();
        while (cursor.moveToNext()) {
            arrayList.add(cursor.getString(0));
        }
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        listViewAdminManga.setAdapter(adapter);
        listViewAdminManga.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AdminMangaActivity.this, AdminChapterActivity.class);
                intent.putExtra("mangaName", arrayList.get(position));
                startActivity(intent);
            }
        });
        buttonAdminAddManga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMangaActivity.this, AdminAddMangaActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onResume() {
        arrayList.removeAll(arrayList);
        Cursor cursor = db.GetListManga();
        while (cursor.moveToNext()) {
            arrayList.add(cursor.getString(0));
        }
        adapter.notifyDataSetChanged();
        super.onResume();
    }
}