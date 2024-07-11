package org.example.springwebtest.domain.post.repository

import org.example.springwebtest.domain.post.repository.entities.Post
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PostRepository : JpaRepository<Post, UUID> {
}