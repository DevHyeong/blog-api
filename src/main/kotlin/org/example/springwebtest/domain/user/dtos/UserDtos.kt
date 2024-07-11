package org.example.springwebtest.domain.user.dtos

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import org.example.springwebtest.domain.user.repository.entities.User
import org.example.springwebtest.validator.Password
import java.time.LocalDateTime
import java.util.*

data class UserCreateCommand(
        @field:Email(message = "이메일 형식에 맞지 않습니다")
        val email: String,

        @field:Password(message = "영문 숫자 특수기호 포함 8자리 이상이어야 합니다.")
        val password: String,

        @field:NotNull(message = "")
        val nickname: String
){
        fun toEntity(): User = User(email, nickname, password)
}

data class UserCreateResponse(
        val id: UUID,
        val email: String,
        val nickname: String,
        val createdAt: LocalDateTime
)

data class UserReadQuery(
        val id: UUID,
        val email: String,
        val nickname: String,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime
)

