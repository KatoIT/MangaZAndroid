package com.example.mangaz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.mangaz.Model.Manga;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AdminAddMangaActivity extends AppCompatActivity {
    private ImageView imageViewAvataManga;
    private EditText editTextMangaName, editTextAuthor, editTextIntroduce;
    private RadioButton radioButtonManga, radioButtonNotManga;
    private Button buttonAddManga;
    private Database db;
    private String MangaName, Author, Introduce;
    private Boolean IsManga;
    private int REQUEST_CODE_FOLDER = 1108;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_manga);
        // Ánh xạ
        imageViewAvataManga = findViewById(R.id.imageViewAvataManga);
        buttonAddManga = findViewById(R.id.buttonAddManga);
        editTextMangaName = findViewById(R.id.editTextMangaName);
        editTextAuthor = findViewById(R.id.editTextAuthor);
        editTextIntroduce = findViewById(R.id.editTextIntroduce);
        radioButtonManga = findViewById(R.id.radioButtonManga);
        radioButtonNotManga = findViewById(R.id.radioButtonNotManga);
        // Khai báo
        db = new Database(this);

        // Xử lý
        buttonAddManga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IsManga = radioButtonManga.isChecked();
                MangaName = editTextMangaName.getText().toString();
                Author = editTextAuthor.getText().toString();
                Introduce = editTextIntroduce.getText().toString();
                if (MangaName.isEmpty() || Author.isEmpty() || Introduce.isEmpty()) {
                    Toast.makeText(AdminAddMangaActivity.this, "Điền đủ các ô thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    Manga manga = db.GetManga(MangaName);
                    if (manga != null) {
                        Toast.makeText(AdminAddMangaActivity.this, "Truyện này đã có" + MangaName, Toast.LENGTH_SHORT).show();
                    } else {
                        // lấy ảnh từ imageView
                        BitmapDrawable bitmapDrawable = (BitmapDrawable) imageViewAvataManga.getDrawable();
                        Bitmap bitmap = bitmapDrawable.getBitmap();
                        Manga manga1 = new Manga(MangaName, Author, IsManga, Introduce, true, "", bitmap);
                        db.INSERT_MANGA(manga1);
                        Toast.makeText(AdminAddMangaActivity.this, "Đã thêm " + MangaName, Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }

            }
        });
        imageViewAvataManga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_FOLDER);

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
                imageViewAvataManga.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                Toast.makeText(AdminAddMangaActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}