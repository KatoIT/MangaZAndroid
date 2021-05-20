/*
 * Coder: Nguyen Van An
 * Date: 8-5-2021
 * Content: Xử lý trang đăng ký
 *
 *
 * */
package com.example.mangaz.signup;

import android.content.Context;
import android.database.Cursor;

import com.example.mangaz.Database;
import com.example.mangaz.Model.User;

public class SignUpPresenter {
    private SignUpInterface mSignUpInterface;
    private final Context mContext;

    public SignUpPresenter(SignUpInterface mSignUpInterface, Context mContext) {
        this.mSignUpInterface = mSignUpInterface;
        this.mContext = mContext;
    }

    public Boolean SignUp(User user) {
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

    public Boolean CheckUserSignUp(User user) {
        Database db = new Database(mContext);
        Cursor cursor = db.GetData("SELECT *  FROM Users WHERE UserName = '" + user.getUserName() + "'");
        return (cursor.getCount() == 0);
    }
}
