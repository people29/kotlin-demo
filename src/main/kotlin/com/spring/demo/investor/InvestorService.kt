package com.spring.demo.investor

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class InvestorService(
    @Autowired val investorRepository: InvestorRepository
) {
    fun getAllInvestors(): List<Investor> {
        return investorRepository.findAll()
    }

    fun getById(id: Long): Investor? {
        return investorRepository.findById(id).orElse(null)
    }

    fun getByCardNo(cardNo: String): Investor? {
        return investorRepository.findByCardNo(cardNo)
    }

    fun createInvestor(investor: Investor): Investor {
        if (investorRepository.findByCardNo(investor.cardNo) != null) {
            throw IllegalArgumentException("Investor with cardNo ${investor.cardNo} already exists")
        }
        return investorRepository.save(investor)
    }
}