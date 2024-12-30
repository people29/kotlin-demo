package com.spring.demo.sa

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SellingAgentService() {
    @Autowired
    private lateinit var sellingAgentRepository: SellingAgentRepository

    fun getAllSellingAgents(): List<SellingAgent> {
        return sellingAgentRepository.findAll()
    }

    fun getByCode(code: String): SellingAgent? {
        return sellingAgentRepository.findByCode(code)
    }

    fun getByThaiName(thaiName: String): List<SellingAgent> {
        return sellingAgentRepository.findByThaiName(thaiName)
    }

    fun getByCodeAndEnabled(code: String): SellingAgent? {
        return sellingAgentRepository.findByCodeAndEnabled(code)
    }
}