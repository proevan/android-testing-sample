package com.proevan.unittestsample;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;

import com.proevan.unitestsample.di.conponent.UserServiceComponent;
import com.proevan.unitestsample.di.testcase.MainActivityTestCase;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;

public class MainActivityTest extends MainActivityTestCase {

    public void setUp() throws Exception {
        super.setUp();
        System.setProperty("dexmaker.dexcache", getInstrumentation().getTargetContext().getCacheDir().getPath());
        clearSharedPref();
        getActivity();
        UserServiceComponent.Initializer.getInstance().inject(this);
    }

    private void clearSharedPref() {
        getInstrumentation().getTargetContext().getSharedPreferences(UserService.SHARED_PREF_NAME, 0)
                .edit().clear().commit();
    }

    public void testLayout() throws Exception {
        // arrange

        // act

        // assert
        onView(withId(R.id.email))
                .check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.password))
                .check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.enter_button))
                .check(ViewAssertions.matches(isDisplayed()));
    }

    public void testLoginSuccess() throws Exception {
        // arrange
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                UserApiService.Callback callback = (UserApiService.Callback) invocation.getArguments()[2];
                callback.onSuccess();
                return null;
            }
        }).when(mMockUserService).login(anyString(), anyString(), any(UserApiService.Callback.class));

        // act
        onView(withId(R.id.email))
                .perform(ViewActions.typeText("whateverEmail"));
        onView(withId(R.id.password))
                .perform(ViewActions.typeText("whateverPassword"));
        onView(withId(R.id.enter_button))
                .perform(ViewActions.click());

        // assert
        onView(withText("Second Page"))
                .check(ViewAssertions.matches(isDisplayed()));
    }
}