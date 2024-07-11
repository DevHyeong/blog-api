package org.example.springwebtest.controllers

import org.example.springwebtest.domain.post.dto.PostRequestDto
import org.example.springwebtest.domain.post.service.PostService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PostController(private val postService: PostService) {

    @PostMapping("/post")
    fun createPost(postRequestDto: PostRequestDto){
        postService.createPost(postRequestDto)
    }
}