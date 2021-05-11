/*
 * Coder: Nguyen Van An
 * Date: 8-5-2021
 * Content: Xử lý trang đăng ký
 *
 *
 * */
package com.example.mangaz.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mangaz.R;
import com.example.mangaz.User.Users;

public class SignUpActivity extends AppCompatActivity implements SignUpInterface {
    private Button buttonSignUp;
    private TextView textViewValidSignUp;
    private EditText editTextUserName, editTextPassword, editTextPassword2, editTextAge, editTextNickName;
    private SignUpPresenter mSignUpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        // Anh xa
        buttonSignUp = findViewById(R.id.buttonSignUp);
        editTextUserName = findViewById(R.id.editTextUserNameOnSignUp);
        editTextPassword = findViewById(R.id.editTextPasswordOnSignUp);
        editTextPassword2 = findViewById(R.id.editTextPassword2OnSignUp);
        editTextNickName = findViewById(R.id.editTextNickNameOnSignUp);
        editTextAge = findViewById(R.id.editTextAgeOnSignUp);
        textViewValidSignUp = findViewById(R.id.textViewValidSignUp);
        mSignUpPresenter = new SignUpPresenter(this, this);

        //
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSignUp();
            }
        });
    }

    private void clickSignUp() {
        String strUserName = editTextUserName.getText().toString().trim();
        String strPassword = editTextPassword.getText().toString().trim();
        String strPassword2 = editTextPassword2.getText().toString().trim();
        String strNickName = editTextNickName.getText().toString().trim();
        Integer strAge = Integer.parseInt(editTextAge.getText().toString().trim());
        if (strPassword.equals(strPassword2)) {
            Users user = new Users(strUserName, strPassword, strNickName, strAge);
            if (mSignUpPresenter.SignUp(user)){
                Toast.makeText(this, "Tạo tài khoản thành công!", Toast.LENGTH_SHORT).show();
                this.finish();
            }
        } else {
            SignUpError("Các mật khẩu đã nhập không khớp");
        }
    }

    @Override
    public void SignUpError(String strError) {
        textViewValidSignUp.setText(strError);
    }
}