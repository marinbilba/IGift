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
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
/**
 * This test will perform the following actions:
 * Log in with email and password
 * Create a new wish list
 * Add a wish list item to the created wish list
 * Assert if the created wish item is displayed correctly
 * Log off from the app
 */
public class MyListsTest {

    @Rule
    public ActivityTestRule<SignInActivity> mActivityTestRule = new ActivityTestRule<>(SignInActivity.class);

    @Test
    public void myListsTest() {
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
        onView(withId(R.id.addListFloatingActionButton)).perform(click());

        onView(withId(R.id.inputTextCreateNewListName)).perform(replaceText("TestList"), closeSoftKeyboard());
        onView(withId(R.id.createNewListButton)).perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.list_name_raw_text_view), withText("TestList"),
                        withParent(allOf(withId(R.id.my_raw_ConstraintLayout),
                                withParent(withId(R.id.card_view)))),
                        isDisplayed()));
        textView.check(matches(withText("TestList")));

        ViewInteraction constraintLayout = onView(
                allOf(withId(R.id.my_raw_ConstraintLayout),
                        childAtPosition(
                                allOf(withId(R.id.card_view),
                                        childAtPosition(
                                                withId(R.id.wishListRecyclerView),
                                                0)),
                                0),
                        isDisplayed()));
        constraintLayout.perform(click());

        ViewInteraction floatingActionButton2 = onView(
                allOf(withId(R.id.addItemFloatingActionButton),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                1),
                        isDisplayed()));
        floatingActionButton2.perform(click());

        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.itemName),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText4.perform(click());

        ViewInteraction textInputEditText5 = onView(
                allOf(withId(R.id.itemName),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText5.perform(replaceText("Phone"), closeSoftKeyboard());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.priceEditText),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("500"), closeSoftKeyboard());

        ViewInteraction textInputEditText6 = onView(
                allOf(withId(R.id.whereToBuyEditText),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                5),
                        isDisplayed()));
        textInputEditText6.perform(replaceText("Apple Store"), closeSoftKeyboard());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.createWishItemButton), withText("Add item"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                8),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction constraintLayout2 = onView(
                allOf(withId(R.id.my_raw_ConstraintLayout),
                        childAtPosition(
                                allOf(withId(R.id.card_view),
                                        childAtPosition(
                                                withId(R.id.wishItemsRecyclerView),
                                                0)),
                                0),
                        isDisplayed()));
        constraintLayout2.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.giftNameTextView), withText("Phone"),
                        withParent(withParent(withId(R.id.nav_host_fragment))),
                        isDisplayed()));
        textView2.check(matches(withText("Phone")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.priceTextView), withText("500.0"),
                        withParent(withParent(withId(R.id.nav_host_fragment))),
                        isDisplayed()));
        textView3.check(matches(withText("500.0")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.whereToBuyTextView), withText("Apple Store"),
                        withParent(withParent(withId(R.id.nav_host_fragment))),
                        isDisplayed()));
        textView4.check(matches(withText("Apple Store")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.descriptionTextView),
                        withParent(withParent(withId(R.id.nav_host_fragment))),
                        isDisplayed()));
        textView5.check(matches(withText("")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.descriptionTextView),
                        withParent(withParent(withId(R.id.nav_host_fragment))),
                        isDisplayed()));
        textView6.check(matches(withText("")));

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.navigation_connect), withContentDescription("Connect"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_navigation_view),
                                        0),
                                0),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

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
