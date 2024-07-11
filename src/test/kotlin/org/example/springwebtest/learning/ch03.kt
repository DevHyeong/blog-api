package org.example.springwebtest.learning

import org.junit.jupiter.api.Test

class ch03 {
    // 디폴트 파라미터 값 지정
    fun <T> joinToString (
            collection: Collection<T>,
            separator: String = ", ",
            prefix: String = "",
            postfix: String = ""
    ): String {
        val result = StringBuilder(prefix)
        for ((index, element) in collection.withIndex()){
            if(index > 0) result.append(separator)
            result.append(element)
        }
        result.append(postfix)
        return result.toString()
    }

    fun <T> Collection<T>.joinToString1(
            separator: String = ", ",
            prefix: String = "",
            postfix: String = ""
    ): String {
        val result = StringBuilder(prefix)
        for ((index, element) in this.withIndex()){
            if(index > 0) result.append(separator)
            result.append(element)
        }
        result.append(postfix)
        return result.toString()
    }

    // 확장 프로퍼티
    var StringBuilder.lastChar: Char
        get() = get(length - 1)
        set(value: Char) {
            this.setCharAt(length - 1, value)
        }

    fun parsePath(path: String){
        val regex = """(.+)/(.+)\.(.+)""".toRegex()
        val matchResult = regex.matchEntire(path)
        if(matchResult != null){
            val (directory, filename, extension) = matchResult.destructured
            println("$directory, $filename, $extension")
        }
    }


    @Test
    fun main(){
        val list = listOf(1, 2, 3);
        joinToString(list, separator = "", prefix = "", postfix = "") // 이름 붙인 인자
        println(joinToString(list)); // 디폴트 파라미터로 인해 인자 생략 가능
        println(list.joinToString1(separator = "; ", prefix = "(", postfix = ")"))
        println("Kotlin".lastChar()) // String이 수신 객체 타입이고 "kotlin"이 수신 객체다
        val sb = StringBuilder("Kotlin?")
        sb.lastChar = '!'
        println(sb)
        parsePath("path/to/dir/filename.ext")
    }
}