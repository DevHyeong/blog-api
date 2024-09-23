package org.example.springwebtest.auth.service

import org.example.springwebtest.auth.dtos.SecuredUser
import org.example.springwebtest.domain.user.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
    private val userRepository: UserRepository
) : UserDetailsService{

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = username?.let { userRepository.findByEmail(it) }
                ?: throw UsernameNotFoundException("${username}은 존재하지 않습니다.");
        return SecuredUser(
                user.email,
                user.password)
    }
}