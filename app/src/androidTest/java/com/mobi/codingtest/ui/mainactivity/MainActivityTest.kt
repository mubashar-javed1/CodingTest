package com.mobi.codingtest.ui.mainactivity

import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule

import com.mobi.codingtest.R

import org.junit.Rule
import org.junit.Test


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import junit.framework.TestCase.assertNotNull

class MainActivityTest {
    @Rule
    var testRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun checkViewsAvailability() {
        assertNotNull(onView(withId(R.id.edit_abbreviation)))
        assertNotNull(onView(withId(R.id.btn_show_result)))
        assertNotNull(onView(withId(R.id.progress_bar)))
        assertNotNull(onView(withId(R.id.tv_status)))
        assertNotNull(onView(withId(R.id.rv_acronym)))
    }

    @Test
    fun validateEditText() {
        onView(withId(R.id.edit_abbreviation)).perform(typeText("TV")).check(matches(withText("TV")))
    }

    @Test
    fun validateButton() {
        onView(withId(R.id.edit_abbreviation)).perform(ViewActions.clearText())
                .perform(typeText("TV"), closeSoftKeyboard())
        onView(withId(R.id.btn_show_result)).perform(click())
        onView(withId(R.id.progress_bar)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }


    @Test
    fun onViewClickedWhenInternetAvailable() {
        onView(withId(R.id.btn_show_result)).perform(click())
        onView(withId(R.id.tv_status)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
    }

    @Test
    fun onViewClickedWhenWithEmptyAcronym() {
        onView(withId(R.id.edit_abbreviation)).perform(ViewActions.clearText())
                .perform(typeText(""), closeSoftKeyboard())
        onView(withId(R.id.btn_show_result)).perform(click())
        onView(withId(R.id.tv_status)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
    }


    @Test
    fun showRecyclerView() {
        onView(withId(R.id.btn_show_result)).perform(click())
        onView(withId(R.id.rv_acronym)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }

    @Test
    @Throws(InterruptedException::class)
    fun loadDataInRecyclerView() {
        onView(withId(R.id.edit_abbreviation)).perform(ViewActions.clearText())
                .perform(typeText("TV"), closeSoftKeyboard())
        onView(withId(R.id.btn_show_result)).perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.tv_status)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.progress_bar)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
    }

    @Test
    @Throws(InterruptedException::class)
    fun showRecyclerViewWithEmptyList() {
        onView(withId(R.id.edit_abbreviation)).perform(ViewActions.clearText())
                .perform(typeText("dfkdjkfjfd"), closeSoftKeyboard())
        onView(withId(R.id.btn_show_result)).perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.tv_status)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }

    @Test
    @Throws(InterruptedException::class)
    fun showStatusTest() {
        onView(withId(R.id.edit_abbreviation)).perform(ViewActions.clearText())
                .perform(typeText("dfkdjkfjfdui"), closeSoftKeyboard())
        onView(withId(R.id.btn_show_result)).perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.tv_status)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }

    @Test
    fun showProgressBar() {
        onView(withId(R.id.edit_abbreviation)).perform(ViewActions.clearText())
                .perform(typeText("AB"), closeSoftKeyboard())
        onView(withId(R.id.btn_show_result)).perform(click())
        onView(withId(R.id.progress_bar)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }
}