package com.esfimus.gbtesting.ui


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.esfimus.gbtesting.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class RecordedEspressoTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun recordedEspressoTest() {
        val materialButton = onView(
            allOf(
                withId(R.id.saveButton), withText("SAVE"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        val textView = onView(
            allOf(
                withId(R.id.inputCopy), withText("Empty"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Empty")))

        val textInputEditText = onView(
            allOf(
                withId(R.id.inputText),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.inputBox),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText.perform(replaceText("random text"), closeSoftKeyboard())

        val materialButton2 = onView(
            allOf(
                withId(R.id.saveButton), withText("SAVE"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        materialButton2.perform(click())

        val textView2 = onView(
            allOf(
                withId(R.id.inputCopy), withText("random text"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("random text")))

        val editText = onView(
            allOf(
                withId(R.id.inputText), withText("random text"),
                withParent(withParent(withId(R.id.inputBox))),
                isDisplayed()
            )
        )
        editText.check(matches(isDisplayed()))

        val button = onView(
            allOf(
                withId(R.id.saveButton), withText("SAVE"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        button.check(matches(isDisplayed()))

        val textView3 = onView(
            allOf(
                withId(R.id.inputCopy), withText("random text"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        textView3.check(matches(isDisplayed()))

        val textView4 = onView(
            allOf(
                withId(R.id.title), withText("TEST APP"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        textView4.check(matches(isDisplayed()))

        val textView5 = onView(
            allOf(
                withId(R.id.title), withText("TEST APP"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        textView5.check(matches(withText("TEST APP")))

        val textInputEditText2 = onView(
            allOf(
                withId(R.id.inputText), withText("random text"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.inputBox),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText2.perform(click())

        val textInputEditText3 = onView(
            allOf(
                withId(R.id.inputText), withText("random text"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.inputBox),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText3.perform(replaceText(""))

        val textInputEditText4 = onView(
            allOf(
                withId(R.id.inputText),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.inputBox),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText4.perform(closeSoftKeyboard())

        val textInputEditText5 = onView(
            allOf(
                withId(R.id.inputText),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.inputBox),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText5.perform(click())

        val textInputEditText6 = onView(
            allOf(
                withId(R.id.inputText),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.inputBox),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText6.perform(replaceText("test@email.gb"), closeSoftKeyboard())

        val materialButton3 = onView(
            allOf(
                withId(R.id.saveButton), withText("SAVE"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        materialButton3.perform(click())

        val materialButton4 = onView(
            allOf(
                withId(R.id.saveButton), withText("SAVE"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        materialButton4.perform(click())
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
