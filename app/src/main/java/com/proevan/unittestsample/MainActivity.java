package com.proevan.unittestsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText mEmailEditText;
    EditText mPasswordEditText;
    UserService mUserService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEmailEditText = (EditText) findViewById(R.id.email);
        mPasswordEditText = (EditText) findViewById(R.id.password);
        mUserService = new UserService(getApplicationContext(), new PasswordValidator());
        if (checkAutoLogin()) {
            goToSecondPage();
            finish();
        }
    }

    private boolean checkAutoLogin() {
        return mUserService.isLogin();
    }

    public void onEnterButtonClick(View view) {
        String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();
        boolean isLoginSuccess = mUserService.login(email, password);
        if (isLoginSuccess)
            goToSecondPage();
    }

    private void goToSecondPage() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}
