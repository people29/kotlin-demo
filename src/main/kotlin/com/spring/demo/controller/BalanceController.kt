package com.spring.demo.controller

import com.spring.demo.service.BalanceService
import com.spring.demo.service.UnitholderBalanceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/balance")
class BalanceController(
    private val balanceService: BalanceService,
) {
    @GetMapping("/")
    fun getAllBalances(): List<Any>? {
        return balanceService.getAllBalances()
    }

    @GetMapping("/get")
    fun getBalance2(): List<Any>? {
        return balanceService.getBalance()
    }

    @GetMapping("/insert")
    fun insertBalances(): String {
        try {
            println("Inserting balances... ${LocalDateTime.now()}")
            balanceService.save()
            println("Successfully inserted balances... ${LocalDateTime.now()}")
            return "OK"
        } catch (e: Exception) {
            println(e)
            return "error"
        }
    }
}