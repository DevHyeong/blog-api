package org.example.springwebtest.config.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfiguration {
    val WHITE_LIST_URLS = arrayOf("/user", "")

    //TODO: URL METHOD 타입에 따라 인증을 할지말지 판단할 수 있는 설정이 있는지
    @Bean
    fun securityFilterChain(http: HttpSecurity) : SecurityFilterChain {
        http.csrf { it.disable() }
                .authorizeHttpRequests { it.requestMatchers(*WHITE_LIST_URLS).permitAll() }

        return http.build()
    }

}