package com.proevan.unitestsample.di.conponent;

import android.content.Context;

import com.proevan.unitestsample.di.module.TestUserServiceModule;
import com.proevan.unitestsample.di.testcase.MainActivityTestCase;
import com.proevan.unittestsample.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        TestUserServiceModule.class
})
public interface UserServiceComponent {

    void inject(MainActivity activity);

    void inject(MainActivityTestCase testCase);

    class Initializer {

        private static UserServiceComponent sInstance;

        public static UserServiceComponent init(Context appContext) {
            sInstance = DaggerUserServiceComponent.builder()
                    .testUserServiceModule(new TestUserServiceModule(appContext))
                    .build();
            return sInstance;
        }

        public static UserServiceComponent getInstance() {
            return sInstance;
        }
    }
}
