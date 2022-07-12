package com.pawelcz.investments.calculation

import com.pawelcz.investments.calculationAlgorithm.AlgorithmType
import com.pawelcz.investments.investment.CapitalizationPeriodInMonths
import com.pawelcz.investments.investment.Investment
import com.pawelcz.investments.investment.InvestmentService
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDate

internal class CalculationServiceMockTest {

    private val calculationRepository : CalculationRepository = mockk(relaxed = true)
    private val investmentService : InvestmentService = mockk(relaxed = true)
    private val underTest : CalculationService = CalculationServiceImpl(calculationRepository, investmentService)



    @Test
    @Disabled
    fun addCalculationTest() {
        // given
        val testInvestment : Investment = Investment("first", BigDecimal("1.06"), CapitalizationPeriodInMonths.SIX,
            LocalDate.parse("2022-04-18"), LocalDate.parse("2023-08-15") )
        val testCalculation : Calculation = Calculation(BigDecimal(5000), testInvestment,
            AlgorithmType.AT_END_OF_THE_INVESTMENT_PERIOD)
        // when
        underTest.addCalculation(testCalculation)
        // then
        val calculationSlot = slot<Calculation>()
        every { calculationRepository.save(capture(calculationSlot)) } returns calculationSlot.captured
    }



    @Test
    fun getCalculationByIdTest() {
        // when
        underTest.getCalculationById(1)
        // then
        verify(exactly = 1) { calculationRepository.getCalculationById(1) }
    }

    @Test
    fun calculationListForTheParticularInvestmentTest() {
        // when
        underTest.calculationListForTheParticularInvestment(1)
        // then
        verify(exactly = 1) { calculationRepository.calculationListForTheParticularInvestment(1) }
    }

    @Test
    fun clearTableTest(){
        // when
        underTest.clearTable()
        // then
        verify(exactly = 1) { calculationRepository.deleteAll() }
    }






}