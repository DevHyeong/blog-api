package org.example.springwebtest.repositories.entities

import jakarta.persistence.Column
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PostLoad
import jakarta.persistence.PostPersist
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import lombok.Getter
import org.hibernate.proxy.HibernateProxy
import org.springframework.data.domain.Persistable
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

@MappedSuperclass
abstract class PrimaryKeyEntity: Persistable<UUID>{
    @Id
    @Column(columnDefinition = "BINARY(16)")
    private val id: UUID = UUID.randomUUID()

    @Transient
    private var _isNew = true

    var createdAt: LocalDateTime = LocalDateTime.now()
        protected set
    var updatedAt: LocalDateTime = LocalDateTime.now()
        protected set

    override fun getId(): UUID = id

    override fun isNew(): Boolean = _isNew

    override fun equals(other: Any?): Boolean {
        if(other == null){
            return false
        }

        if(other !is HibernateProxy && this::class != other::class){
            return false
        }

        return id == getIdentifier(other)
    }

    override fun hashCode(): Int = Objects.hashCode(id)

    @PostPersist
    @PostLoad
    protected fun load(){
        _isNew = false
    }

    @PrePersist
    protected fun prePersistOperation() {
        this.createdAt = LocalDateTime.now()
    }

    @PreUpdate
    protected fun preUpdateOperation(){
        this.updatedAt = LocalDateTime.now()
    }

    private fun getIdentifier(obj: Any): Any? {
        return if(obj is HibernateProxy){
            obj.hibernateLazyInitializer.identifier
        }else{
            (obj as PrimaryKeyEntity).id
        }
    }
}