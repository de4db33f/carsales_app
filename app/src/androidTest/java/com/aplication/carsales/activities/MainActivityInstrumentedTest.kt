package com.aplication.carsales.activities

import android.widget.DatePicker
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.PickerActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.aplication.carsales.R
import com.aplication.carsales.main_module.view.MainActivity
import com.aplication.carsales.common.Constants
import com.aplication.carsales.common.Utils
import org.hamcrest.Matchers.containsString
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*
import com.example.covidmodule.R as RCovid

@LargeTest
@RunWith(JUnit4::class)
class MainActivityInstrumentedTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun checkSetDateFromDatePicker() {
        Thread.sleep(Constants.LONG_TIME)
        onView(withId(RCovid.id.select_date_button)).perform(click())
        Thread.sleep(Constants.SMALL_TIME)
        val newDate: String = Utils.getDateFromToday(-4)
        val newDateIntList: List<Int> = Utils.getDateAsSeparatedInt(newDate)
        onView(isAssignableFrom(DatePicker::class.java))
            .perform(PickerActions.setDate(newDateIntList[0], newDateIntList[1], newDateIntList[2]))
        onView(withText(Constants.OK_LABEL)).perform(click())
        Thread.sleep(Constants.SMALL_TIME)
        checkViewLabels(newDate)
        Thread.sleep(Constants.MEDIUM_TIME)
    }

    @Test
    fun checkFirstViews() {
        Thread.sleep(Constants.VERY_SMALL_TIME)
        onView(withId(RCovid.id.progress_indicator)).check(matches(isDisplayed()))
        Thread.sleep(Constants.MEDIUM_TIME)
        val date: String = Utils.getDateFromToday(-1)
        checkViewLabels(date)
        Thread.sleep(Constants.SMALL_TIME)
    }

    private fun checkViewLabels(date: String) {
        onView(withId(RCovid.id.date)).check(matches(withText(Utils.getDateFormatted(date))))
        onView(withId(RCovid.id.confirm_cases)).check(
            matches(
                withText(
                    containsString(
                        InstrumentationRegistry.getInstrumentation().targetContext.resources.getString(
                            R.string.confirmed_cases
                        ).replace("%s", "")
                    )
                )
            )
        )
        onView(withId(RCovid.id.num_deaths)).check(
            matches(
                withText(
                    containsString(
                        InstrumentationRegistry.getInstrumentation().targetContext.resources.getString(
                            R.string.death_cases
                        ).replace("%s", "")
                    )
                )
            )
        )
    }

}