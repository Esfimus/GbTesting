package com.esfimus.gbtesting.espresso

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.esfimus.gbtesting.ITEMS_COUNT
import com.esfimus.gbtesting.R
import com.esfimus.gbtesting.ui.MainActivity
import org.junit.Before
import org.junit.Test

class ListFragmentEspressoTest {
    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.listItemsInputText)).perform(replaceText(ITEMS_COUNT.toString()))
        onView(withId(R.id.listFragmentButton)).perform(click())
    }

    @Test
    fun fragment_testOpening() {
        onView(withId(R.id.fragmentTitle)).check(matches(withText("$ITEMS_COUNT items")))
    }

    @Test
    fun fragment_testAddItem() {
        onView(withId(R.id.addButton)).perform(click())
        onView(withId(R.id.fragmentTitle)).check(matches(withText("${ITEMS_COUNT + 1} items")))
        onView(withId(R.id.addButton)).perform(click())
        onView(withId(R.id.fragmentTitle)).check(matches(withText("${ITEMS_COUNT + 2} items")))
    }
}