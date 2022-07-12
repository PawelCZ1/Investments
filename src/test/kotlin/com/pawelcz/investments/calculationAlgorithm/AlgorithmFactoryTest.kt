package com.pawelcz.investments.calculationAlgorithm

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test

internal class AlgorithmFactoryTest{

    @Test
    fun makeAlgorithmTest(){
        // given
        val algorithmFactory = AlgorithmFactory()
        val firstOption = AlgorithmType.AT_END_OF_THE_INVESTMENT_PERIOD
        val secondOption = AlgorithmType.ON_THE_DAY_OF_THE_CALCULATION
        // when
        val firstResult = algorithmFactory.makeAlgorithm(firstOption)
        val secondResult = algorithmFactory.makeAlgorithm(secondOption)
        val expectedAlgorithmForFirstResult = AtTheEndOfTheInvestmentPeriodAlgorithm().javaClass
        val expectedAlgorithmForSecondResult = OnTheDayOfTheCalculationAlgorithm().javaClass
        // then
        assertThat(firstResult.javaClass).isEqualTo(expectedAlgorithmForFirstResult)
        assertThat(secondResult.javaClass).isEqualTo(expectedAlgorithmForSecondResult)
    }
}