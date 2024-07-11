package org.example.springwebtest.learning.ch10.deserialization

import org.example.springwebtest.learning.ch10.ValueSerializer
import kotlin.reflect.KClass
import kotlin.reflect.KParameter
import kotlin.reflect.full.primaryConstructor

class ClassInfoCache{
    private val cacheData = mutableMapOf<KClass<*>, ClassInfo<*>>()

    @Suppress("")
    operator fun <T: Any> get(cls: KClass<T>): ClassInfo<T> =
            cacheData.getOrPut(cls) {ClassInfo(cls)} as ClassInfo<T>
}


class ClassInfo<T : Any>(cls: KClass<T>){
    private val className = cls.qualifiedName
    private val constructor = cls.primaryConstructor
            ?: throw JKidException("Class ${cls.qualifiedName} doesn`t have a primary constructor")
    private val jsonNameToParamMap = hashMapOf<String, KParameter>()
    private val paramToSerializerMap = hashMapOf<KParameter, ValueSerializer<out Any?>>()
    private val jsonNameToDeserializeClassMap = hashMapOf<String, Class<out Any?>>()

    init {
        constructor.parameters.forEach { cacheDataForParameter(cls, it) }
    }

    private fun cacheDataForParameter(cls: KClass<*>, param: KParameter) {

    }

}


class JKidException(message: String): Exception(message)