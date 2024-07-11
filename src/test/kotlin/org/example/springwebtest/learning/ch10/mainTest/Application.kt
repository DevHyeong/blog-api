package org.example.springwebtest.learning.ch10.mainTest

import org.example.springwebtest.learning.ch10.serialization.serialize
import org.junit.jupiter.api.Test

enum class Grade{FIRST, SECOND, THIRD, FOURTH}

data class Student(val name: String, val grade: Grade)


class main {

    @Test
    fun test(){
        val student = Student("Chris", Grade.SECOND)
        val serialize = serialize(student)
        println(serialize)
    }
}