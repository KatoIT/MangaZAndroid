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
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mangaz.login.LoginActivity;

public class TaiKhoanFragment extends Fragment {
    private View view;
    private Button buttonMainToLogin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tai_khoan, container, false);
        buttonMainToLogin = view.findViewById(R.id.buttonMainToLogin);
        buttonMainToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}