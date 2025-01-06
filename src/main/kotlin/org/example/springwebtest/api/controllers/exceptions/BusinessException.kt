package org.example.springwebtest.api.controllers.exceptions

import org.springframework.http.HttpStatus

class BusinessException(
    val errorCode: HttpStatus,
    val msg: String
): RuntimeException() {
}