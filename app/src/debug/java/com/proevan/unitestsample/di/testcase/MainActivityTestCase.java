package com.proevan.unitestsample.di.testcase;

import android.test.ActivityInstrumentationTestCase2;

import com.proevan.unittestsample.MainActivity;
import com.proevan.unittestsample.UserService;

import javax.inject.Inject;

public class MainActivityTestCase extends ActivityInstrumentationTestCase2<MainActivity> {

    @Inject
    protected UserService mMockUserService;

    public MainActivityTestCase() {
        super(MainActivity.class);
    }
}
