package com.proevan.unittestsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.proevan.unitestsample.di.conponent.UserServiceComponent;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    EditText mEmailEditText;
    EditText mPasswordEditText;

    @Inject
    UserService mUserService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserServiceComponent.Initializer.init(getApplicationContext()).inject(this);
        setContentView(R.layout.activity_main);
        mEmailEditText = (EditText) findViewById(R.id.email);
        mPasswordEditText = (EditText) findViewById(R.id.password);
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
        mUserService.login(email, password, new UserApiService.Callback() {
            @Override
            public void onSuccess() {
                goToSecondPage();
            }

            @Override
            public void onFailure() {

            }
        });
    }

    private void goToSecondPage() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}
