package com.example.mangaz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mangaz.Model.CategoryManga;

import java.util.ArrayList;

public class AdminAddMangaCategoryActivity extends AppCompatActivity {
    private Spinner spinnerCategory;
    private TextView textViewMangaName;
    private Button buttonAddMangaCategory;
    private Database db;
    private String mMangaName;
    private ArrayList<String> arrayList;
    private ArrayAdapter adapter;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_manga_category);
        // Anh xa
        spinnerCategory = findViewById(R.id.spinnerCategory);
        textViewMangaName = findViewById(R.id.textViewMangaName);
        buttonAddMangaCategory = findViewById(R.id.buttonAddMangaCategory);
        // Khai bao
        db = new Database(this);
        Intent intent = getIntent();
        arrayList = new ArrayList<String>();
        // Xu ly
        mMangaName = intent.getStringExtra("mangaName");
        cursor = db.GetListCategory();
        while (cursor.moveToNext()) {
            arrayList.add(cursor.getString(0));
        }
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        spinnerCategory.setAdapter(adapter);

        buttonAddMangaCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = spinnerCategory.getSelectedItem().toString();
                cursor = db.GetData("SELECT * FROM CategoryManga WHERE Category_CategoryName = '" + category + "' AND Manga_MangaName = '" + mMangaName + "'");
                if (cursor.getCount() != 0) {
                    Toast.makeText(AdminAddMangaCategoryActivity.this, "Truyện đã thuộc thể loại này!", Toast.LENGTH_SHORT).show();
                } else {
                    CategoryManga categoryManga = new CategoryManga(category, mMangaName);
                    db.INSERT_CATEGORYMANGA(categoryManga);
                    finish();
                }
            }
        });


    }
}