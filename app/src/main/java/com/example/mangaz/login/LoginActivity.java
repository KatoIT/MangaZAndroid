/*
 * Coder: Nguyen Van An
 * Date: 7-5-2021
 * Content: Xử lý đăng nhập
 *
 *
 * */
package com.example.mangaz.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mangaz.R;
import com.example.mangaz.signup.SignUpActivity;
import com.example.mangaz.User.Users;

public class LoginActivity extends AppCompatActivity implements LoginInterface {
    private Button buttonLogin, buttonLoginToSignUp;
    private EditText editTextUserName, editTextPassword;
    private TextView textViewValidLogin;
    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Anh xa
        editTextUserName = findViewById(R.id.editTextUserNameOnLogin);
        editTextPassword = findViewById(R.id.editTextPasswordOnLogin);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonLoginToSignUp = findViewById(R.id.buttonLoginToSignUp);
        textViewValidLogin = findViewById(R.id.textViewValidLogin);

        mLoginPresenter = new LoginPresenter(this, this);
        mLoginPresenter.CheckUserAdmin();// Thêm tài khoản admin cho lần cài đặt đầu tiên.

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickLogin();
            }
        });
        buttonLoginToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }

    private void clickLogin() {
        String strUserName = editTextUserName.getText().toString().trim();
        String strPassword = editTextPassword.getText().toString().trim();
        Users user = new Users(strUserName, strPassword);
        mLoginPresenter.Login(user);
    }

    @Override
    public void LoginError(String strError) {
        textViewValidLogin.setText("Tài khoản hoặc mật khẩu sai");
        if (!strError.equals("Not")) {
            Toast.makeText(this, "Error: " + strError, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void LoginSuccess(String txt) {
        textViewValidLogin.setText(txt);
    }

}