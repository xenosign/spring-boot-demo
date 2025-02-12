package com.tetz.spring_boot_demo.entity.user

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "users")
class UserKt {
    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = false, unique = true, length = 50)
    var username: String? = null

    @Column(nullable = false)
    var password: String? = null

    @Column(nullable = false, unique = true, length = 100)
    var email: String? = null

    @Column(length = 50)
    var name: String? = null

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: LocalDateTime? = null
        private set

    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime? = null
        private set

    @PrePersist
    protected fun onCreate() {
        createdAt = LocalDateTime.now()
        updatedAt = LocalDateTime.now()
    }

    @PreUpdate
    protected fun onUpdate() {
        updatedAt = LocalDateTime.now()
    }
}
