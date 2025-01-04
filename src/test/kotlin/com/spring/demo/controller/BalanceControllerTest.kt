package com.spring.demo.controller

import com.spring.demo.entity.BalanceEntity
import org.junit.jupiter.api.Test
import com.spring.demo.service.BalanceService
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(BalanceController::class)
class BalanceControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @MockitoBean
    lateinit var balanceService: BalanceService

    @Test
    fun `should call getAllBalances`() {
        val mockBalance: List<BalanceEntity> = listOf(
            BalanceEntity().apply {
                id = 1
                unitholderNo = "UHN001"
                amount = 1000.50
                unit = 50.25
                pricePerUnit = 20.0
                effectiveDate = "2024-01-01"
                status = "ACTIVE"
                createdAt = "2024-01-01 10:00:00"
                createdBy = "admin"
            },
            BalanceEntity().apply {
                id = 2
                unitholderNo = "UHN002"
                amount = 2000.50
                unit = 100.25
                pricePerUnit = 20.0
                effectiveDate = "2024-01-01"
                status = "ACTIVE"
                createdAt = "2024-01-01 10:00:00"
                createdBy = "admin"
            }
        )

        Mockito.`when`(balanceService.getAllBalances()).thenReturn(mockBalance)

        mockMvc.get("/api/balance/")
            .andExpect { status().isOk }
            .andExpect { content {
                json(
                    """
                    [
                        {
                            "id": 1,
                            "unitholderNo": "UHN001",
                            "amount": 1000.50,
                            "unit": 50.25,
                            "pricePerUnit": 20.0,
                            "effectiveDate": "2024-01-01",
                            "status": "ACTIVE",
                            "createdAt": "2024-01-01 10:00:00",
                            "createdBy": "admin"
                        },
                        {
                            "id": 2,
                            "unitholderNo": "UHN002",
                            "amount": 2000.50,
                            "unit": 100.25,
                            "pricePerUnit": 20.0,
                            "effectiveDate": "2024-01-01",
                            "status": "ACTIVE",
                            "createdAt": "2024-01-01 10:00:00",
                            "createdBy": "admin"
                        }
                    ]
                    """.trimIndent()
                )
            } }
            .andExpect { content {
                jsonPath("$[0].id") { value(1) }
                jsonPath("$[0].unitholderNo") { value("UHN001") }
                jsonPath("$[1].id") { value(2) }
                jsonPath("$[1].unitholderNo") { value("UHN002") }
            } }
    }


}