package com.proevan.unittestsample;

import android.content.Context;
import android.content.SharedPreferences;

public class UserService {

    public static final String SHARED_PREF_NAME = "MY_SHARED_PREF";
    private static final String SHARED_PREF_KEY_IS_LOGIN = "SHARED_PREF_KEY_IS_LOGIN";
    private PasswordValidator mPasswordValidator;
    private SharedPreferences mSharedPreferences;

    public UserService(Context appContext, PasswordValidator validator) {
        mPasswordValidator = validator;
        mSharedPreferences = appContext.getSharedPreferences(SHARED_PREF_NAME, 0);
    }

    public boolean isLogin() {
        return loadLoginStatus();
    }

    public boolean login(String email, String password) {
        if (mPasswordValidator.checkEmailAndPassword(email, password)) {
            saveLoginStatus(true);
            return true;
        } else {
            return false;
        }
    }

    private boolean loadLoginStatus() {
        return mSharedPreferences.getBoolean(SHARED_PREF_KEY_IS_LOGIN, false);
    }

    private void saveLoginStatus(boolean isLogin) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(SHARED_PREF_KEY_IS_LOGIN, isLogin);
        editor.commit();
    }
}
