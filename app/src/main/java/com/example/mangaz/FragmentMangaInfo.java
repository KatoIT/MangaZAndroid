package com.example.mangaz;

import android.app.Dialog;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mangaz.Model.Comment;
import com.example.mangaz.Model.User;
import com.example.mangaz.Model.UserManga;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMangaInfo extends Fragment {

    private View myView;
    private RecyclerView rcvcmt;
    private ImageButton ibtnRate, ibtnRateConfirm;
    private ImageView btnFollow;
    private TextView tvfollow, tvIntro, tvAuthor, tvVote;
    private boolean isfollow;
    private String MgName;
    private Database database;
    private MangaZSharedPreferences mMangaZSharedPreferences;
    private VarFinal mVarFinal;
    private String UserName;
    private UserManga mUserManga;
    private int mVote;

    public FragmentMangaInfo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_manga_info, container, false);

        database = new Database(getContext());
        mMangaZSharedPreferences = new MangaZSharedPreferences(getActivity());
        mVarFinal = new VarFinal();

        MgName = mMangaZSharedPreferences.GetStringValue(mVarFinal.MANGA_NAME);
        UserName = mMangaZSharedPreferences.GetStringValue(mVarFinal.ACCOUNT);

        rcvcmt = myView.findViewById(R.id.rcv_listcmt);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rcvcmt.setLayoutManager(linearLayoutManager);

        CmtAdapter cmtAdapter = new CmtAdapter();
        cmtAdapter.setdata(getListCmt());
        rcvcmt.setAdapter(cmtAdapter);

        anhxa();//anh xa

        setData();//truyen du lieu cho cac view

        //bat su kien nut Danh gia
        ibtnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UserName.equals(mVarFinal.TXT_NULL)) {
                    Toast.makeText(getActivity(), "Vui lòng đang đăng nhập!", Toast.LENGTH_SHORT).show();
                } else {
                    openRateDialog();
                }
            }
        });

        //bat su kien nut theo doi
        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UserName.equals(mVarFinal.TXT_NULL)) {
                    Toast.makeText(getActivity(), "Vui lòng đang đăng nhập!", Toast.LENGTH_SHORT).show();
                } else {
                    CheckFollow(mUserManga.getFollow());
                    mUserManga = database.GetUserManga(UserName, MgName);
                }
            }
        });
        tvIntro.setMovementMethod(new ScrollingMovementMethod());
        return myView;
    }

    //ham anh xa
    public void anhxa() {
        tvIntro = (TextView) myView.findViewById(R.id.tvPlot);
        tvAuthor = (TextView) myView.findViewById(R.id.tvAuthor);
        tvVote = (TextView) myView.findViewById(R.id.tvRate);
        ibtnRate = (ImageButton) myView.findViewById(R.id.ibtnRate);
        tvfollow = (TextView) myView.findViewById(R.id.tvfollow);
        btnFollow = (ImageView) myView.findViewById(R.id.btnfollow);
    }

    //truyen data
    private void setData() {
        Cursor c1 = database.GetData("SELECT * FROM Manga WHERE MangaName='" + MgName + "'");
        c1.moveToFirst();
        tvIntro.setText(c1.getString(3));
        tvAuthor.setText("Tác giả: " + c1.getString(1));

        c1 = database.GetData("SELECT * FROM UserManga WHERE Vote NOT NULL AND Manga_MangaName ='" + MgName + "'");
        double sumofvote = 0;
        while (c1.moveToNext()) {
            if (UserName.equals(c1.getString(2))) {
                isfollow = Boolean.valueOf(c1.getString(0));
            }
            sumofvote += c1.getInt(1);
        }
        sumofvote = (double) Math.round((sumofvote / c1.getCount()) * 100) / 100;
        tvVote.setText("Rate: " + sumofvote + " ⭐");
        //
        if (database.isUserManga(UserName, MgName)) {
            mUserManga = database.GetUserManga(UserName, MgName);
            if (mUserManga.getFollow()) {
                btnFollow.setImageResource(R.drawable.follow);
                tvfollow.setText("Bỏ theo dõi");
            } else {
                btnFollow.setImageResource(R.drawable.unfollow);
                tvfollow.setText("Theo dõi");
            }

        } else {
            btnFollow.setImageResource(R.drawable.unfollow);
            tvfollow.setText("Theo dõi");
            database.QueryData("INSERT INTO UserManga VALUES(0,0,'" + UserName + "','" + MgName + "');");
            mUserManga = database.GetUserManga(UserName, MgName);
        }
    }

    //check follow
    private void CheckFollow(Boolean isFollow) {
        if (!isFollow) {
            boolean a = true;
            btnFollow.setImageResource(R.drawable.follow);
            tvfollow.setText("Bỏ theo dõi");
            //add code here...
            database.QueryData("UPDATE UserManga SET IsFollow = '" + a + "' WHERE User_UserName = '" + UserName + "' AND Manga_MangaName = '" + MgName + "';");
        } else {
            boolean a = false;
            btnFollow.setImageResource(R.drawable.unfollow);
            tvfollow.setText("Theo dõi");
            //add code here...
            database.QueryData("UPDATE UserManga SET IsFollow = '" + a + "' WHERE User_UserName = '" + UserName + "' AND Manga_MangaName = '" + MgName + "';");
        }
    }

    //ham lay ra danh sach cac comment
    public List<Comment> getListCmt() {
        List<Comment> list = new ArrayList<>();
        Cursor c = database.GetData("SELECT * FROM Comment WHERE AnswerIdComment = '' ORDER BY DateComment DESC");
        while (c.moveToNext()) {
            if (c.getString(5).contains(MgName)) {
                User mUser = database.GetUser(c.getString(4));
                list.add(new Comment(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), mUser));
            }
        }
        return list;
    }

    //ham xu li su kien cua dialog Rate
    private void openRateDialog() {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_rate);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //xu li vi tri cua dialog
        WindowManager.LayoutParams windowAttribute = window.getAttributes();
        windowAttribute.gravity = Gravity.CENTER;
        window.setAttributes(windowAttribute);

        //anh xa thanh phan trong dialog
        ImageView rate1 = (ImageView) dialog.findViewById(R.id.rate1);
        ImageView rate2 = (ImageView) dialog.findViewById(R.id.rate2);
        ImageView rate3 = (ImageView) dialog.findViewById(R.id.rate3);
        ImageView rate4 = (ImageView) dialog.findViewById(R.id.rate4);
        ImageView rate5 = (ImageView) dialog.findViewById(R.id.rate5);
        ibtnRateConfirm = (ImageButton) dialog.findViewById(R.id.ibtnRateConfirm);

        //bat su kien ca thanh phan trong dialog
        rate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rate2.setImageResource(R.drawable.star);
                rate3.setImageResource(R.drawable.star);
                rate4.setImageResource(R.drawable.star);
                rate5.setImageResource(R.drawable.star);
                rate1.setImageResource(R.drawable.yellowstar);
                mVote = 1;
            }
        });

        rate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rate3.setImageResource(R.drawable.star);
                rate4.setImageResource(R.drawable.star);
                rate5.setImageResource(R.drawable.star);
                rate1.setImageResource(R.drawable.yellowstar);
                rate2.setImageResource(R.drawable.yellowstar);
                mVote = 2;
            }
        });

        rate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rate4.setImageResource(R.drawable.star);
                rate5.setImageResource(R.drawable.star);
                rate1.setImageResource(R.drawable.yellowstar);
                rate2.setImageResource(R.drawable.yellowstar);
                rate3.setImageResource(R.drawable.yellowstar);
                mVote = 3;
            }
        });

        rate4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rate5.setImageResource(R.drawable.star);
                rate1.setImageResource(R.drawable.yellowstar);
                rate2.setImageResource(R.drawable.yellowstar);
                rate3.setImageResource(R.drawable.yellowstar);
                rate4.setImageResource(R.drawable.yellowstar);
                mVote = 4;
            }
        });

        rate5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rate1.setImageResource(R.drawable.yellowstar);
                rate2.setImageResource(R.drawable.yellowstar);
                rate3.setImageResource(R.drawable.yellowstar);
                rate4.setImageResource(R.drawable.yellowstar);
                rate5.setImageResource(R.drawable.yellowstar);
                mVote = 5;
            }
        });

        ibtnRateConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add code here...
                database.QueryData("UPDATE UserManga SET Vote= " + mVote + " WHERE User_UserName = '" + UserName + "' AND Manga_MangaName = '" + MgName + "';");
                setData();
                dialog.dismiss();
            }
        });
        //chon ngoai dialog de dong dialog
        dialog.setCancelable(true);

        //show dialog
        dialog.show();
    }
}