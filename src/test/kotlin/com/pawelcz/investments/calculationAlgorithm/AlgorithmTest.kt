package com.pawelcz.investments.calculationAlgorithm

import com.pawelcz.investments.calculation.Calculation
import com.pawelcz.investments.investment.CapitalizationPeriodInMonths
import com.pawelcz.investments.investment.Investment
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDate

internal class AlgorithmTest {

    @Test
    fun atTheEndOfTheInvestmentPeriodAlgorithmCalculationTest(){
        //given
        val algorithm = AtTheEndOfTheInvestmentPeriodAlgorithm()
        val testInvestment = Investment("first", BigDecimal("1.06"), CapitalizationPeriodInMonths.SIX,
            LocalDate.parse("2022-04-18"), LocalDate.parse("2023-08-15") )
        // when
        val result = algorithm.calculation(testInvestment)
        val expected = BigDecimal("1.1236")
        // then
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun onTheDayOfTheCalculationAlgorithmCalculationTest(){
        // given
        val algorithm = OnTheDayOfTheCalculationAlgorithm()
        val testInvestment = Investment("first", BigDecimal("1.06"), CapitalizationPeriodInMonths.SIX,
            LocalDate.parse("2022-04-18"), LocalDate.parse("2023-08-15") )
        // when
        val result = algorithm.calculation(testInvestment)
        val expected = BigDecimal("1.0")
        // then
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun calculateProfitForAtTheEndOfTheInvestmentPeriodAlgorithmTest(){
        // given
        val testInvestment = Investment("first", BigDecimal("1.06"), CapitalizationPeriodInMonths.SIX,
            LocalDate.parse("2022-04-18"), LocalDate.parse("2023-08-15") )
        val algorithmFactory = AlgorithmFactory()
        // when
        val amount = BigDecimal("5000")
        val result = Calculation.calculateProfit(amount,testInvestment,'1')
        val expected = BigDecimal("618.00")
        // then
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun calculateProfitForOnTheDayOfTheCalculationAlgorithmTest(){
        // given
        val testInvestment = Investment("first", BigDecimal("1.06"), CapitalizationPeriodInMonths.SIX,
            LocalDate.parse("2022-04-18"), LocalDate.parse("2023-08-15") )
        val algorithmFactory = AlgorithmFactory()
        // when
        val amount = BigDecimal("5000")
        val result = Calculation.calculateProfit(amount,testInvestment,'2')
        val expected = BigDecimal("0.00")
        // then
        assertThat(result).isEqualTo(expected)
    }

}