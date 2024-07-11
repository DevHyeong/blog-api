package org.example.springwebtest.config.security

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import java.util.*

class AuthenticationFilter : Filter {
    private val log = LoggerFactory.getLogger(this.javaClass)!!

    override fun doFilter(req: ServletRequest?, res: ServletResponse?, chain: FilterChain?) {
        log.info("execute authentication filter")
        val httpServletRequest: HttpServletRequest = req as HttpServletRequest
        val token: String? = httpServletRequest.getHeader("X-ACCESS-TOKEN")
        if(!validateToken(token))
            throw RuntimeException("")

        req.setAttribute("userUuid", getUserInfoFromToken(token!!))
        chain?.doFilter(req, res)
    }

    private fun validateToken(token: String?): Boolean{
        return true
    }

    private fun getUserInfoFromToken(token: String): UUID{
        return UUID.fromString("")
    }
}