package org.example.springwebtest.domain.post.dto

import org.example.springwebtest.domain.post.repository.entities.Post
import org.example.springwebtest.domain.post.repository.entities.PostAttachFile
import org.example.springwebtest.domain.user.repository.entities.User
import org.springframework.web.multipart.MultipartFile

data class PostRequestDto (
        val title: String,
        val content: String,
        val attachFiles: List<MultipartFile>
){

    fun toEntity(user: User): Post = Post(title, content, user, mutableListOf<PostAttachFile>())
}