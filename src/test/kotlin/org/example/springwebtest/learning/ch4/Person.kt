package org.example.springwebtest.learning.ch4


// 4.24 중첩 객체를 사용해 Comparator 구현
data class Person(val name: String){

    object CaseInsensitiveFileComparator : Comparator<Person> {
        override fun compare(o1: Person, o2: Person): Int =
            o1.name.compareTo(o2.name)
    }
}
