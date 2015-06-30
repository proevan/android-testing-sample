package com.proevan.unittestsample;

public class UserApiService {

    public void login(String email, String password, Callback callback) {
        // todo: send login request to server...
    }

    public interface Callback {
        void onSuccess();
        void onFailure();
    }
}
