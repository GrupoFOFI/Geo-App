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
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static com.ucr.fofis.geoapp.R.id.imageView;
import static org.hamcrest.Matchers.allOf;


/**
 * Clase para probar la funcionalidad del carrusel de imágenes
 * Revisa que exista el carrusel y que las imágenes cambien al hacer swipe
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class CarouselTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivity_carrouselTest() {

        ViewInteraction Title = onView(allOf(withId(R.id.introText)));
        Title.check(matches(isDisplayed()));

        ViewInteraction imageView = onView(
                allOf(withParent(allOf(withId(R.id.containerViewPager),
                childAtPosition(
                        withId(R.id.carouselView),
                        0))),
        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        SystemClock.sleep(3000);

        ViewInteraction carouselViewPager = onView(
                allOf(withId(R.id.containerViewPager),
                        withParent(allOf(withId(R.id.carouselView),
                                withParent(withId(R.id.layoutcarousel)))),
                        isDisplayed()));
        carouselViewPager.perform(swipeLeft());

        ViewInteraction imageView2 = onView(
                allOf(withParent(allOf(withId(R.id.containerViewPager),
                        childAtPosition(
                                withId(R.id.carouselView),
                                0))),
                        isDisplayed()));
        imageView2.check(matches(isDisplayed()));

        SystemClock.sleep(3000);
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
