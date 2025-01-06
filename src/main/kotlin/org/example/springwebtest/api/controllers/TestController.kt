package org.example.springwebtest.api.controllers

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @GetMapping("/ping")
    fun test(): SuccessResponseEntity<String>{
        return SuccessResponseEntity(HttpStatus.OK.value(), "정상적으로 통신이 됩니다.")
    }
}