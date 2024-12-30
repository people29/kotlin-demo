package com.spring.demo.sa

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/selling-agents")
class SellingAgentController() {
    @Autowired
    private lateinit var sellingAgentService: SellingAgentService

    @GetMapping
    fun getAllSellingAgents(): List<SellingAgent> {
        return sellingAgentService.getAllSellingAgents()
    }

    @GetMapping("/code/{code}")
    fun getByCode(@PathVariable code: String): SellingAgent? {
        return sellingAgentService.getByCode(code)
    }
}