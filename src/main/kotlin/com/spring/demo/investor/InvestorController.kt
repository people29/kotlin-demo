package com.spring.demo.investor

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/investors")
class InvestorController {
    val investors = mutableListOf(InvestorData(1, "1234567890", "John", "Doe", "1234567890", "mail@mail.com"))

    @GetMapping
    fun getAllInvestors(): List<InvestorData> {
        return investors
    }

    @PostMapping
    fun addInvestor(@RequestBody investor: InvestorData): InvestorData {
        if (investors.any { it.cardNo == investor.cardNo }) {
            throw ResponseStatusException(HttpStatus.CONFLICT, "Investor with cardNo ${investor.cardNo} already exists")
        }
        investors.add(investor)
        return investor
    }

    @DeleteMapping("/{id}")
    fun deleteInvestor(@PathVariable id: Int) {
        if (!investors.removeIf { it.id == id }) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Investor with id $id not found")
        }
    }

    @PutMapping("/{id}")
    fun updateInvestor(@PathVariable id: Int, @RequestBody investor: InvestorData): InvestorData {
        val existingInvestor = investors.find { it.id == id }
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Investor with id $id not found")
        existingInvestor.firstname = investor.firstname
        existingInvestor.lastname = investor.lastname
        existingInvestor.phone = investor.phone
        return investor
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int): InvestorData? {
        return investors.find { investor -> investor.id == id }
    }

    @GetMapping("/search")
    fun searchInvestors(@RequestParam name: String): List<InvestorData> {
        println("Searching for $name")
        return investors.filter { it.firstname.contains(name, ignoreCase = true) || it.lastname.contains(name, ignoreCase = true) }
    }
}