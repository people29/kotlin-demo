package com.spring.demo.service

import com.spring.demo.repository.UnitholderBalanceRepository
import org.springframework.stereotype.Service

@Service
class UnitholderBalanceService(
    private val unitholderBalanceRepository: UnitholderBalanceRepository
) {
    fun save() = unitholderBalanceRepository.save()
}