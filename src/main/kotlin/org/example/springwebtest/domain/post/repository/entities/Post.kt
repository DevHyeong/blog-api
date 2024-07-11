package org.example.springwebtest.domain.post.repository.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import lombok.ToString
import org.example.springwebtest.domain.user.repository.entities.User
import org.example.springwebtest.repositories.entities.PrimaryKeyEntity
import java.time.LocalDateTime


@Entity
@Table(name = "post")
class Post (
    title: String,
    content: String,
    writer: User,
    attachFiles: MutableList<PostAttachFile>?
) : PrimaryKeyEntity() {
    @Column(nullable = false)
    var title: String = title

    @Column(nullable = false)
    var content: String = content

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    var writer: User = writer
        protected set

    @OneToMany
    var attachFiles: MutableList<PostAttachFile>? = attachFiles

    init {
        writer.writePost(this)
    }


    override fun toString(): String {
        return "Post(title='$title', content='$content', writer=$writer, " +
            "attachFiles=${attachFiles?.forEach { it.toString() }})"
    }
}



@Entity
@Table(name = "post_attach_file")
class PostAttachFile (
    post: Post,
    filePath: String,
    fileName: String
) : PrimaryKeyEntity() {
    //TODO("createdAt, updatedBy 칼럼 없애기")

    var filePath: String = filePath
    var fileName: String = fileName

    @ManyToOne
    var post: Post = post

    override fun toString(): String {
        return "PostAttachFile(filePath='$filePath', fileName='$fileName')"
    }
}
