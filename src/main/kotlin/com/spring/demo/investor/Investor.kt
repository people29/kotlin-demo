package com.spring.demo.investor

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity(name = "investor")
data class Investor (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val firstname: String?,
    val lastname: String?,
    val email: String?,
    val phone: String,
    val cardNo: String,
    val type: String,
    val saId: Long,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    val createdBy: String?,
    val updatedBy: String?,
)