package org.example.springwebtest.controllers

import jakarta.validation.Valid
import org.example.springwebtest.domain.user.service.UserWriteService
import org.example.springwebtest.domain.user.dtos.UserCreateCommand
import org.example.springwebtest.domain.user.dtos.UserCreateResponse
import org.example.springwebtest.domain.user.service.UserReadService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class UserController (
        private val userWriteService: UserWriteService,
        private val userReadService: UserReadService){

    @GetMapping("/user/{email}")
    fun getUser(@PathVariable email: String){

    }

    @PostMapping("/user")
    fun createUser(@Valid @RequestBody userCreateCommand: UserCreateCommand)
        : ResponseEntity<UserCreateResponse>{
        userWriteService.createUser(userCreateCommand)
        return ResponseEntity<UserCreateResponse>(HttpStatus.CREATED)
    }
}