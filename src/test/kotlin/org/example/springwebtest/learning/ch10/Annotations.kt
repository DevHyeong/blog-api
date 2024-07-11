package org.example.springwebtest.learning.ch10

import kotlin.reflect.KClass

@Target(AnnotationTarget.PROPERTY)
annotation class JsonExclude

@Target(AnnotationTarget.PROPERTY)
annotation class JsonName(val name: String)

interface ValueSerializer<T> {
    fun toJsonValue(value: T): Any?
    fun fromJsonValue(jsonValue: Any?): T
}

/**
 *  KClass<out Any>의 의미 :
 *
 * */
@Target(AnnotationTarget.PROPERTY)
annotation class DeserializerInterface(val targetClass: KClass<out Any>)

/**
 *  KClass<out ValueSerializer<*>> 의미:
 *
* */
@Target(AnnotationTarget.PROPERTY)
annotation class CustomSerializer(val serializerClass: KClass<out ValueSerializer<*>>)