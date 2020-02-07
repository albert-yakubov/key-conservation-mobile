package com.stepasha.keyconservation


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SplashActivity::class.java)

    @Rule
    @JvmField
    var mGrantPermissionRule =
        GrantPermissionRule.grant(
            "android.permission.ACCESS_FINE_LOCATION"
        )

    @Test
    fun activityTest() {
        val textInputEditText = onView(
            allOf(
                childAtPosition(
                    childAtPosition(
                        withId(R.id.text_input_username),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText.perform(replaceText("conserve3"), closeSoftKeyboard())

        val textInputEditText2 = onView(
            allOf(
                childAtPosition(
                    childAtPosition(
                        withId(R.id.text_input_password),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText2.perform(replaceText("password"), closeSoftKeyboard())

        val appCompatButton = onView(
            allOf(
                withId(R.id.btn_login), withText("Login"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatButton.perform(click())

        val bottomNavigationItemView = onView(
            allOf(
                withId(R.id.navigation_profile),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.navigation),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView.perform(click())

        val appCompatImageButton = onView(
            allOf(
                withId(R.id.imagebutton_email),
                childAtPosition(
                    allOf(
                        withId(R.id.relativeLayout),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            2
                        )
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())

        val appCompatButton2 = onView(
            allOf(
                withId(R.id.view_buttonUpdate), withText("UPDATE"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatButton2.perform(click())

        val appCompatEditText = onView(
            allOf(
                withId(R.id.edittext_species), withText("Dogs and Cats"),
                childAtPosition(
                    allOf(
                        withId(R.id.relativeLayout3),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            3
                        )
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("Dogs and Cats, owls"))

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.edittext_species), withText("Dogs and Cats, owls"),
                childAtPosition(
                    allOf(
                        withId(R.id.relativeLayout3),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            3
                        )
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatEditText2.perform(closeSoftKeyboard())

        val appCompatImageButton2 = onView(
            allOf(
                withId(R.id.imagebutton_update_profile),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatImageButton2.perform(click())

        pressBack()

        pressBack()

        pressBack()

        val bottomNavigationItemView2 = onView(
            allOf(
                withId(R.id.navigation_map),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.navigation),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView2.perform(click())

        pressBack()

        val floatingActionButton = onView(
            allOf(
                withId(R.id.view_floatingbutton),
                childAtPosition(
                    allOf(
                        withId(R.id.cl_activity_main_parent),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        floatingActionButton.perform(click())

        val textInputEditText3 = onView(
            allOf(
                withId(R.id.imageView),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.view_textTitle),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText3.perform(replaceText("title"), closeSoftKeyboard())

        val textInputEditText4 = onView(
            allOf(
                childAtPosition(
                    childAtPosition(
                        withId(R.id.view_status),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText4.perform(replaceText("new"), closeSoftKeyboard())

        val textInputEditText5 = onView(
            allOf(
                childAtPosition(
                    childAtPosition(
                        withId(R.id.view_textLocation),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText5.perform(replaceText("location"), closeSoftKeyboard())

        val appCompatImageView = onView(
            allOf(
                withId(R.id.view_image),
                childAtPosition(
                    allOf(
                        withId(R.id.mAddPlaceLayout),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatImageView.perform(click())

        val appCompatImageView2 = onView(
            allOf(
                withId(R.id.view_image),
                childAtPosition(
                    allOf(
                        withId(R.id.mAddPlaceLayout),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatImageView2.perform(click())

        val textInputEditText6 = onView(
            allOf(
                childAtPosition(
                    childAtPosition(
                        withId(R.id.view_event_name),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText6.perform(click())

        val textInputEditText7 = onView(
            allOf(
                childAtPosition(
                    childAtPosition(
                        withId(R.id.view_event_name),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText7.perform(replaceText("event"), closeSoftKeyboard())

        val textInputEditText8 = onView(
            allOf(
                withId(R.id.view_event_image),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.view_event_image_layout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText8.perform(click())

        val textInputEditText9 = onView(
            allOf(
                withId(R.id.view_event_image),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.view_event_image_layout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText9.perform(replaceText("na"), closeSoftKeyboard())

        val textInputEditText10 = onView(
            allOf(
                childAtPosition(
                    childAtPosition(
                        withId(R.id.view_event_description),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText10.perform(replaceText("descript"), closeSoftKeyboard())

        val appCompatButton3 = onView(
            allOf(
                withId(R.id.btn_property_add), withText("Post Campaign"),
                childAtPosition(
                    allOf(
                        withId(R.id.mAddPlaceLayout),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    11
                ),
                isDisplayed()
            )
        )
        appCompatButton3.perform(click())

        pressBack()

        pressBack()

        pressBack()

        pressBack()
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
