package com.ucr.fofis.geoapp;


import android.os.SystemClock;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RecommendationTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {

        SystemClock.sleep(3000);

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        SystemClock.sleep(3000);

        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Ver recomendaciones"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        ViewInteraction relativeLayout = onView(
                allOf(withId(R.id.recommendation_dialog),
                        childAtPosition(
                                allOf(withId(android.R.id.content),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                                0)),
                                0),
                        isDisplayed()));
        relativeLayout.check(matches(isDisplayed()));

        ViewInteraction imageView = onView(
                allOf(withId(R.id.recommendation_fragment_image),
                        childAtPosition(
                                withParent(withId(R.id.recommendation_dialog_viewpager)),
                                1),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));


        ViewInteraction viewPager2 = onView(
                allOf(withId(R.id.recommendation_dialog_viewpager),
                        withParent(allOf(withId(R.id.fofi),
                                withParent(withId(R.id.recommendation_dialog)))),
                        isDisplayed()));

        SystemClock.sleep(3000);
        viewPager2.perform(swipeLeft());

        ViewInteraction relativeLayout2 = onView(
                allOf(withId(R.id.exit),
                        withParent(allOf(withId(R.id.recommendation_dialog),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        relativeLayout2.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.btnStart), withText("Iniciar Viaje"),
                        withParent(withId(R.id.button)),
                        isDisplayed()));
        appCompatButton2.perform(click());

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction imageView3 = onView(
                allOf(withId(R.id.recommendation_fragment_image),
                        childAtPosition(
                                withParent(withId(R.id.recommendation_dialog_viewpager)),
                                1),
                        isDisplayed()));
        imageView3.check(matches(isDisplayed()));

        ViewInteraction viewPager8 = onView(
                allOf(withId(R.id.recommendation_dialog_viewpager),
                        withParent(allOf(withId(R.id.fofi),
                                withParent(withId(R.id.recommendation_dialog)))),
                        isDisplayed()));
        viewPager8.perform(swipeLeft());

        ViewInteraction relativeLayout3 = onView(
                allOf(withId(R.id.exit),
                        withParent(allOf(withId(R.id.recommendation_dialog),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        relativeLayout3.perform(click());

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
