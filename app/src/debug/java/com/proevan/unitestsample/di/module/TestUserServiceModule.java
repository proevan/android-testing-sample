package com.proevan.unitestsample.di.module;

import android.content.Context;

import com.proevan.unittestsample.UserApiService;
import com.proevan.unittestsample.UserService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

@Module
public class TestUserServiceModule {

    private Context mAppContext;
    private UserService mMockUserService;
    private UserApiService mMockUserApiService;

    public TestUserServiceModule(Context appContext) {
        mAppContext = appContext;
        mMockUserService = mock(UserService.class);
        mMockUserApiService = mock(UserApiService.class);
    }

    @Provides
    @Singleton
    UserService provideTestUserService(UserApiService userApiService){
        return mMockUserService;
    }

    @Provides
    @Singleton
    UserApiService provideTestUserApiService(){
        return mMockUserApiService;
    }
}
