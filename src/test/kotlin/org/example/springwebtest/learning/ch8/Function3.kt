package org.example.springwebtest.learning.ch8


data class SiteVisit (
        val path: String,
        val duration: Double,
        val os: OS
)

enum class OS {WINDOW, LINUX, MAX, IOS, ANDROID }

val log = listOf(
        SiteVisit("/", 34.0, OS.WINDOW)

)

fun List<SiteVisit>.averageDurationFor(predicate: (SiteVisit) -> Boolean) =
        filter(predicate).map(SiteVisit::duration).average()

fun main(){
    log.averageDurationFor { it.os in setOf(OS.ANDROID, OS.IOS) }
    log.averageDurationFor { it.os == OS.IOS && it.path == "/singUp"}
}