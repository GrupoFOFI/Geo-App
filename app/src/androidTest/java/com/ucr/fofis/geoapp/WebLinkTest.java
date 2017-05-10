package com.ucr.fofis.geoapp;


import android.content.Intent;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.ucr.fofis.dataaccess.database.Ruta;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;

/**
 * Clase para probar la funcionalidad del link a la página web.
 * Revisa que exista el intent que lleva a alguna actividad con el mismo url guardado en la capa de acceso a datos
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class WebLinkTest {

    private static final String PACKAGE_NAME="com.android.browser";

    @Rule
    public IntentsTestRule<MainActivity> mActivity = new IntentsTestRule<MainActivity>(MainActivity.class);

    @Test
    public void mainActivity_webLinkTest() {
        try {
            ViewInteraction fab = onView(
                    allOf(withId(R.id.fab),
                            isDisplayed()));
            fab.perform(click());

            intended(allOf(
                    hasAction(equalTo(Intent.ACTION_VIEW)),
                    hasData(Ruta.WEB_PAGE_URL)));

            pressBack();

            fab.check(matches((isDisplayed())));
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
