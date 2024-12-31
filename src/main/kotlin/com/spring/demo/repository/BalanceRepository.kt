package com.spring.demo.repository

import com.spring.demo.entity.BalanceEntity
import org.springframework.data.jpa.repository.JpaRepository

interface BalanceRepository : JpaRepository<BalanceEntity, Long> {
    fun findByUnitholderNo(unitholderNo: String): List<BalanceEntity>
}