package com.ucr.fofis.geoapp;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.*;

 /** Clase para probar la funcionalidad del audi introductorio
 * Revisa que se reproduzca el audi ointroductorio la primera ve que se abre el app y cuando se pareta el botón para ello
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class IntroductionSoundTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivity_soundTest() {
        try {

            assertTrue(mActivityTestRule.getActivity().introMediaPlayer.isPlaying());
            Thread.sleep(21000);
            ViewInteraction appCompatImageButton = onView(
                    allOf(withContentDescription("Open navigation drawer"),
                            withParent(withId(R.id.toolbar)),
                            isDisplayed()));
            appCompatImageButton.perform(click());
            Thread.sleep(1000);

            ViewInteraction appCompatCheckedTextView = onView(
                    allOf(withId(R.id.design_menu_item_text), withText("Escuchar audio introductorio"), isDisplayed()));
            appCompatCheckedTextView.perform(click());
            Thread.sleep(2000);
            assertTrue(mActivityTestRule.getActivity().introMediaPlayer.isPlaying());
        }catch(InterruptedException e){

        }
    }

}
