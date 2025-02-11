package com.tetz.spring_boot_demo.entity.user

import java.time.LocalDateTime

class UserVoKt  // 기본 생성자
{
    // Getter와 Setter 메서드
    var id: Int? = null
    var username: String? = null
    var password: String? = null
    var email: String? = null
    var name: String? = null
    var createdAt: LocalDateTime? = null
    var updatedAt: LocalDateTime? = null

    // toString 메서드 (선택사항이지만 디버깅에 유용)
    override fun toString(): String {
        return "UserVo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}'
    }
}
