package com.example.mangaz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mangaz.category.CategoryAdapter;
import com.example.mangaz.manga.Manga;
import com.example.mangaz.manga.MangaAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private EditText editTextSearch;
    private Button buttonSearch;
    private RecyclerView recyclerViewSearch;
    private CategoryAdapter mCategoryAdapter;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        editTextSearch = findViewById(R.id.editTextSearch);
        buttonSearch = findViewById(R.id.buttonSearch);
        recyclerViewSearch = findViewById(R.id.recyclerViewSearch);
        db = new Database(this);

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count == 0) {
                    buttonSearch.setText("Hủy");
                } else {
                    buttonSearch.setText("Tìm Kiếm");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextSearch.getText().toString().trim().length() == 0) {
                    finish();
                } else {
                    String strSearch = editTextSearch.getText().toString().trim();
//                    Toast.makeText(SearchActivity.this, "Search: " + strSearch, Toast.LENGTH_SHORT).show();
                    mCategoryAdapter = new CategoryAdapter(new MangaAdapter.IClickItemListener() {
                        @Override
                        public void onClickItemManga(Manga manga) {
                            Toast.makeText(SearchActivity.this, manga.getMangaName(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchActivity.this, RecyclerView.VERTICAL, false);
                    recyclerViewSearch.setLayoutManager(linearLayoutManager);
                    mCategoryAdapter.setData(getListManga(strSearch));
                    recyclerViewSearch.setAdapter(mCategoryAdapter);
                }

            }
        });

    }

    private List<Manga> getListManga(String strSearch) {
        List<Manga> mangaList = new ArrayList<>();
        Cursor cursor = db.GetListManga();
        while (cursor.moveToNext()) {
            if (cursor.getString(0).toUpperCase().contains(strSearch.toUpperCase())) {
                String category = "";
                Cursor cursor1 = db.GetListCategoryByMangaName(cursor.getString(0));
                while (cursor1.moveToNext()) {
                    category += cursor1.getString(0) + "   ";
                }
                cursor1 = db.GetChapterByMangaName(cursor.getString(0));
                mangaList.add(new Manga(cursor.getString(0), cursor.getString(4), category, cursor1.getCount()));
            }
        }
        return mangaList;
    }
}