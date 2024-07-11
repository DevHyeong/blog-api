package org.example.springwebtest.controllers


import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.example.springwebtest.domain.user.dtos.UserCreateCommand
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    /**
     *  TO DO
     *  - 이메일 검증 테스트 코드 작성
     *  - 비밀번호 검증 테스트 코드 작성
     *  - email 또는 nickname 중복 검증 테스트 코드 작성
     *  - 성공 테스트 코드 작성
     *
     * */

    @Test
    fun testCreateUser() {
        val userCreateCommand = UserCreateCommand(email = "wogud19@naver.com",
                nickname = "생각하는개발자", password = "UuidCom13@$%")
        val content: String = jacksonObjectMapper().writeValueAsString(userCreateCommand)

        mockMvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andDo(print())
    }


}