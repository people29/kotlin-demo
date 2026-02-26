package com.spring.demo.service

import com.spring.demo.entity.BalanceEntity
import com.spring.demo.repository.BalanceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service

@Service
class BalanceService(
    private val balanceRepository: BalanceRepository,
    private val jdbcTemplate: JdbcTemplate
) {
    fun getAllBalances(): List<BalanceEntity> = balanceRepository.findAll()
    fun getBalanceByUnitholderNo(unitholderNo: String) = balanceRepository.findByUnitholderNo(unitholderNo)
    fun save() = balanceRepository.save()
    fun getBalance(): List<BalanceEntity> {
        val sql = "SELECT * FROM balance"
        return jdbcTemplate.queryForList(sql, BalanceEntity::class.java)
    }
}