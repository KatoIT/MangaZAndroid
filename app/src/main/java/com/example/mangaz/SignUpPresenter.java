package com.example.mangaz;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import com.example.mangaz.Model.Users;

public class SignUpPresenter {
    private SignUpInterface mSignUpInterface;
    private final Context mContext;

    public SignUpPresenter(SignUpInterface mSignUpInterface, Context mContext) {
        this.mSignUpInterface = mSignUpInterface;
        this.mContext = mContext;
    }

    public Boolean SignUp(Users user) {
        if (!user.ísValidUserName()) {
            mSignUpInterface.SignUpError("Tên đăng nhập có ít nhất 4 ký tự");
        } else {
            if (!user.ísValidPassWord()) {
                mSignUpInterface.SignUpError("Mật khẩu có ít nhất 6 ký tự");
            } else {
                if (!user.ísValidAge()) {
                    mSignUpInterface.SignUpError("Tuổi phải lớn hơn 0");
                } else {
                    if (!CheckUserSignUp(user)) {
                        mSignUpInterface.SignUpError("Tên đăng nhập đã tồn tại");
                    } else {
                        Database db = new Database(mContext);
                        db.QueryData("INSERT INTO Users VALUES('" + user.getUserName() + "','" + user.getPassWord() + "','" + user.getNickname() + "'," + user.getAge() + ",0,'',NULL,0)");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Boolean CheckUserSignUp(Users user) {
        Database db = new Database(mContext);
        Cursor cursor = db.GetData("SELECT *  FROM Users WHERE UserName = '" + user.getUserName() + "'");
        return (cursor.getCount() == 0);
    }
}
