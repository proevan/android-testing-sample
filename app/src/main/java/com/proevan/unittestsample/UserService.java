package com.proevan.unittestsample;

import android.content.Context;
import android.content.SharedPreferences;

import static com.proevan.unittestsample.UserApiService.*;

public class UserService {

    public static final String SHARED_PREF_NAME = "MY_SHARED_PREF";
    private static final String SHARED_PREF_KEY_IS_LOGIN = "SHARED_PREF_KEY_IS_LOGIN";
    private UserApiService mUserApiService;
    private SharedPreferences mSharedPreferences;

    public UserService(Context appContext, UserApiService userApiService) {
        mUserApiService = userApiService;
        mSharedPreferences = appContext.getSharedPreferences(SHARED_PREF_NAME, 0);
    }

    public boolean isLogin() {
        return loadLoginStatus();
    }

    public void login(String email, String password, final Callback callback) {
        mUserApiService.login(email, password, new Callback() {
            @Override
            public void onSuccess() {
                saveLoginStatus(true);
                callback.onSuccess();
            }

            @Override
            public void onFailure() {
                callback.onSuccess();
            }
        });
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
