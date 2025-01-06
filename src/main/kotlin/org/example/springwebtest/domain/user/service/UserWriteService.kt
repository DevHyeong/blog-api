package org.example.springwebtest.domain.user.service

import org.example.springwebtest.api.controllers.exceptions.BusinessException
import org.example.springwebtest.domain.user.dtos.UserCreateCommand
import org.example.springwebtest.domain.user.repository.UserRepository
import org.example.springwebtest.domain.user.repository.entities.User
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class UserWriteService (private val userRepository: UserRepository){

    /**
     *  TO DO
     *  - 비밀번호 암호화
     *
     * */
    fun createUser(userCreateCommand: UserCreateCommand){
        checkEmailValidation(userCreateCommand.email)
        checkNicknameValidation(userCreateCommand.nickname)
        val user = userCreateCommand.toEntity()
        userRepository.save(user)
    }

    private fun checkEmailValidation(email: String) =
            when(userRepository.findByEmail(email)){
                is User -> throw BusinessException(HttpStatus.BAD_REQUEST, "이메일 ${email}은 존재합니다.")
                else -> ""
            }
    private fun checkNicknameValidation(nickname: String) =
            when(userRepository.findByNickname(nickname)){
                is User -> throw BusinessException(HttpStatus.BAD_REQUEST, "닉네임 ${nickname}은 존재합니다.")
                else -> ""
            }
}