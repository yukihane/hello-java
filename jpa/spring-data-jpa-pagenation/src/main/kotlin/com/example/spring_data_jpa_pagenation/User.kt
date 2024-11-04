package com.example.spring_data_jpa_pagenation

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "users")
class User(
    @Column(nullable = false)
    var name: String,
) {
    @Id
    @GeneratedValue
    var id: Long? = null

    @Column(nullable = false)
    var createdAt: Date = Date()
}
