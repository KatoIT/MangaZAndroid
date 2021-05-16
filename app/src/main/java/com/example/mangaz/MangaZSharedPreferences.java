package com.example.mangaz;

import android.content.Context;
import android.content.SharedPreferences;

public class MangaZSharedPreferences {
    private static final String MANGAZ_SHARED_PREFERENCES = "MANGAZ_SHARED_PREFERENCES";
    private Context mContext;
    private VarFinal mVarFinal = new VarFinal();

    public MangaZSharedPreferences(Context mContext) {
        this.mContext = mContext;
    }

    public void putBooleanValue(String key, boolean value) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MANGAZ_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean GetBooleanValue(String key) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MANGAZ_SHARED_PREFERENCES, 0);
        return sharedPreferences.getBoolean(key, false);
    }

    public void putStringValue(String key, String value) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MANGAZ_SHARED_PREFERENCES, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String GetStringValue(String key) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MANGAZ_SHARED_PREFERENCES, 0);
        return sharedPreferences.getString(key, mVarFinal.TXT_NULL);
    }
}
