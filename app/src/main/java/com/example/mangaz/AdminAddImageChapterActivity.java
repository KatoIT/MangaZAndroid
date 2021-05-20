package com.example.mangaz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mangaz.Model.ChapterImage;
import com.example.mangaz.Model.Manga;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AdminAddImageChapterActivity extends AppCompatActivity {
    private ImageView imageViewImageChapter;
    private Button buttonAddImageChapter;
    private TextView textViewImageNumber;
    private Database db;
    private String IdChapter;
    private int REQUEST_CODE_FOLDER = 1108, ImageNumber;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_image_chapter);
        // Anh xa
        imageViewImageChapter = findViewById(R.id.imageViewImageChapter);
        buttonAddImageChapter = findViewById(R.id.buttonAddImageChapter);
        textViewImageNumber = findViewById(R.id.textViewImageNumber);
        // Khai bao
        db = new Database(this);
        Intent intent = getIntent();
        // Xu ly
        IdChapter = intent.getStringExtra("idChapter");
        cursor = db.GetData("SELECT * FROM ChapterImage WHERE Chapter_IdChapter = '" + IdChapter + "'");
        ImageNumber = cursor.getCount();
        textViewImageNumber.setText("Chap " + ImageNumber);
        //
        imageViewImageChapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_FOLDER);
            }
        });
        //
        buttonAddImageChapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // lấy ảnh từ imageView
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imageViewImageChapter.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ChapterImage chapterImage = new ChapterImage(ImageNumber, bitmap, "", IdChapter);
                db.INSERT_CHAPTERIMAGE(chapterImage);
                Toast.makeText(AdminAddImageChapterActivity.this, "Đã thêm ảnh " + ImageNumber, Toast.LENGTH_SHORT).show();
                Intent intent2 = getIntent();
                finish();
                startActivity(intent2);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        if (requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream mInputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(mInputStream);
                imageViewImageChapter.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                Toast.makeText(AdminAddImageChapterActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}