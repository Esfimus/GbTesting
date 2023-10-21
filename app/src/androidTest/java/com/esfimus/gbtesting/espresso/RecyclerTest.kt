package com.esfimus.gbtesting.espresso

import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.esfimus.gbtesting.ITEMS_COUNT
import com.esfimus.gbtesting.R
import com.esfimus.gbtesting.ui.MainActivity
import com.esfimus.gbtesting.ui.RecyclerAdapter
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RecyclerTest {
    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.listItemsInputText)).perform(replaceText(ITEMS_COUNT.toString()))
        onView(withId(R.id.listFragmentButton)).perform(click())
    }

    @Test
    fun recycler_ScrollTo() {
        onView(withId(R.id.recyclerLayout)).perform(
            RecyclerViewActions.scrollTo<RecyclerAdapter.CustomViewHolder>(
                hasDescendant(withText(ITEMS_COUNT.toString()))
            ))
    }

    @Test
    fun recycler_ClickAtPosition() {
        onView(withId(R.id.recyclerLayout)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerAdapter.CustomViewHolder>(
                5, click()
            ))
    }

    @Test
    fun recycler_ClickOnItem() {
        onView(withId(R.id.recyclerLayout)).perform(
            RecyclerViewActions.scrollTo<RecyclerAdapter.CustomViewHolder>(
                hasDescendant(withText(ITEMS_COUNT.toString()))
            ))

        onView(withId(R.id.recyclerLayout)).perform(
            RecyclerViewActions.actionOnItem<RecyclerAdapter.CustomViewHolder>(
                hasDescendant(withText("17")), click()
            ))
    }

    @Test
    fun recycler_CustomClick() {
        onView(withId(R.id.recyclerLayout)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerAdapter.CustomViewHolder>(
                19, tapOnItemWithId(R.id.chip)
            ))
    }

    private fun tapOnItemWithId(id: Int) = object : ViewAction {

        override fun getDescription() = "View Id"

        override fun getConstraints(): Matcher<View>? = null

        override fun perform(uiController: UiController, view: View) {
            val selectedView = view.findViewById(id) as View
            selectedView.performClick()
        }
    }

    @After
    fun close() {
        scenario.close()
    }
}