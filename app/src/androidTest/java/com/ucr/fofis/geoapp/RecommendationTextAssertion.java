package com.ucr.fofis.geoapp;


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
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RecommendationTextAssertion {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void recommendationTextAssertion() {
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

        ViewInteraction textView = onView(
                allOf(withId(R.id.recommendation_fragment_text), withText("En todo momento del viaje se debe de utilizar el chaleco salvavidas."),
                        childAtPosition(
                                withParent(withId(R.id.recommendation_dialog_viewpager)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("En todo momento del viaje se debe de utilizar el chaleco salvavidas.")));

        ViewInteraction viewPager = onView(
                allOf(withId(R.id.recommendation_dialog_viewpager),
                        withParent(allOf(withId(R.id.fofi),
                                withParent(withId(R.id.recommendation_dialog)))),
                        isDisplayed()));
        viewPager.perform(swipeLeft());

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.recommendation_fragment_text), withText("Siempre acatar las instrucciones del capit?n."),
                        childAtPosition(
                                withParent(withId(R.id.recommendation_dialog_viewpager)),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("Siempre acatar las instrucciones del capitán.")));

        ViewInteraction viewPager2 = onView(
                allOf(withId(R.id.recommendation_dialog_viewpager),
                        withParent(allOf(withId(R.id.fofi),
                                withParent(withId(R.id.recommendation_dialog)))),
                        isDisplayed()));
        viewPager2.perform(swipeLeft());

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.recommendation_fragment_text), withText("?Prot?jase del sol! Use protector solar"),
                        childAtPosition(
                                withParent(withId(R.id.recommendation_dialog_viewpager)),
                                0),
                        isDisplayed()));
        textView3.check(matches(withText("¡Protéjase del sol! Use protector solar")));

        ViewInteraction viewPager3 = onView(
                allOf(withId(R.id.recommendation_dialog_viewpager),
                        withParent(allOf(withId(R.id.fofi),
                                withParent(withId(R.id.recommendation_dialog)))),
                        isDisplayed()));
        viewPager3.perform(swipeLeft());

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.recommendation_fragment_text), withText("?Prot?jase del sol! Use sombrero"),
                        childAtPosition(
                                withParent(withId(R.id.recommendation_dialog_viewpager)),
                                0),
                        isDisplayed()));
        textView4.check(matches(withText("¡Protéjase del sol! Use sombrero")));

        ViewInteraction viewPager4 = onView(
                allOf(withId(R.id.recommendation_dialog_viewpager),
                        withParent(allOf(withId(R.id.fofi),
                                withParent(withId(R.id.recommendation_dialog)))),
                        isDisplayed()));
        viewPager4.perform(swipeRight());

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.recommendation_fragment_text), withText("?Prot?jase del sol! Use protector solar"),
                        childAtPosition(
                                withParent(withId(R.id.recommendation_dialog_viewpager)),
                                0),
                        isDisplayed()));
        textView5.check(matches(withText("¡Protéjase del sol! Use protector solar")));

        ViewInteraction imageView = onView(
                allOf(withId(R.id.recommendation_fragment_image),
                        childAtPosition(
                                withParent(withId(R.id.recommendation_dialog_viewpager)),
                                1),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction viewPager5 = onView(
                allOf(withId(R.id.recommendation_dialog_viewpager),
                        withParent(allOf(withId(R.id.fofi),
                                withParent(withId(R.id.recommendation_dialog)))),
                        isDisplayed()));
        viewPager5.perform(swipeRight());

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.recommendation_fragment_text), withText("Siempre acatar las instrucciones del capit?n."),
                        childAtPosition(
                                withParent(withId(R.id.recommendation_dialog_viewpager)),
                                0),
                        isDisplayed()));
        textView6.check(matches(withText("Siempre acatar las instrucciones del capitán.")));

        ViewInteraction viewPager6 = onView(
                allOf(withId(R.id.recommendation_dialog_viewpager),
                        withParent(allOf(withId(R.id.fofi),
                                withParent(withId(R.id.recommendation_dialog)))),
                        isDisplayed()));
        viewPager6.perform(swipeRight());

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.recommendation_fragment_text), withText("En todo momento del viaje se debe de utilizar el chaleco salvavidas."),
                        childAtPosition(
                                withParent(withId(R.id.recommendation_dialog_viewpager)),
                                0),
                        isDisplayed()));
        textView7.check(matches(withText("En todo momento del viaje se debe de utilizar el chaleco salvavidas.")));

        ViewInteraction relativeLayout = onView(
                allOf(withId(R.id.exit),
                        withParent(allOf(withId(R.id.recommendation_dialog),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        relativeLayout.perform(click());

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
