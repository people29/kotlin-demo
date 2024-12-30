package com.spring.demo.sa

import jakarta.persistence.*

@Entity
data class SellingAgent (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val code: String,

    @Column(name = "thai_name")
    val thaiName: String,

    @Column(name = "eng_name")
    val engName: String,

    @Column(name = "enabled")
    val enabled: Boolean,
)