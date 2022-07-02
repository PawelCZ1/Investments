package com.pawelcz.investments.investment

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
internal class InvestmentServiceImplTest(
    @Autowired
    private val investmentService: InvestmentService
) {

    @Test
    fun availableInvestments() {
        val x = investmentService.availableInvestments()
        assertThat(x).isEqualTo(5)
    }

    @Test
    fun allInvestments() {
    }

    @Test
    fun addInvestment() {
    }

    @Test
    fun getInvestmentWithId() {
    }

    @Test
    fun getInvestmentById() {
    }

    @Test
    fun getInvestment() {
    }
}