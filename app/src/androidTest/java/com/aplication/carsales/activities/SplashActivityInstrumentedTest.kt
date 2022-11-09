package com.aplication.carsales.activities

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.aplication.carsales.SplashScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.aplication.carsales.R

@LargeTest
@RunWith(JUnit4::class)
class SplashActivityInstrumentedTest {

    @Rule
    @JvmField
    var sActivityTestRule = ActivityScenarioRule(SplashScreen::class.java)



    @Test
    fun isImageSplashShowing(){
        onView(withId(R.id.splash_image))
            .check(matches(isDisplayed()))
    }

}