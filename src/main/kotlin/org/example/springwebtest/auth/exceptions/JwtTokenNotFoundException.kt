package org.example.springwebtest.auth.exceptions

import org.springframework.security.core.AuthenticationException

class JwtTokenNotFoundException(message: String) : AuthenticationException(message) {
}