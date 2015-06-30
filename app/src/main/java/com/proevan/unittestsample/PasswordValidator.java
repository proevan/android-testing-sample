package com.proevan.unittestsample;

public class PasswordValidator {

    public boolean checkEmailAndPassword(String email, String password) {
        return email.equals("admin") && password.equals("admin");
    }

}
