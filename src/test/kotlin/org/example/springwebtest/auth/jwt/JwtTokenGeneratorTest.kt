package org.example.springwebtest.auth.jwt

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class JwtTokenGeneratorTest {

    @Test
    fun generateToken() {
        val jwtToken = JwtTokenGenerator().generateToken("wogud19@naver.com")

    }
}