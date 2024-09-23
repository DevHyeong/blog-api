package org.example.springwebtest.auth.filter

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.example.springwebtest.auth.dtos.LoginUserDto
import org.example.springwebtest.auth.dtos.SecuredUser
import org.example.springwebtest.auth.jwt.JwtTokenGenerator
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter


class LoginAuthenticationFilter(
    defaultFilterProcessesUrl: String,
    authenticationManager: AuthenticationManager,
    val jwtTokenGenerator: JwtTokenGenerator,
): AbstractAuthenticationProcessingFilter(defaultFilterProcessesUrl, authenticationManager) {

    override fun attemptAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?
    ): Authentication {
        try{
            val userDto = jacksonObjectMapper().readValue(request?.inputStream, LoginUserDto::class.java)
            val authenticationToken = UsernamePasswordAuthenticationToken(userDto.email, userDto.password)
            return this.authenticationManager.authenticate(authenticationToken)
        }catch (e: Exception){
            throw InternalAuthenticationServiceException(e.message)
        }
    }

    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
        authResult: Authentication
    ) {
        val securedUser = authResult.principal as SecuredUser
        val jwtToken = jwtTokenGenerator.generateToken(securedUser.username)
        response.setHeader("Authorization", "Bearer ${jwtToken}")
        response.status = HttpStatus.OK.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = Charsets.UTF_8.name()
        response.writer.write("{\"success\": \"Authentication failed\"}")
    }

    override fun unsuccessfulAuthentication(
            request: HttpServletRequest,
            response: HttpServletResponse,
            failed: AuthenticationException
    ) {
        response.status = HttpStatus.UNAUTHORIZED.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = Charsets.UTF_8.name()
        response.writer.write("{\"error\": \"Authentication failed\"}")
    }
}