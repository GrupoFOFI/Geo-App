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
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class GeofenceTest {

    @Rule
    public ActivityTestRule<SplashActivity> mActivityTestRule = new ActivityTestRule<>(SplashActivity.class);

    @Test
    public void geofenceTest() {

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.introText), withText("La isla Bolaños y los lugares cercanos permitirán una apreciación de rocas, depositadas en los taludes continentales, emergidos y visibles en superficie por diversos procesos tectónicos y de erosión. En esta ruta se identificarán rocas de hasta 40 millones de años, a través de 24 sitios de interés patrimonial."),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("La isla Bolaños y los lugares cercanos permitirán una apreciación de rocas, depositadas en los taludes continentales, emergidos y visibles en superficie por diversos procesos tectónicos y de erosión. En esta ruta se identificarán rocas de hasta 40 millones de años, a través de 24 sitios de interés patrimonial.")));

        ViewInteraction button = onView(
                allOf(withId(R.id.btnStart),
                        childAtPosition(
                                allOf(withId(R.id.button),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                1)),
                                0),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        SystemClock.sleep(3000);

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btnStart), withText("Iniciar Viaje"),
                        withParent(withId(R.id.button)),
                        isDisplayed()));
        appCompatButton.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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

        ViewInteraction relativeLayout2 = onView(
                allOf(withId(R.id.exit),
                        withParent(allOf(withId(R.id.recommendation_dialog),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        relativeLayout2.perform(click());

        SystemClock.sleep(3000);

        ViewInteraction imageButton = onView(
                allOf(withId(R.id.fab),
                        childAtPosition(
                                allOf(withId(R.id.fab_speed_dial),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                                2)),
                                1),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.fab),
                        withParent(withId(R.id.fab_speed_dial)),
                        isDisplayed()));
        floatingActionButton.perform(click());

        SystemClock.sleep(3000);

        ViewInteraction linearLayout = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.menu_items_layout),
                                childAtPosition(
                                        withId(R.id.fab_speed_dial),
                                        0)),
                        0),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));

        ViewInteraction linearLayout3 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.menu_items_layout),
                                childAtPosition(
                                        withId(R.id.fab_speed_dial),
                                        0)),
                        1),
                        isDisplayed()));
        linearLayout3.check(matches(isDisplayed()));

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
