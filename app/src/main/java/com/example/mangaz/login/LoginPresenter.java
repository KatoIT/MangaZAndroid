/*
 * Coder: Nguyen Van An
 * Date: 7-5-2021
 * Content: Xử lý đăng nhập
 *
 *
 * */
package com.example.mangaz.login;

import android.content.Context;
import android.database.Cursor;

import com.example.mangaz.Database;
import com.example.mangaz.MD5;
import com.example.mangaz.MangaZSharedPreferences;
import com.example.mangaz.User.Users;
import com.example.mangaz.VarFinal;

public class LoginPresenter {
    private final LoginInterface mLoginInterface;
    private final Context mContext;
    private MD5 md5 = new MD5();
    private MangaZSharedPreferences mSharedPreferences;
    private VarFinal mVarFinal = new VarFinal();

    public LoginPresenter(LoginInterface mLoginInterface, Context mContext) {
        this.mLoginInterface = mLoginInterface;
        this.mContext = mContext;
    }

    public void Login(Users user) {
        if (!(user.ísValidUserName() && user.ísValidPassWord() && CheckUserLogin(user))) {
            mLoginInterface.LoginError("Not");
        }else {
            mLoginInterface.LoginSuccess("Đăng nhập thành công!");
        }
    }

    public void CheckUserAdmin() {
        Database db = new Database(mContext);
        Cursor cursor = db.GetData("SELECT * FROM Users WHERE UserName = 'admin'");
        if (cursor.getCount() == 0) {
            db.QueryData("INSERT INTO Users VALUES('admin','manAn1207*','Admin',21,0,'',NULL,1)");
        }
    }

    public Boolean CheckUserLogin(Users user) {
        try {
            Database db = new Database(mContext);
            Cursor cursor = db.GetData("SELECT UserName, Password  FROM Users WHERE UserName = '" + user.getUserName() + "'");
            cursor.moveToFirst();
            String strUserName = cursor.getString(0);
            String strPassword = cursor.getString(1);
            if (strUserName.equals(user.getUserName()) && strPassword.equals(user.getPassWord())){
                mSharedPreferences = new MangaZSharedPreferences(mContext);
                mSharedPreferences.putStringValue(mVarFinal.ACCOUNT, strUserName);
                mSharedPreferences.putStringValue(mVarFinal.PASSWORD, strPassword);
            }
            return strUserName.equals(user.getUserName()) && strPassword.equals(user.getPassWord());
        } catch (Exception e) {
            mLoginInterface.LoginError(e.getMessage());
            return false;
        }
    }

}
