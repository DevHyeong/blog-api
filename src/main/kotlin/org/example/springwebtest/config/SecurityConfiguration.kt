package org.example.springwebtest.config

import org.example.springwebtest.auth.filter.JwtAuthenticationFilter
import org.example.springwebtest.auth.filter.LoginAuthenticationFilter
import org.example.springwebtest.auth.jwt.JwtTokenGenerator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.ExceptionTranslationFilter


@Configuration
@EnableWebSecurity //(debug = true)
class SecurityConfiguration(
     private val authConfig: AuthenticationConfiguration
) {

    //TODO: URL METHOD 타입에 따라 인증을 할지말지 판단할 수 있는 설정이 있는지
    @Bean
    fun securityFilterChain(http: HttpSecurity) : SecurityFilterChain {
        http.csrf { it.disable() }

        http.authorizeHttpRequests {
            //it.requestMatchers(*WHITE_LIST_URLS).permitAll()
            //it.requestMatchers("/api/ping").permitAll()
            it.anyRequest().authenticated()

        }
//        .exceptionHandling {
//            it.authenticationEntryPoint(
//                { request, response, authException ->  response?.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase())}
//            )
//            .accessDeniedHandler(
//                { request, response, accessDeniedException ->
//                    response?.sendError(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase())
//                }
//            )
//
//        }
        .addFilterBefore(JwtAuthenticationFilter(), ExceptionTranslationFilter::class.java)
        .addFilterBefore(LoginAuthenticationFilter(
            "/login",
            authConfig.authenticationManager,
            JwtTokenGenerator()),
            JwtAuthenticationFilter::class.java
        )
        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = NoOpPasswordEncoder.getInstance()

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web: WebSecurity ->
            web.ignoring()
                    .requestMatchers("/ping", "/swagger-ui/**")
        }
    }

    companion object{
        private val WHITE_LIST_URLS = arrayOf("/**", "")
    }
}