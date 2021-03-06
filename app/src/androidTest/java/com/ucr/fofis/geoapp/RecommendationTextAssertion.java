package com.ucr.fofis.geoapp;


import android.os.SystemClock;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.ucr.fofis.dataaccess.database.Datos;

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

        int index = 0;

        SystemClock.sleep(3000);

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btnStart), withText("Iniciar Viaje"),
                        withParent(withId(R.id.button)),
                        isDisplayed()));
        appCompatButton.perform(click());

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ViewInteraction viewPager = onView(
                allOf(withId(R.id.recommendation_dialog_viewpager),
                        withParent(allOf(withId(R.id.fofi),
                                withParent(withId(R.id.recommendation_dialog)))),
                        isDisplayed()));

        ViewInteraction textView = null;
        for(index = 0;index < Datos.RECOMENDACIONES.size();index++) {
            textView =onView(
                    allOf(withId(R.id.recommendation_fragment_text),withText(Datos.RECOMENDACIONES.get(index).getTexto()),
                            childAtPosition(
                                    withParent(withId(R.id.recommendation_dialog_viewpager)),
                                    0),
                            isDisplayed()));
            textView.check(matches(withText(Datos.RECOMENDACIONES.get(index).getTexto())));
            viewPager.perform(swipeLeft());
        }

        SystemClock.sleep(3000);


        for(index = Datos.RECOMENDACIONES.size()-1;index >= 0; index--) {
            textView =onView(
                    allOf(withId(R.id.recommendation_fragment_text),withText(Datos.RECOMENDACIONES.get(index).getTexto()),
                            childAtPosition(
                                    withParent(withId(R.id.recommendation_dialog_viewpager)),
                                    0),
                            isDisplayed()));
            textView.check(matches(withText(Datos.RECOMENDACIONES.get(index).getTexto())));
            viewPager.perform(swipeRight());
            SystemClock.sleep(3000);

        }


        ViewInteraction relativeLayout = onView(
                allOf(withId(R.id.exit),
                        withParent(allOf(withId(R.id.recommendation_dialog),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        relativeLayout.perform(click());
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
