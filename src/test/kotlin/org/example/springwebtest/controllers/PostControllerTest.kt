package org.example.springwebtest.controllers

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart
import java.io.FileInputStream
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun testCreatePost() {
        val file1 = MockMultipartFile("attachFiles", "1.png", MediaType.MULTIPART_FORM_DATA_VALUE, FileInputStream("/Users/jo/Downloads/log_out.PNG"))
        val file2 = MockMultipartFile("attachFiles", "1.png", MediaType.MULTIPART_FORM_DATA_VALUE, FileInputStream("/Users/jo/Downloads/log_out.PNG"))
        val file3 = MockMultipartFile("attachFiles", "1.png", MediaType.MULTIPART_FORM_DATA_VALUE, FileInputStream("/Users/jo/Downloads/log_out.PNG"))

        val content = "{\"content\": \"test\", \"title\": \"ttttt\"}"
        mockMvc.perform(multipart("/api/post")
                .file(file1)
                .file(file2)
                .file(file3)
                //.contentType(MediaType.MULTIPART_FORM_DATA)
                .param("title", "ttttt")
                .param("content", "123123")
                //.content(content)
                )
                .andDo(print())
    }
}