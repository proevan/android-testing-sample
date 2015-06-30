package com.proevan.unittestsample;

import android.test.AndroidTestCase;

import org.mockito.Matchers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static com.proevan.unittestsample.UserApiService.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest extends AndroidTestCase {

    public void setUp() throws Exception {
        super.setUp();
        System.setProperty("dexmaker.dexcache", getContext().getCacheDir().getPath());
        clearSharedPref();
    }

    private void clearSharedPref() {
        getContext().getSharedPreferences(UserService.SHARED_PREF_NAME, 0)
                .edit().clear().commit();
    }

    public void testLoginSuccess() throws Exception {
        // arrange
        UserApiService userApiService = mock(UserApiService.class);
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Callback callback = (Callback) invocation.getArguments()[2];
                callback.onSuccess();
                return null;
            }
        }).when(userApiService).login(anyString(), anyString(), any(Callback.class));
        UserService userService = new UserService(getContext(), userApiService);

        // act
        userService.login("whateverEmail", "whateverPassword", mock(Callback.class));

        // assert
        assertTrue(userService.isLogin());
    }

    public void testLoginFailure() throws Exception {
        // arrange
        UserApiService userApiService = mock(UserApiService.class);
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Callback callback = (Callback) invocation.getArguments()[2];
                callback.onFailure();
                return null;
            }
        }).when(userApiService).login(anyString(), anyString(), any(Callback.class));
        UserService userService = new UserService(getContext(), userApiService);

        // act
        userService.login("whateverEmail", "whateverPassword", mock(Callback.class));

        // assert
        assertFalse(userService.isLogin());
    }

}