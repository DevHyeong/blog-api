package org.example.springwebtest.domain.user.service


import org.example.springwebtest.domain.user.repository.UserRepository
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserReadService(private val userRepository: UserRepository) {

//    fun getUserByEmail(email: String): UserDto {
//        val user = userRepository.findByEmail(email) ?: throw UsernameNotFoundException("this email ${email} doesn't exist")
//        return UserDto(user.id,
//                user.email,
//                user.nickname,
//                user.createdAt,
//                user.updatedAt
//        )
//    }
}