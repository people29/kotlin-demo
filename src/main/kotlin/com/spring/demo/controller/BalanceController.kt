package com.spring.demo.controller

import com.spring.demo.model.Balance
import com.spring.demo.service.BalanceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/balance")
class BalanceController {
    @Autowired
    private lateinit var balanceService: BalanceService

    @GetMapping("/code/{code}")
    fun getByCode(@PathVariable code: String): List<Balance>? {
        if (code == "BBLAM") {
            return balanceService.getBBLAMBalance().map { balance ->
                Balance(
                    id = balance.id!!,
                    unitholderNo = balance.unitholderNo!!,
                    amount = balance.amount!!,
                    unit = balance.unit!!,
                    pricePerUnit = balance.pricePerUnit!!,
                    effectiveDate = balance.effectiveDate!!,
                    status = balance.status!!,
                    createdAt = balance.createdAt!!,
                    createdBy = balance.createdBy!!
                )
            }
        } else if (code == "SCBAM") {
            return balanceService.getSCBAMBalance().map { balance ->
                Balance(
                    id = balance.id!!,
                    unitholderNo = balance.unitholderNo!!,
                    amount = balance.amount!!,
                    unit = balance.unit!!,
                    pricePerUnit = balance.pricePerUnit!!,
                    effectiveDate = balance.effectiveDate!!,
                    status = balance.status!!,
                    createdAt = balance.createdAt!!,
                    createdBy = balance.createdBy!!
                )
            }
        }
        return null
    }
}