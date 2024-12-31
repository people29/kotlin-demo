package com.spring.demo.model

data class Balance(
    val id: Long,
    val unitholderNo: String,
    val amount: Double,
    val unit: Double,
    val pricePerUnit: Double,
    val effectiveDate: String,
    val status: String,
    val createdAt: String,
    val createdBy: String
)