/*
 * Coder: Nguyen Van An
 * Date: 10-5-2021
 * Content: HomePage
 *      + BottomNavigationBar
 *
 *
 * */
package com.example.mangaz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private Toast toast;
    private long backPressTime;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_bar);
        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_container, homeFragment).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.nav_trang_chu: {
                        fragment = new HomeFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_container, fragment).commit();
                        break;
                    }
                    case R.id.nav_phan_loai: {
                        fragment = new PhanLoaiFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_container, fragment).commit();

                        break;
                    }
                    case R.id.nav_tai_khoan: {
                        fragment = new TaiKhoanFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_container, fragment).commit();
                        break;
                    }

                }
                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (backPressTime + 2000 > System.currentTimeMillis()) {
            toast.cancel();
            super.onBackPressed();
            return;
        } else {
            toast = Toast.makeText(MainActivity.this, "Nhấp Back 1 lần nữa để thoát ", Toast.LENGTH_SHORT);
            //  + R.drawable.image_tien_de_tro_ve + " "+ R.drawable.image_dau_la_dai_luc + " "+ R.drawable.image_tan_the_nguoi_tran + " "+ R.drawable.image_wechat_cua_toi_lien_ket_tam_gioi + " "+ R.drawable.image_nhat_hoa_khoi_ve_lam_vo + " "+ R.drawable.image_tong_tai_tai_thuong + " "
            toast.show();
        }
        backPressTime = System.currentTimeMillis();
    }
}