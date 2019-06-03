package com.mobi.codingtest

import android.content.Context

import androidx.test.InstrumentationRegistry

import org.junit.Test

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()

        assertEquals("com.mobi.codingtest", appContext.packageName)
    }
}
