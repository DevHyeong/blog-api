package org.example.springwebtest.domain.user.repository.entities

import jakarta.persistence.*
import org.example.springwebtest.domain.post.repository.entities.Post
import org.example.springwebtest.repositories.entities.PrimaryKeyEntity

@Entity
@Table(name = "_user")
class User (
    email: String,
    nickname: String,
    password: String
) : PrimaryKeyEntity() {
    @Column(nullable = false, unique = true)
    var email: String = email
        protected set

    @Column(nullable = false, unique = true)
    var nickname: String = nickname
        protected set

    @Column(nullable = false)
    var password: String = password
        protected set

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "writer")
    //@JoinColumn(name = "id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    protected val mutablePosts: MutableList<Post> = mutableListOf()
    val posts: List<Post> get() = mutablePosts.toList()

    fun writePost(post: Post) = mutablePosts.add(post)
}