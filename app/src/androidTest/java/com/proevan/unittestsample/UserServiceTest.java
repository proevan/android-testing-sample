package com.proevan.unittestsample;

import android.test.AndroidTestCase;

import org.mockito.Matchers;

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
        PasswordValidator validator = mock(PasswordValidator.class);
        when(validator.checkEmailAndPassword(Matchers.anyString(), Matchers.anyString()))
                .thenReturn(true);
        UserService userService = new UserService(getContext(), validator);

        // act
        userService.login("whateverEmail", "whateverPassword");

        // assert
        assertTrue(userService.isLogin());
    }

    public void testLoginFail() throws Exception {
        // arrange
        PasswordValidator validator = mock(PasswordValidator.class);
        when(validator.checkEmailAndPassword(Matchers.anyString(), Matchers.anyString()))
                .thenReturn(false);
        UserService userService = new UserService(getContext(), validator);

        // act
        userService.login("whateverEmail", "whateverPassword");

        // assert
        assertFalse(userService.isLogin());
    }

}