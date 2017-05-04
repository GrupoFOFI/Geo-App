package com.ucr.fofis.geoapp;


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

    /**
     *
     */
    @Test
    public void recommendationTest() {
        try {
            long millis = 1000; //pausa entre interacciones

            ViewInteraction appCompatImageButton = onView(
                    allOf(withContentDescription("Open navigation drawer"),
                            withParent(withId(R.id.toolbar)),
                            isDisplayed()));
            appCompatImageButton.perform(click()); //abrir navigationview
            Thread.sleep(millis);

            ViewInteraction appCompatCheckedTextView = onView(
                    allOf(withId(R.id.design_menu_item_text), withText("Ver recomendaciones"), isDisplayed()));
            appCompatCheckedTextView.perform(click()); //selecionar recomendaciones
            Thread.sleep(millis);

            ViewInteraction linearLayout = onView(
                    allOf(withParent(allOf(withId(R.id.recommendation_dialog_viewpager),
                            childAtPosition(
                                    withId(R.id.fofi),
                                    1))),
                            isDisplayed()));
            linearLayout.check(matches(isDisplayed())); //checkea que se abre diálogo
            Thread.sleep(millis);
            int index = 0;

            ViewInteraction textView = onView(
                    allOf(withId(R.id.recommendation_fragment_text),
                            childAtPosition(
                                    withParent(withId(R.id.recommendation_dialog_viewpager)),
                                    0),
                            isDisplayed()));
            textView.check(matches(withText(Datos.RECOMENDACIONES.get(index).getTexto()))); //chequea que el texto es el correcto
            Thread.sleep(millis);
            ViewInteraction viewPager = onView(
                    allOf(withId(R.id.recommendation_dialog_viewpager),
                            withParent(allOf(withId(R.id.fofi),
                                    withParent(withId(R.id.recommendation_dialog)))),
                            isDisplayed()));
            viewPager.perform(swipeLeft()); //hace swipe a una nueva recomendacion
            index++;
            Thread.sleep(millis);
            ViewInteraction textView2 = onView(
                    allOf(withId(R.id.recommendation_fragment_text),
                            childAtPosition(
                                    withParent(withId(R.id.recommendation_dialog_viewpager)),
                                    0),
                            isDisplayed()));
            textView2.check(matches(withText(Datos.RECOMENDACIONES.get(index).getTexto()))); //chequea que el texto es el correcto
            Thread.sleep(millis);
        }catch(Exception e){

        }
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
