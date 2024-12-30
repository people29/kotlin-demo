package com.spring.demo.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "balance", schema = "bblam")
class BBLAMBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var unitholderNo: String? = null
    var amount: Double? = null
    var unit: Double? = null
    var pricePerUnit: Double? = null
    var effectiveDate: String? = null
    var status: String? = null
    var createdAt: String? = null
    var createdBy: String? = null
}

@Entity
@Table(name = "balance", schema = "scbam")
class SCBAMBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var unitholderNo: String? = null
    var amount: Double? = null
    var unit: Double? = null
    var pricePerUnit: Double? = null
    var effectiveDate: String? = null
    var status: String? = null
    var createdAt: String? = null
    var createdBy: String? = null
}