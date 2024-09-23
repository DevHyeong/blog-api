package org.example.springwebtest.auth.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.example.springwebtest.auth.jwt.JwtTokenExtractor
import org.example.springwebtest.auth.jwt.JwtTokenValidator
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JwtAuthenticationFilter : OncePerRequestFilter() {
    private val log = LoggerFactory.getLogger(this.javaClass)!!
    private val jwtTokenValidator = JwtTokenValidator()
    private val jwtTokenExtractor = JwtTokenExtractor()


    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        log.info("execute JwtTokenFilter...")
        val token: String? = request.getHeader("Authorization")
        jwtTokenValidator.validate(token)

        val context: SecurityContext = SecurityContextHolder.createEmptyContext()
        val authentication: Authentication = createAuthentication(token!!)
        context.authentication = authentication
        SecurityContextHolder.setContext(context)

        filterChain.doFilter(request, response)

        SecurityContextHolder.clearContext()
    }

    private fun createAuthentication(token: String): Authentication{
        val email = jwtTokenExtractor.getEmail(token)
        return UsernamePasswordAuthenticationToken(email, "", mutableListOf())
    }
}