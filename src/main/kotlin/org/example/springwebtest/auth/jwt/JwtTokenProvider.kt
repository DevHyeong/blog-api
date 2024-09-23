package org.example.springwebtest.auth.jwt

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.SignatureException
import org.example.springwebtest.auth.exceptions.JwtTokenNotFoundException
import java.util.*
import java.util.concurrent.TimeUnit

/*
*   TODO
*    - refresh Token이 왜 필요한가
*    - 토큰을 통해 사용자 정보를 가져오기 위해 redis나 hazelcast를 사용하기도 한다.
*    - io.jsonwebtoken:jjwt:0.9.1 vs jjwt 관련 라이브러리 3개 import하는거랑 차이점
*
* */

private val secretKey = "blog-api"

class JwtTokenGenerator {

    fun generateToken(username: String): String{
        val nowMillis = System.currentTimeMillis()
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(Date(nowMillis + TimeUnit.HOURS.toMillis(1)))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact()
    }
}

class JwtTokenExtractor{

    fun getEmail(token: String) : String{
        val claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .body
        return claims.subject
    }
}

class JwtTokenValidator{

    fun validate(token: String?){
        if(token == null)
            throw JwtTokenNotFoundException("request header haven`t jwt token")

        try {
            val claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .body
        } catch (e: SignatureException) {
            throw JwtTokenNotFoundException("Invalid JWT signature!")
        } catch (e: ExpiredJwtException) {
            throw JwtTokenNotFoundException("JWT token is expired!")
        } catch (e: Exception) {
            throw JwtTokenNotFoundException("JWT token is invalid!")
        }
    }
}