package org.example.springwebtest.learning.ch7

import org.junit.jupiter.api.Test
import java.time.LocalDate

class mainCh7Test {

    @Test
    fun testIterator(){
        val newYear = LocalDate.ofYearDay(2017, 1)
        val daysOff = newYear.minusDays(1)..newYear
        for(dayOff in daysOff) { println(dayOff) }
    }
}