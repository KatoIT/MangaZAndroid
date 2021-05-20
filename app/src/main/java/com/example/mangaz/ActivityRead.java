package com.example.mangaz;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangaz.Model.ChapterImage;
import com.example.mangaz.Model.Comment;
import com.example.mangaz.Model.User;
import com.example.mangaz.Model.UserChapter;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ActivityRead extends AppCompatActivity {

    private ListView listPage;
    private List<ChapterImage> list;
    private CmtAdapter cmtAdapter;

    private RecyclerView listcmtchap;
    private ImageButton btnCmt;
    private EditText txtCmt;
    private ImageView btnLike, btnPreviousChap, btnNextChap;
    private TextView tvCountCmt, tvNumberChap;

    private String idchap, UserName, MgName;
    private Database database;

    private LinearLayout layoutbottomsheetcmt;
    private BottomSheetBehavior bottomSheetBehavior;
    private boolean isLike = false;
    private UserChapter userChapter;
    private PageAdapter adapter;

    private int mCurrentChap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        idchap = getIntent().getStringExtra("IdChap");
        database = new Database(ActivityRead.this);
        MangaZSharedPreferences mMangaZSharedPreferences = new MangaZSharedPreferences(this);
        VarFinal mVarFinal = new VarFinal();
        MgName = mMangaZSharedPreferences.GetStringValue(mVarFinal.MANGA_NAME);
        UserName = mMangaZSharedPreferences.GetStringValue(mVarFinal.ACCOUNT);

        List<Comments> listcmt = new ArrayList<>();
        //anh xa
        listPage = findViewById(R.id.listPage);
        listcmtchap = findViewById(R.id.rcv_listcmtchap);
        tvCountCmt = findViewById(R.id.tvCountCmtChap);
        tvNumberChap = findViewById(R.id.tvNumberChapter);

        layoutbottomsheetcmt = findViewById(R.id.bottom_sheet_cmt);
        bottomSheetBehavior = BottomSheetBehavior.from(layoutbottomsheetcmt);

        btnCmt = findViewById(R.id.btncmtchap);
        btnPreviousChap = findViewById(R.id.btnPreviousChap);
        btnNextChap = findViewById(R.id.btnNextChap);
        txtCmt = findViewById(R.id.txtcmtchap);
        btnLike = findViewById(R.id.btnlike);

        //truyen du lieu
        mCurrentChap = database.getNumberChapbyChapId(idchap);
        getChapLike();

        list = fixData2();
        //setup adapter
        adapter = new PageAdapter(ActivityRead.this, list);
        listPage.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
        listcmtchap.setLayoutManager(linearLayoutManager);

        cmtAdapter = new CmtAdapter();
        cmtAdapter.setdata(getListCmt());
        listcmtchap.setAdapter(cmtAdapter);

        tvCountCmt.setText(getListCmt().size() + " Bình luận");


        tvNumberChap.setText("Chapter " + mCurrentChap);
        //bat su kien nut comment
        btnCmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtCmt.getText() != null) {
                    if (txtCmt.getText().toString() != "") {
                        String ContentCMT = txtCmt.getText().toString();
                        String IdCmt = UserName + "-" + idchap + "-" + getCurrentTime();
                        Comment comment = new Comment(IdCmt, ContentCMT, "", getCurrentTime(), UserName, idchap);
                        addCmtData(comment);
                        cmtAdapter.setdata(getListCmt());
                        tvCountCmt.setText(getListCmt().size() + " Bình luận");
                    }

                }
                txtCmt.setText("");
            }
        });

        //bat su kien nut like
        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckLike(userChapter.getLike());
                userChapter = database.GetUserChapter(UserName, idchap);
            }
        });

        //bat su kien nut PreviousChap
        btnPreviousChap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickPrevious();
                list = fixData2();
                listPage.smoothScrollToPosition(0);
                adapter.setData(list);
            }
        });

        //bat su kien nut NextChap
        btnNextChap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickNext();
                list = fixData2();
                adapter.setData(list);
                listPage.smoothScrollToPosition(0);
            }
        });
    }

    private void getChapLike() {
        if (database.isUserLike(UserName, idchap)) {
            userChapter = database.GetUserChapter(UserName, idchap);
            if (userChapter.getLike()) {
                btnLike.setImageResource(R.drawable.like);
            } else {
                btnLike.setImageResource(R.drawable.unlike);
            }

        } else {
            btnLike.setImageResource(R.drawable.unlike);
            database.QueryData("INSERT INTO UserChapter VALUES('" + getCurrentTime() + "',0,'" + UserName + "','" + idchap + "');");
            userChapter = database.GetUserChapter(UserName, idchap);
        }
    }

    //xu ly su kien cho nut NextChap
    private void onClickNext() {
        if (mCurrentChap + 1 < database.GetData("SELECT * FROM Chapter WHERE Manga_MangaName = '" + MgName + "'").getCount()) {
            mCurrentChap = mCurrentChap + 1;
            idchap = database.GetChapIdbyNumberChap(mCurrentChap, MgName);
            //reset data
            tvNumberChap.setText("Chapter " + mCurrentChap);
            cmtAdapter.setdata(getListCmt());
            tvCountCmt.setText(getListCmt().size() + " Bình luận");
            //CheckLike(userChapter.getLike());
            getChapLike();
        }
    }

    //xu ly su kien cho nut PreviousChap
    private void onClickPrevious() {
        if (mCurrentChap > 0) {
            mCurrentChap = mCurrentChap - 1;
            idchap = database.GetChapIdbyNumberChap(mCurrentChap, MgName);
            //reset data
            tvNumberChap.setText("Chapter " + mCurrentChap);
            cmtAdapter.setdata(getListCmt());
            tvCountCmt.setText(getListCmt().size() + " Bình luận");

            //CheckLike(userChapter.getLike());
            getChapLike();
        }
    }

    //ham truyen du lieu cho listcmtchap
    public List<Comment> getListCmt() {
        List<Comment> list = new ArrayList<>();
        Cursor c = database.GetData("SELECT * FROM Comment WHERE AnswerIdComment = '' AND Chapter_IdChapter='" + idchap + "' ORDER BY DateComment DESC");
        while (c.moveToNext()) {
            User mUser = database.GetUser(c.getString(4));
            String dateTime = c.getString(3).split(" ")[0];
            list.add(new Comment(c.getString(0), c.getString(1), c.getString(2), dateTime, c.getString(4), c.getString(5), mUser));
        }
        return list;
    }


    private List<ChapterImage> fixData2() {
        List<ChapterImage> list = new ArrayList<>();
        Cursor cursor = database.GetData("SELECT * FROM ChapterImage WHERE Chapter_IdChapter = '" + idchap + "' ORDER BY ImageNumber");
        while (cursor.moveToNext()) {
            Bitmap bitmap;
            if (cursor.getBlob(1) != null) {
                bitmap = covertBytesToBitmap(cursor.getBlob(1));
            } else {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_anh_mac_dinh);
            }
            list.add(new ChapterImage(cursor.getInt(0),
                    bitmap,
                    cursor.getString(2),
                    cursor.getString(3)));
        }
        return list;
    }

    private void addCmtData(Comment cmt) {
        database.QueryData("INSERT INTO Comment VALUES('" + cmt.getIdComment() + "','" + cmt.getContentComment() + "','','" + cmt.getDateComment() + "','" + cmt.getUser_UserName() + "','" + cmt.getChapter_IdChapter() + "');");
    }

    private String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(calendar.getTime());
    }

    private void CheckLike(Boolean isLike) {
        if (!isLike) {
            boolean a = true;
            btnLike.setImageResource(R.drawable.like);
            //add code here...
            database.QueryData("UPDATE UserChapter SET IsLike = '" + a + "' WHERE User_UserName = '" + UserName + "' AND Chapter_IdChapter = '" + idchap + "';");
        } else {
            boolean a = false;
            btnLike.setImageResource(R.drawable.unlike);
            //add code here...
            database.QueryData("UPDATE UserChapter SET IsLike = '" + a + "' WHERE User_UserName = '" + UserName + "' AND Chapter_IdChapter = '" + idchap + "';");
        }
    }

    public Bitmap covertBytesToBitmap(byte[] bytes) {
        // byte[] --> bitmat
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}