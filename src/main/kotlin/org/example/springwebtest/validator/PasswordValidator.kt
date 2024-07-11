package org.example.springwebtest.validator

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import java.util.regex.Pattern

class PasswordValidator : ConstraintValidator<Password, String>{
    // 영문 숫자 특수기호 조합 8자리이상 정규식
    override fun isValid(value: String, context: ConstraintValidatorContext): Boolean {
        val regex = """^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#${'$'}%^&*()])[a-zA-Z\d!@#${'$'}%^&*()]{8,12}${'$'}""".toRegex()
        //return Pattern.matches(regex, value)
        return regex.matches(value)
    }
}

