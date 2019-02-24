package com.example.android.arinspect_test;


import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<>
            (MainActivity.class);

    @Before
    public void setUp() {
        mainActivityTestRule.getActivity();
    }

    @Test
    public void testFactsLoadedSuccess() {
        onView(withId(R.id.rv_view))
                .check(matches(hasDescendant(withText("Flag"))));


    }

    @Test
    public void testFactsLoadedError() {
        onView(withId(R.id.tv_error_message_display))
                .check(matches(withText("Something went wrong!")));
    }
}
