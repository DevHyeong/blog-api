@file:JvmName("StringFunctions")

package org.example.springwebtest.learning

// val의 경우 getter, var의 경우 getter와 setter가 생긴다.

const val UNIX_LINE_SEPARATOR = "\n" // public static final String과 동일, 원시타입과 String 타입의 프로퍼티만 const로 지정할 수 있다.

fun String.lastChar(): Char = this.get(this.length - 1)

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
