package com.example.tiptime

import android.util.AttributeSet
import android.widget.RadioButton
import org.junit.Test

import org.junit.Assert.*
import kotlin.math.round

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UnitTest {
    @Test
    fun tipIsCorrect() {
        assertEquals(4.0, 20.0*20/100, 0.02)
        assertEquals(3.6, 18.0*20/100, 0.02)
        assertEquals(3.0, 15.0*20/100, 0.02)
    }
    @Test
    fun roundedTipIsCorrect() {
        assertEquals(4.0, round(20.0*20/100), 0.02)
        assertEquals(4.0, round(18.0*20/100), 0.02)
        assertEquals(3.0, round(15.0*20/100), 0.02)
    }
}

