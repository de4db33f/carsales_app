package com.aplication.carsales

import android.view.View
import android.widget.DatePicker
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.PickerActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.aplication.carsales.main_module.view.MainActivity
import com.google.android.material.datepicker.MaterialDatePicker
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.containsString
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import com.example.covidmodule.R as RCovid


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@LargeTest
@RunWith(JUnit4::class)
class ExampleInstrumentedTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun start() {
        Thread.sleep(3000L)
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        val cal: Calendar = Calendar.getInstance()
        cal.add(Calendar.DATE, -1)
        val date: String = dateFormat.format(cal.time)
        onView(withId(RCovid.id.date)).check(matches(withText(getDateFormatted(date))))
        onView(withId(RCovid.id.confirm_cases)).check(matches(withText(containsString(
            InstrumentationRegistry.getInstrumentation().targetContext.resources.getString(R.string.confirmed_cases).replace("%s", "")
        ))))
        onView(withId(RCovid.id.num_deaths)).check(matches(withText(containsString(
            InstrumentationRegistry.getInstrumentation().targetContext.resources.getString(R.string.death_cases).replace("%s", "")
        ))))
        Thread.sleep(2000L)


        onView(withId(RCovid.id.select_date_button)).perform(click())
        Thread.sleep(2000L)

        //onView(isAssignableFrom(DatePicker::class.java)).perform(PickerActions.setDate(date.split("-")[0].toInt(),date.split("-")[1].toInt(),date.split("-")[2].toInt()-3))
        onView(withText("OK")).perform(click())
        Thread.sleep(5000L)
    }


    fun getDateFormatted(date: String): String {
        return try {
            val formattedDateSplit = date.split("-")
            formattedDateSplit[2] + " de " + getMonthName(date) + " del " + formattedDateSplit[0]
        } catch (e: Exception) {
            ""
        }

    }

    private fun getMonthName(date: String): String {
        val cal = Calendar.getInstance()
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale("es", "ES"))
        cal.time = sdf.parse(date) as Date
        val a: String = SimpleDateFormat("MMMM", Locale("es", "ES")).format(cal.time)
        return a;
    }

    fun matchesDate(year: Int, month: Int, day: Int): Matcher<View?> {
        return object : BoundedMatcher<View?, DatePicker>(DatePicker::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("matches date:")
            }

            override fun matchesSafely(item: DatePicker): Boolean {
                return year == item.year && month == item.month && day == item.dayOfMonth
            }
        }
    }
}