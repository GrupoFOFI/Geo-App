package com.ucr.fofis.geoapp;


import android.app.Instrumentation;
import android.os.SystemClock;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.CoordinatesProvider;
import android.support.test.espresso.action.GeneralClickAction;
import android.support.test.espresso.action.Press;
import android.support.test.espresso.action.Tap;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MapActivityTest {

    @Rule
    public ActivityTestRule<MapActivity> mActivityTestRule = new ActivityTestRule<>(MapActivity.class);

    @Test
    public void mainActivityTest() {
       // mActivityTestRule.getActivity().onCreate(null);
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int initialZoom = mActivityTestRule.getActivity().mMapView.getZoomLevel();
        try {
            pinch();
        }catch (Exception e){
            Log.d("test","pinch failed");
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int newZoom = mActivityTestRule.getActivity().mMapView.getZoomLevel();
        Assert.assertNotEquals(initialZoom,newZoom);

        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.layoutMap),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.fab),
                        withParent(withId(R.id.fab_speed_dial)),
                        isDisplayed()));
        floatingActionButton.perform(click());

        clickXY(100,200);



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

    public static ViewAction clickXY(final int x, final int y){
        return new GeneralClickAction(
                Tap.SINGLE,
                new CoordinatesProvider() {
                    @Override
                    public float[] calculateCoordinates(View view) {

                        final int[] screenPos = new int[2];
                        view.getLocationOnScreen(screenPos);

                        final float screenX = screenPos[0] + x;
                        final float screenY = screenPos[1] + y;
                        float[] coordinates = {screenX, screenY};

                        return coordinates;
                    }
                },
                Press.FINGER);
    }

    private void pinch()throws Exception {
            long downTime = SystemClock.uptimeMillis();
// event time MUST be retrieved only by this way!
            long eventTime = SystemClock.uptimeMillis();
            Instrumentation inst = getInstrumentation();
// Just init your variables, or create your own coords logic 🙂
            float xStart = 200;
            float yStart = 200;
// simulating thick finger touch
            float x0 = 205;
            float y0 = 205;
            float x1 = 205;
            float y1 = 208;
            float x2 = 235;
            float y2 = 215;
// move finger to bottom and right
// increment previous coords
            float x3 = 245;
            float y3 = 255;
            float x4 = 247;
            float y4 = 275;
            float x5 = 252;
            float y5 = 300;
// proceed one more movement to bottom and left
            float x6 = 230;
            float y6 = 320;
            float x7 = 200;
            float y7 = 357;
            float x8 = 180;
            float y8 = 380;
            float x9 = 160;
            float y9 = 400;
// release finger, logically to use coords from last movent
            float x10 = 160;
            float y10 = 400;
            try {
// sending event – finger touched the screen
                MotionEvent event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_DOWN, xStart, yStart, 0);
                inst.sendPointerSync(event);
// sending events – finger is moving over the screen
                eventTime = SystemClock.uptimeMillis();
                event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_MOVE, x0, y0, 0);
                inst.sendPointerSync(event);
                event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_MOVE, x1, y1, 0);
                inst.sendPointerSync(event);
                event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_MOVE, x2, y2, 0);
                inst.sendPointerSync(event);
// simulating pause by incrementing eventTime with 1 second
// finger is still touching the screen
                eventTime = SystemClock.uptimeMillis() + 1000;
// moving finger across the screen
                event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_MOVE, x3, y3, 0);
                inst.sendPointerSync(event);
                event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_MOVE, x4, y4, 0);
                inst.sendPointerSync(event);
                event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_MOVE, x5, y5, 0);
                inst.sendPointerSync(event);
                event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_UP, x6, y6, 0);
// simulating one more pause
                eventTime = SystemClock.uptimeMillis() + 1000;
// moving finger again
                event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_MOVE, x7, y7, 0);
                inst.sendPointerSync(event);
                event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_MOVE, x8, y8, 0);
                inst.sendPointerSync(event);
                event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_MOVE, x9, y9, 0);
                inst.sendPointerSync(event);
// release finger, gesture is finished
                event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_UP, x10, y10, 0);
                inst.sendPointerSync(event);
            } catch (Exception ignored) {
// Handle exceptions if necessary
            }
        }

}
