package com.spring.demo.service

import com.spring.demo.repositories.BBLAMBalanceRepository
import com.spring.demo.repositories.SCBAMBalanceRepository
import org.springframework.stereotype.Service

@Service
class BalanceService(
    private val bblamBalanceRepository: BBLAMBalanceRepository,
    private val scbamBalanceRepository: SCBAMBalanceRepository,
) {
    fun getBBLAMBalance() = bblamBalanceRepository.findAll()
    fun getSCBAMBalance() = scbamBalanceRepository.findAll()
}