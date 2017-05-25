//package com.ucr.fofis.geoapp;
//
//
//import android.support.test.espresso.ViewInteraction;
//import android.support.test.rule.ActivityTestRule;
//import android.support.test.runner.AndroidJUnit4;
//import android.test.suitebuilder.annotation.LargeTest;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.ViewParent;
//
//import org.hamcrest.Description;
//import org.hamcrest.Matcher;
//import org.hamcrest.TypeSafeMatcher;
//import org.hamcrest.core.IsInstanceOf;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import static android.support.test.espresso.Espresso.onView;
//import static android.support.test.espresso.Espresso.pressBack;
//import static android.support.test.espresso.action.ViewActions.click;
//import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
//import static android.support.test.espresso.assertion.ViewAssertions.matches;
//import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static android.support.test.espresso.matcher.ViewMatchers.withId;
//import static android.support.test.espresso.matcher.ViewMatchers.withParent;
//import static android.support.test.espresso.matcher.ViewMatchers.withText;
//import static org.hamcrest.Matchers.allOf;
//
//@LargeTest
//@RunWith(AndroidJUnit4.class)
//public class CameraActivityTest {
//
//    @Rule
//    public ActivityTestRule<SplashActivity> mActivityTestRule = new ActivityTestRule<>(SplashActivity.class);
//
//    @Test
//    public void cameraActivityTest() {
//
//        ViewInteraction appCompatButton = onView(
//                allOf(withId(R.id.btnStart), withText("Iniciar Viaje"),
//                        withParent(withId(R.id.button)),
//                        isDisplayed()));
//        appCompatButton.perform(click());
//
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        try {
//        ViewInteraction relativeLayout = onView(
//                allOf(withId(R.id.exit),
//                        withParent(allOf(withId(R.id.recommendation_dialog),
//                                withParent(withId(android.R.id.content)))),
//                        isDisplayed()));
//        relativeLayout.perform(click());
//        } catch (Exception e) {
//            pressBack();
//        }
//
//        ViewInteraction floatingActionButton3 = onView(
//                allOf(withId(R.id.fab),
//                        withParent(withId(R.id.fab_speed_dial)),
//                        isDisplayed()));
//        floatingActionButton3.perform(click());
//
//        //+---------->AppCompatTextView{id=2131689719, res-name=title_view, visibility=VISIBLE, width=124, height=49, has-focus=false, has-focusable=false, has-window-focus=true, is-clickable=false, is-enabled=true, is-focused=false, is-focusable=false, is-layout-requested=false, is-selected=false, root-is-layout-requested=false, has-input-connection=false, x=44.0, y=38.0, text=Cámara, input-type=0, ime-target=false, has-links=false}
//
//
//        ViewInteraction cardView = onView(withText("Cámara"));
//        cardView.check(matches(isDisplayed()));
//
//
//
//        try {
//            Thread.sleep(30000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        cardView.perform(click());
//        ViewInteraction frameLayout = onView(
//                allOf(withId(R.id.camera_view),
//                        isDisplayed()));
//        frameLayout.check(matches(isDisplayed()));
//
//        ViewInteraction imageView = onView(
//                allOf(withId(R.id.arrow),
//                        childAtPosition(
//                                childAtPosition(
//                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
//                                        0),
//                                1),
//                        isDisplayed()));
//        imageView.check(matches(isDisplayed()));
//
//        pressBack();
//
//        imageView.check(doesNotExist());
//
//    }
//
//    private static Matcher<View> childAtPosition(
//            final Matcher<View> parentMatcher, final int position) {
//
//        return new TypeSafeMatcher<View>() {
//            @Override
//            public void describeTo(Description description) {
//                description.appendText("Child at position " + position + " in parent ");
//                parentMatcher.describeTo(description);
//            }
//
//            @Override
//            public boolean matchesSafely(View view) {
//                ViewParent parent = view.getParent();
//                return parent instanceof ViewGroup && parentMatcher.matches(parent)
//                        && view.equals(((ViewGroup) parent).getChildAt(position));
//            }
//        };
//    }
//}
