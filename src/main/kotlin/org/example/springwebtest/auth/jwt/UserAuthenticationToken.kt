package org.example.springwebtest.auth.jwt

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority


// TODO 스프링 시큐리티 기본 인증과 인가 처리로직 먼저 생각해보기
class UserAuthenticationToken: AbstractAuthenticationToken {

    constructor(token: String, authorities: Collection<GrantedAuthority>) : super(authorities) {

    }

    override fun getCredentials(): Any {
        TODO("Not yet implemented")
    }

    override fun getPrincipal(): Any {
        TODO("Not yet implemented")
    }
}