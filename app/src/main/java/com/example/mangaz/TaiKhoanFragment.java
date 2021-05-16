/*
 * Coder: Nguyen Van An
 * Date: 11-5-2021
 * Content:
 *
 *
 *
 * */
package com.example.mangaz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mangaz.login.LoginActivity;

public class TaiKhoanFragment extends Fragment {
    private View view;
    private Button buttonMainToLogin;
    private MangaZSharedPreferences mSharedPreferences;
    private String account;
    private VarFinal mVarFinal = new VarFinal();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tai_khoan, container, false);
        buttonMainToLogin = view.findViewById(R.id.buttonMainToLogin);
        mSharedPreferences = new MangaZSharedPreferences(getActivity());
        account = mSharedPreferences.GetStringValue(mVarFinal.ACCOUNT);
        if (!account.equals(mVarFinal.TXT_NULL)) {
            buttonMainToLogin.setText("Đăng xuất");
        } else {
            buttonMainToLogin.setText("Đăng Nhập");
        }
        buttonMainToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!account.equals(mVarFinal.TXT_NULL)) {
                    mSharedPreferences.putStringValue(mVarFinal.ACCOUNT, mVarFinal.TXT_NULL);
                    mSharedPreferences.putStringValue(mVarFinal.PASSWORD, mVarFinal.TXT_NULL);
                }
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        account = mSharedPreferences.GetStringValue(mVarFinal.ACCOUNT);
        if (!account.equals(mVarFinal.TXT_NULL)) {
            buttonMainToLogin.setText("Đăng xuất");
        } else {
            buttonMainToLogin.setText("Đăng Nhập");
        }
    }
}