package org.example.springwebtest.domain.post.service

import org.example.springwebtest.domain.post.dto.PostRequestDto
import org.example.springwebtest.domain.post.repository.PostRepository
import org.example.springwebtest.domain.post.repository.entities.PostAttachFile
import org.example.springwebtest.domain.user.repository.entities.User
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.File
import java.io.IOException

@Service
class PostService (
        private val postRepository: PostRepository
){
    @Value("\${app.post.file-path}")
    private lateinit var filePath: String

    fun createPost(postRequestDto: PostRequestDto){
        val user = User("", "", "")
        val post = postRequestDto.toEntity(user)
        val folder = File(filePath);
        if(!folder.exists())
            folder.mkdir()

        for (file in postRequestDto.attachFiles){
            val path = filePath + File.separator + file.originalFilename
            println(path)
            post.attachFiles?.add(file.originalFilename?.let { PostAttachFile(post, path, it) }!!)
            file.transferTo(File(path))
        }
        println(post.toString())
        //postRepository.save(post)
    }
}