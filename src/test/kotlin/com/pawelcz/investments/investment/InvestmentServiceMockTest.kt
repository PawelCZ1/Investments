package com.pawelcz.investments.investment

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test


internal class InvestmentServiceMockTest{

    private val investmentRepository : InvestmentRepository = mockk(relaxed = true)
    private val underTest : InvestmentService = InvestmentServiceImpl(investmentRepository)

    @Test
    fun canGetAllInvestments(){
        // when
        val investments = underTest.allInvestments()
        // then
        verify(exactly = 1) { investmentRepository.allInvestments() }
    }

    @Test
    fun canGetAvailableInvestments(){
        // when
        val investments = underTest.availableInvestments()
        // then
        verify(exactly = 1) { investmentRepository.availableInvestments() }
    }



    @Test
    fun canGetInvestmentWithId(){}
}