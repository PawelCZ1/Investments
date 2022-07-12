package com.pawelcz.investments

import com.pawelcz.investments.calculation.CalculationService
import com.pawelcz.investments.controller.InvestmentsController
import com.pawelcz.investments.investment.InvestmentService
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest


internal class InvestmentsControllerTestT {
    private val investmentService : InvestmentService = mockk(relaxed = true)
    private val calculationService : CalculationService = mockk(relaxed = true)
    private val underTest : InvestmentsController = InvestmentsController(investmentService, calculationService)

    @Test
    fun availableInvestmentsTest(){
        // when
        underTest.availableInvestments()
        // then
        verify(exactly = 1) { investmentService.availableInvestments() }
    }

    @Test
    fun allInvestmentsTest(){
        // when
        underTest.allInvestments()
        // then
        verify(exactly = 1) { investmentService.allInvestments() }
    }

    @Test
    fun historicalCalculationsOfTheParticularInvestmentTest(){
        // when
        underTest.historicalCalculationsOfTheParticularInvestment(1)
        // then
        verify(exactly = 1) { calculationService.historicalCalculationsOfTheParticularInvestment(1) }
    }

    @Test
    @Disabled
    fun addInvestmentTest(){
        // not yet implemented, testing would be the same as in the InvestmentServiceTest.kt
    }

    @Test
    @Disabled
    fun addCalculationTest(){
        // not yet implemented, testing would be the same as in the InvestmentServiceTest.kt
    }
}