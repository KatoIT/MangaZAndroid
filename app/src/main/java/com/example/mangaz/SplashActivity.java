package com.example.mangaz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    private TextView textView;
    private MangaZSharedPreferences mSharedPreferences;
    private String account;
    private VarFinal mVarFinal = new VarFinal();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        textView = findViewById(R.id.textViewSplash);
        mSharedPreferences = new MangaZSharedPreferences(this);
        account = mSharedPreferences.GetStringValue(mVarFinal.ACCOUNT);
        if (!account.equals("null")) {
            textView.setText("Xin chào " + account + "❤,\nChúc bạn 1 ngày tốt lành");
        }else {
            textView.setText("Xin chào người mới❤,\nHãy đăng nhập để có được trải nghiệm tốt nhất!");
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActi(MainActivity.class);
                finish();
            }
        }, 2000);
    }

    private void startActi(Class<?> aClass) {
        Intent intent = new Intent(this, aClass);
        startActivity(intent);
    }
}