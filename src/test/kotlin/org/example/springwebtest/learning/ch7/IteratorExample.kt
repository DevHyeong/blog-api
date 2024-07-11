package org.example.springwebtest.learning.ch7

import java.time.LocalDate


operator fun ClosedRange<LocalDate>.iterator() : Iterator<LocalDate> =
        object : Iterator<LocalDate> {
            var current = start
            override fun hasNext(): Boolean =
                    current <= endInclusive

            override fun next() = current.apply {
                current = plusDays(1)
            }
        }

