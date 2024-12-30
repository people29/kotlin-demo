package com.spring.demo.investor

import java.time.LocalDateTime
import kotlin.random.Random

data class InvestorData(
    val id: Int = Random.nextInt(),
    val cardNo: String,
    var firstname: String,
    var lastname: String,
    var phone: String,
    var email: String?,
    val createdAt: LocalDateTime = LocalDateTime.now(),
)