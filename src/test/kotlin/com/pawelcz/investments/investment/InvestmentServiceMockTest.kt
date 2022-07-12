package com.pawelcz.investments.investment


import io.mockk.*
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

import org.mockito.Mockito.verify
import java.math.BigDecimal
import java.time.LocalDate
import java.util.Optional


internal class InvestmentServiceMockTest{

    private val investmentRepository : InvestmentRepository = mockk(relaxed = true)
    private val underTest : InvestmentService = InvestmentServiceImpl(investmentRepository)

    @Test
    fun allInvestmentsTest(){
        // when
        val investments = underTest.allInvestments()
        // then
        verify(exactly = 1) { investmentRepository.allInvestments()}
    }

    @Test
    fun availableInvestmentsTest(){
        // when
        val investments = underTest.availableInvestments()
        // then
        verify(exactly = 1) { investmentRepository.availableInvestments()}
    }

    @Test
    @Disabled
    fun addInvestmentTest(){
        // when
        val investment = Investment("first", BigDecimal("6"), CapitalizationPeriodInMonths.SIX,
            LocalDate.parse("2022-04-18"), LocalDate.parse("2023-08-15") )
        // then
       // every { investmentRepository.save(any()) } returns Investment()

        //assertThat(investmentSlot.captured.javaClass).isEqualTo(Investment::class.java)

        // test implemented in InvestmentServiceTest.kt //
    }

    @Test
    @Disabled
    fun getInvestmentWithIdTest(){
        // when
        val testObject : Investment = underTest.getInvestmentWithId(1)
        // then
        verify(exactly = 1) {investmentRepository.findById(1).get()}

        // test implemented in InvestmentServiceTest.kt //
    }



    @Test
    fun selectEverythingFromInvestmentTest(){
        // when
        val investment = underTest.selectEverythingFromInvestment(1)
        // then
        verify(exactly = 1) { investmentRepository.selectEverythingFromInvestment(1)}

    }

    @Test
    fun selectLessFromInvestmentTest(){
        // when
        val investment = underTest.selectLessFromInvestment(1)
        // then
        verify(exactly = 1) { investmentRepository.selectLessFromInvestment(1) }
    }

    @Test
    fun clearTableTest(){
        // when
        underTest.clearTable()
        // then
        verify(exactly = 1) { investmentRepository.deleteAll() }

    }

}