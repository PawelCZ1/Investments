package com.pawelcz.investments.investment

import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal
import java.time.LocalDate


internal class InvestmentServiceTest{

    private val investmentRepository : InvestmentRepository = mockk(relaxed = true)
    private val underTest : InvestmentService = InvestmentServiceImpl(investmentRepository)

    @Test
    fun canGetAllInvestments(){
        // when
        val investments = underTest.allInvestments()
        // then
        verify(exactly = 1) { investmentRepository.allInvestments() }
    }
}