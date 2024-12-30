package com.spring.demo.investor

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface InvestorRepository : JpaRepository<Investor, Long> {
    fun findByCardNo(cardNo: String): Investor?
}