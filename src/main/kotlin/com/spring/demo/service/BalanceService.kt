package com.spring.demo.service

import com.spring.demo.entity.BalanceEntity
import com.spring.demo.repository.BalanceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BalanceService(
    private val balanceRepository: BalanceRepository
) {
    fun getAllBalances(): List<BalanceEntity> = balanceRepository.findAll()
    fun getBalanceByUnitholderNo(unitholderNo: String) = balanceRepository.findByUnitholderNo(unitholderNo)
}