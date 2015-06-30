package com.proevan.unittestsample;

import android.os.Handler;

public class UserApiService {

    public void login(String email, String password, Callback callback) {
        if (checkEmailAndPassword(email, password))
            wait2SecondsAndRespondSuccess(callback);
    }

    private boolean checkEmailAndPassword(String email, String password) {
        return email.equals("admin") && password.equals("admin");
    }

    private void wait2SecondsAndRespondSuccess(final Callback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess();
            }
        }, 2000);
    }

    public interface Callback {
        void onSuccess();
        void onFailure();
    }
}
