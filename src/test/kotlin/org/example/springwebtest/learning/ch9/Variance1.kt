package org.example.springwebtest.learning.ch9

import kotlin.reflect.KClass

interface FieldValidator<in T>{ // T에 대해 반공변인 인터페이스 선언
    fun validate(input: T): Boolean // T 타입의 값을 소비한다.
}

object DefaultStringValidator: FieldValidator<String> {
    override fun validate(input: String): Boolean = input.isNotEmpty()
}

object DefaultIntValidator: FieldValidator<Int>{
    override fun validate(input: Int): Boolean = input >= 0
}

object Validators {
    private val validators = mutableMapOf<KClass<*>, FieldValidator<*>>()
    fun <T: Any> registerValidator(kClass: KClass<T>, fieldValidator: FieldValidator<T>){
        validators[kClass] = fieldValidator
    }

    @Suppress("UNCHECKED_CAST")
    operator fun <T: Any> get(kClass: KClass<T>): FieldValidator<T> =
            validators[kClass] as? FieldValidator<T> ?:
                throw IllegalArgumentException("No Validator for ${kClass.simpleName}")
}





fun main (){
    val validators = mutableMapOf<KClass<*>, FieldValidator<*>>()
    validators[String::class] = DefaultStringValidator
    validators[Int::class] = DefaultIntValidator

    val stringValidator = validators[String::class] as FieldValidator<String>
    println(stringValidator.validate(""))
}

