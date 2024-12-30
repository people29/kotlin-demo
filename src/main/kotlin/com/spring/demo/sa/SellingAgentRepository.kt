package com.spring.demo.sa

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface SellingAgentRepository : JpaRepository<SellingAgent, Long> {
    fun findByCode(code: String): SellingAgent?
    fun findByThaiName(thaiName: String): List<SellingAgent>

    @Query("SELECT sa FROM SellingAgent sa WHERE sa.code = ?1 and sa.enabled = true")
    fun findByCodeAndEnabled(code: String): SellingAgent?
}