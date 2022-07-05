package com.pawelcz.investments.calculationAlgorithm

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test

internal class AlgorithmFactoryTest{

    @Test
    fun makeAlgorithmTest(){
        // given
        val algorithmFactory = AlgorithmFactory()
        val firstOption = '1'
        val secondOption = '2'
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