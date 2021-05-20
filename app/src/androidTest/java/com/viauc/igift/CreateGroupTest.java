package com.viauc.igift;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.viauc.igift.R;
import com.viauc.igift.ui.signin.SignInActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
/**
 * This test will perform the following actions:
 * Log in with email and password
 * Create a new group
 * Delete the created group
 * Log off from the app
 */
public class CreateGroupTest {

    @Rule
    public ActivityTestRule<SignInActivity> mActivityTestRule = new ActivityTestRule<>(SignInActivity.class);

    @Test
    public void createGroupsTest() {
        onView(withId(R.id.emailEditText)).perform(replaceText("maryn_777@mail.ru"), closeSoftKeyboard());
        onView(withId(R.id.signInPasswordEditText)).perform(replaceText("test123"), closeSoftKeyboard());

        ViewInteraction materialTextView = onView(
                allOf(withText("SIGNIN"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.linearLayout),
                                        1),
                                0),
                        isDisplayed()));
        materialTextView.perform(click());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.navigation_connect), withContentDescription("Connect"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_navigation_view),
                                        0),
                                0),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction tableRow = onView(
                allOf(withId(R.id.startGroupRow),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                2),
                        isDisplayed()));
        tableRow.perform(click());

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.inputTextCreateGroupName),
                        childAtPosition(
                                allOf(withId(R.id.create_group_fragment_container),
                                        childAtPosition(
                                                withId(R.id.nav_host_fragment),
                                                0)),
                                3),
                        isDisplayed()));
        textInputEditText3.perform(replaceText("TestGroup"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.createGroupButton), withText("Create Group"),
                        childAtPosition(
                                allOf(withId(R.id.create_group_fragment_container),
                                        childAtPosition(
                                                withId(R.id.nav_host_fragment),
                                                0)),
                                4),
                        isDisplayed()));
        materialButton.perform(click());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ViewInteraction cardView = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.expandableCreatedGroupsPlaceholder),
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0)),
                        0),
                        isDisplayed()));
        cardView.perform(click());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.deleteGroupImageView),
                        childAtPosition(
                                allOf(withId(R.id.groupsNameConstraintLayout),
                                        childAtPosition(
                                                withId(R.id.card_view),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withClassName(is("com.google.android.material.appbar.AppBarLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction navigationMenuItemView = onView(
                allOf(withId(R.id.nav_drawer_sign_out),
                        childAtPosition(
                                allOf(withId(R.id.design_navigation_view),
                                        childAtPosition(
                                                withId(R.id.navigation_drawer),
                                                0)),
                                5),
                        isDisplayed()));
        navigationMenuItemView.perform(click());
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
