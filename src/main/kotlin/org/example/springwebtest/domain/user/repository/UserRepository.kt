package org.example.springwebtest.domain.user.repository

import org.example.springwebtest.domain.user.repository.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepository : JpaRepository<User, UUID> {
    fun findByEmail(email: String): User?
    fun findByNickname(nickname: String): User?
}