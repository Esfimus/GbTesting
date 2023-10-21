package com.esfimus.gbtesting.espresso

import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.esfimus.gbtesting.ANOTHER_INPUT
import com.esfimus.gbtesting.APP_NAME
import com.esfimus.gbtesting.EMPTY_MESSAGE
import com.esfimus.gbtesting.R
import com.esfimus.gbtesting.TEST_INPUT
import com.esfimus.gbtesting.ui.MainActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityEspressoTest {
    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun activity_IsNotNull() {
        scenario.onActivity {
            TestCase.assertNotNull(it)
        }
    }

    @Test
    fun activity_IsResumed() {
        TestCase.assertEquals(Lifecycle.State.RESUMED, scenario.state)
    }

    @Test
    fun activityTitle_IsNotNull() {
        scenario.onActivity {
            val title = it.findViewById<TextView>(R.id.title)
            TestCase.assertNotNull(title)
        }
    }

    @Test
    fun activityTextView_IsNotNull() {
        scenario.onActivity {
            val inputCopy = it.findViewById<TextView>(R.id.inputCopy)
            TestCase.assertNotNull(inputCopy)
        }
    }

    @Test
    fun activityEditTextBox_IsNotNull() {
        scenario.onActivity {
            val textBox = it.findViewById<TextInputLayout>(R.id.inputBox)
            TestCase.assertNotNull(textBox)
        }
    }

    @Test
    fun activityEditText_IsNotNull() {
        scenario.onActivity {
            val textInput = it.findViewById<TextInputEditText>(R.id.inputText)
            TestCase.assertNotNull(textInput)
        }
    }

    @Test
    fun activityTextView_HasText() {
        val assertion = matches(withText(APP_NAME))
        onView(withId(R.id.title)).check(assertion)
    }

    @Test
    fun activityTextView_IsDisplayed() {
        onView(withId(R.id.title)).check(matches(isDisplayed()))
    }

    @Test
    fun activityTextView_IsCompletelyDisplayed() {
        onView(withId(R.id.title)).check(matches(isCompletelyDisplayed()))
    }

    @Test
    fun activityButton_IsEffectiveVisible() {
        onView(withId(R.id.saveButton)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }

    @Test
    fun activityButton_IsWorking() {
        onView(withId(R.id.saveButton)).perform(click())
        onView(withId(R.id.inputCopy)).check(matches(withText(EMPTY_MESSAGE)))
    }

    @Test
    fun activityTextViewFromInput_IsWorking() {
        onView(withId(R.id.inputText)).perform(replaceText(TEST_INPUT))
        onView(withId(R.id.saveButton)).perform(click())
        onView(withId(R.id.inputCopy)).check(matches(withText(TEST_INPUT)))
    }

    @Test
    fun activityTextViewFromAnotherInput_IsWorking() {
        onView(withId(R.id.inputText)).perform(replaceText(TEST_INPUT))
        onView(withId(R.id.saveButton)).perform(click())
        onView(withId(R.id.inputText)).perform(replaceText(ANOTHER_INPUT))
        onView(withId(R.id.saveButton)).perform(click())
        onView(withId(R.id.inputCopy)).check(matches(withText(ANOTHER_INPUT)))
    }

    @After
    fun close() {
        scenario.close()
    }
}