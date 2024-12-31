package com.spring.demo.controller

import com.spring.demo.service.BalanceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/balance")
class BalanceController {
    @Autowired
    private lateinit var balanceService: BalanceService

    @GetMapping("/")
    fun getAllBalances(): List<Any>? {
        return balanceService.getAllBalances()
    }
}