package com.example.mangaz;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.palette.graphics.Palette;
import androidx.viewpager.widget.ViewPager;

import com.example.mangaz.Model.Manga;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;


public class MangaDetailActivity extends AppCompatActivity {

    private ImageView imgPoster;
    private TabLayout mangatab;
    private ViewPager viewPager;
    private ViewTabAdapter tabAdapter;
    private String mName = "";
    private MangaZSharedPreferences mMangaZSharedPreferences;
    private VarFinal mVarFinal;
    private TextView tvMangaName, tvMangaCategory, tvCountLike, tvCountComment;
    private int countLike, countComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manga_detail);

        Database database = new Database(MangaDetailActivity.this);

        Bundle b = getIntent().getExtras();//lay gia tri tu Intent

        this.inittoolbar();//set up toolbar

        this.anhxa();//anh xa cac thanh phan

        //gan gia tri
        mName = b.getString("MangaName");
        mMangaZSharedPreferences = new MangaZSharedPreferences(MangaDetailActivity.this);
        mVarFinal = new VarFinal();
        mMangaZSharedPreferences.putStringValue(mVarFinal.MANGA_NAME, mName.trim());

        Manga manga = database.GetManga(mName);

        Cursor c = database.GetData("SELECT * FROM CategoryManga WHERE Manga_MangaName='" + mName + "'");
        String str = "";
        while (c.moveToNext()) {
            str += c.getString(0) + "  ";
        }
        tvMangaCategory.setText(str);
        c = database.GetData("SELECT * FROM UserChapter WHERE IsLike <> 0 AND Chapter_IdChapter LIKE '%" + mName + "%'");
        countLike = c.getCount();
        c = database.GetData("SELECT * FROM Comment WHERE Chapter_IdChapter LIKE '%" + mName + "%' AND AnswerIdComment=''");
        countComment = c.getCount();

        tvMangaName.setText(mName);
        tvCountLike.setText(countLike + " Likes");
        tvCountComment.setText(countComment + " Comments");
        //khoi tao viewtab
        tabAdapter = new ViewTabAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(tabAdapter);
        mangatab.setupWithViewPager(viewPager);
        this.settablayoutanimation();
        imgPoster.setImageBitmap(manga.getAvatar());
    }

    //anh xa
    public void anhxa() {
        mangatab = findViewById(R.id.mangatab);
        viewPager = findViewById(R.id.viewpager);
        tvMangaName = findViewById(R.id.tvMangaName);
        tvMangaCategory = findViewById(R.id.tvMangaCategory);
        tvCountLike = findViewById(R.id.tvCountLike);
        tvCountComment = findViewById(R.id.tvCountComment);
        imgPoster = findViewById(R.id.imgPoster);
    }

    private void inittoolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void settablayoutanimation() {
        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsetoolbarLayout);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.kenganashura);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(@Nullable Palette palette) {
                int mycolor = palette.getVibrantColor(getResources().getColor(R.color.design_default_color_primary));
                int mydarkcolor = palette.getVibrantColor(getResources().getColor(R.color.black_trans));
                collapsingToolbarLayout.setContentScrimColor(mycolor);
                collapsingToolbarLayout.setStatusBarScrimColor(mydarkcolor);
            }
        });
    }
}