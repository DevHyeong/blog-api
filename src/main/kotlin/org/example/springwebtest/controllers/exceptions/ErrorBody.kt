package org.example.springwebtest.controllers.exceptions

data class ErrorBody (val status:Int, val message :String, val parameters: List<FieldError> = listOf()){

    data class FieldError (
            val name: String,
            val value: String?,
            val message: String?
    )
}
