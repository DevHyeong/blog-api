package org.example.springwebtest.learning.ch9

import java.util.concurrent.locks.Lock

fun foo(l: Lock){
    println("Before sync")
    synchronized(l){
        println("Action")
    }
    println("After sync")
}

inline fun <reified T> isA(value: Any) = value is T
