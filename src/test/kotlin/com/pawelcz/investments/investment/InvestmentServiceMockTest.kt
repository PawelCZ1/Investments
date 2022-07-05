package com.pawelcz.investments.investment

import io.mockk.InternalPlatformDsl.toArray
import io.mockk.InternalPlatformDsl.toStr
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDate


internal class InvestmentServiceMockTest{

    private val investmentRepository : InvestmentRepository = mockk(relaxed = true)
    private val underTest : InvestmentService = InvestmentServiceImpl(investmentRepository)

    @Test
    fun allInvestmentsTest(){
        // when
        val investments = underTest.allInvestments()
        // then
        verify(exactly = 1) { investmentRepository.allInvestments() }
    }

    @Test
    fun availableInvestmentsTest(){
        // when
        val investments = underTest.availableInvestments()
        // then
        verify(exactly = 1) { investmentRepository.availableInvestments() }
    }

    @Test
    @Disabled
    fun addInvestmentTest(){
        // when
        val investmentSlot = slot<Investment>()
        // then
        every { underTest.addInvestment(capture(investmentSlot)) } returns arrayOf(1, "first", 1.06).javaClass

        // test implemented in InvestmentServiceTest.kt //
    }

    @Test
    @Disabled
    fun getInvestmentWithIdTest(){
        // when
        val investment = underTest.getInvestmentWithId(1)
        // then
        assertThat(investment).isEqualTo(investmentRepository.findById(1).get())

        // test implemented in InvestmentServiceTest.kt //
    }



    @Test
    fun selectEverythingFromInvestmentTest(){
        // when
        val investment = underTest.selectEverythingFromInvestment(1)
        // then
        verify(exactly = 1) { investmentRepository.selectEverythingFromInvestment(1) }

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