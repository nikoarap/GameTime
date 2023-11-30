package com.nikoarap.gametime

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.nikoarap.gametime.feature_sports.presentation.common.CountdownTimer
import org.junit.Rule
import org.junit.Test

class TimerTest {

    @get:Rule
    val testRule = createComposeRule()

    @Test
    fun testCountdownValueMinute() {
        val timeUntilEventStartInMs = 60000L // 1 minute
        testRule.setContent {
            CountdownTimer(timeUntilEventStartInMs)
        }
        val expectedText = "1m, 00s"
        testRule.onNodeWithText(expectedText).assertExists()
    }

    @Test
    fun testCountdownValueDaysMonthsMinutesSeconds() {
        val timeUntilEventStartInMs = 16965052000 //196 days, 8 hours, 30 minutes and 52 seconds
        testRule.setContent {
            CountdownTimer(timeUntilEventStartInMs)
        }
        val expectedText = "196d, 8h, 30m, 52s"
        testRule.onNodeWithText(expectedText).assertExists()
    }

    @Test
    fun testCountdownValueZero() {
        val timeUntilEventStartInMs = 0L
        testRule.setContent {
            CountdownTimer(timeUntilEventStartInMs)
        }
        val expectedText = "EVENT_STARTED"
        testRule.onNodeWithText(expectedText).assertExists()
    }

}