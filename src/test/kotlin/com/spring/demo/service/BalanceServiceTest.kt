package com.spring.demo.service

import com.spring.demo.repository.BalanceRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class BalanceServiceTest {
    // Create a mock of BalanceRepository
    private val balanceRepository: BalanceRepository = mockk(relaxed = true)
    private val balanceService = BalanceService(balanceRepository)

    @Test
    fun `should call getAllBalances`() {
        every { balanceRepository.findAll() } returns emptyList()
        // Call the method getAllBalances
        val result = balanceService.getAllBalances()

        // Expected
        verify { balanceRepository.findAll() }
        verify(exactly = 1) { balanceRepository.findAll() }

        assertAll(
            { assertTrue(result.isEmpty()) },
            { verify { balanceRepository.findAll() } }
        )

        assert(result.isEmpty())

        assertThat(result).isEmpty()
    }

    @Test
    fun `should call getBalanceByUnitholderNo`() {
        // Call the method getBalanceByUnitholderNo
        balanceService.getBalanceByUnitholderNo("123")

        // Expected
        verify { balanceRepository.findByUnitholderNo("123") }
    }

    @Test
    fun getAllBalances() {
        // Call the method getAllBalances
        balanceService.getAllBalances()

        // Expected
        verify { balanceRepository.findAll() }
    }
}