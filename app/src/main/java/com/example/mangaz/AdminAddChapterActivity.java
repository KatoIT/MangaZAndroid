package com.example.mangaz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mangaz.Model.Chapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AdminAddChapterActivity extends AppCompatActivity {
    private EditText editTextContentChapter;
    private TextView textViewIdChapter;
    private Button buttonAddChapterNew;
    private Database db;
    private String mMangaName, IdChapter, ContentChapter;
    private int Chap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_chapter);
        // Ánh xạ
        editTextContentChapter = findViewById(R.id.editTextContentChapter);
        textViewIdChapter = findViewById(R.id.textViewIdChapter);
        buttonAddChapterNew = findViewById(R.id.buttonAddChapterNew);
        // Khai bao
        db = new Database(this);
        Intent intent = getIntent();
        // Xu ly
        mMangaName = intent.getStringExtra("mangaName");
        Cursor cursor = db.GetData("SELECT Chap FROM Chapter WHERE Manga_MangaName = '" + mMangaName + "'");
        Chap = cursor.getCount();
        IdChapter = mMangaName + "-" + Chap;
        textViewIdChapter.setText(IdChapter);
        buttonAddChapterNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentChapter = editTextContentChapter.getText().toString();
                if (ContentChapter.isEmpty()) {
                    ContentChapter = " ";
                }
                Chapter chapter = new Chapter(IdChapter, Chap, ContentChapter, getCurrentTime(), mMangaName);
                db.INSERT_CHAPTER(chapter);
                finish();
            }
        });


    }

    private String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(calendar.getTime());
    }
}