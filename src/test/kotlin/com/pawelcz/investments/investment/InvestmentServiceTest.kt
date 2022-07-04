package com.pawelcz.investments.investment

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal
import java.time.LocalDate

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
internal class InvestmentServiceTest(
    @Autowired
    private val underTest: InvestmentService
) {

    @AfterEach
    internal fun tearDown() {
        underTest.clearTable()
    }

    @Test
    @Disabled
    fun doesReturnAllInvestmentsAsList(){
        // given
        val firstTestInvestment = Investment("first", BigDecimal("1.06"), CapitalizationPeriodInMonths.SIX,
            LocalDate.parse("2022-04-18"), LocalDate.parse("2023-08-15") )
        val secondTestInvestment = Investment("second", BigDecimal("1.08"), CapitalizationPeriodInMonths.THREE,
            LocalDate.parse("2021-01-15"), LocalDate.parse("2022-06-15") )
       // underTest.save(firstTestInvestment)
       // underTest.save(secondTestInvestment)
        // when
        val result = underTest.allInvestments()
        val expected = 2
        // then
        assertThat(result.size).isEqualTo(expected)


    }
}