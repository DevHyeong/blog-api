package org.example.springwebtest.learning.ch5

import java.io.File

class Sequence {

    fun File.isInsideHiddenDirectory() =
            generateSequence(this) { it.parentFile }.any { it.isHidden }

    val isInsideHiddenDirectory = File("").isInsideHiddenDirectory()


}