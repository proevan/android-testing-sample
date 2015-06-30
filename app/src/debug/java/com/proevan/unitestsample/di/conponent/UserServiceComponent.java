package com.proevan.unitestsample.di.conponent;

import android.content.Context;

import com.proevan.unitestsample.di.module.UserServiceModule;
import com.proevan.unittestsample.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        UserServiceModule.class
})
public interface UserServiceComponent {

    void inject(MainActivity activity);

    class Initializer {

        public static UserServiceComponent init(Context appContext) {
            return DaggerUserServiceComponent.builder()
                    .userServiceModule(new UserServiceModule(appContext))
                    .build();
        }
    }
}
