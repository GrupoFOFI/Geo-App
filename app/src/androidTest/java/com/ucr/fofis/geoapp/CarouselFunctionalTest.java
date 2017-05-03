package com.ucr.fofis.geoapp;

import android.support.test.espresso.action.Swipe;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.synnapps.carouselview.CarouselView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.R.attr.tag;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Usuario on 29/04/2017.
 */

@RunWith(AndroidJUnit4.class)

//Prueba funcional de carrusel
public class CarouselFunctionalTest {

    private int swipes = 7;//Cantidad de swipes a probrar

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void changeText_sameActivity() {
        RandomSwipe();//Prueba de swipes aleatorios en el carrusel

        int cont = 0;//Prueba de swipes a la izquierda en el carrusel
        while( cont++ < swipes) {
            onView(withId(R.id.carouselView)).perform(swipeRight());
        }

        cont = 0;//Prueba de swipes a la derecha en el carrusel
        while( cont++ < swipes) {
            onView(withId(R.id.carouselView)).perform(swipeLeft());
        }
    }

    //Se hace swipe aleatoriamente a la izquierda o derecha
    public void RandomSwipe(){
        int cont = 0;
        while( cont++ < swipes){
            double rand;
            if( (rand = Math.random()) < 0.5)
                onView(withId(R.id.carouselView)).perform(swipeRight());
            else
                onView(withId(R.id.carouselView)).perform(swipeLeft());
        }
    }
}
