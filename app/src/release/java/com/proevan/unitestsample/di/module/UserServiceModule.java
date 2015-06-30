package com.proevan.unitestsample.di.module;

import android.content.Context;

import com.proevan.unittestsample.UserApiService;
import com.proevan.unittestsample.UserService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UserServiceModule {

    private Context mAppContext;

    public UserServiceModule(Context appContext) {
        mAppContext = appContext;
    }

    @Provides
    @Singleton
    UserService provideUserService(UserApiService userApiService){
        return new UserService(mAppContext, userApiService);
    }

    @Provides
    @Singleton
    UserApiService provideUserApiService(){
        return new UserApiService();
    }
}
