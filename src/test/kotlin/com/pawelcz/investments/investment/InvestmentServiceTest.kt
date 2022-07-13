package com.pawelcz.investments.investment

import io.mockk.InternalPlatformDsl.toStr
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal
import java.time.LocalDate
import java.time.temporal.ChronoUnit

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
    fun addInvestmentTest(){
        // given
        val firstTestInvestment = Investment("first", BigDecimal("1.06"), CapitalizationPeriodInMonths.SIX,
            LocalDate.parse("2022-04-18"), LocalDate.parse("2023-08-15") )
        val secondTestInvestment = Investment("second", BigDecimal("1.08"), CapitalizationPeriodInMonths.THREE,
            LocalDate.parse("2021-01-15"), LocalDate.parse("2022-06-15") )
        val firstTestObject = underTest.addInvestment(firstTestInvestment)
        val secondTestObject = underTest.addInvestment(secondTestInvestment)
        // when
        val result = underTest.allInvestments()
        val comparableToFirstTestObject = arrayOf(1, "first", BigDecimal("1.06"),
            ChronoUnit.DAYS.between(LocalDate.parse("2022-04-18"), LocalDate.parse("2023-08-15")).toInt())
        val comparableToSecondTestObject = arrayOf(2, "second", BigDecimal("1.08"),
            ChronoUnit.DAYS.between(LocalDate.parse("2021-01-15"), LocalDate.parse("2022-06-15")).toInt())
        val expected = 2
        // then
        assertThat(result.size).isEqualTo(expected)
        assertThat(arrayOf(firstTestObject.getId(),firstTestObject.getName(),
            firstTestObject.getInterest_Rate(), firstTestObject.getDays())
            .contentToString()).isEqualTo(comparableToFirstTestObject.contentToString())
        assertThat(arrayOf(secondTestObject.getId(),secondTestObject.getName(),
            secondTestObject.getInterest_Rate(), secondTestObject.getDays())
            .contentToString()).isEqualTo(comparableToSecondTestObject.contentToString())

    }

    @Test
    fun getInvestmentWithIdTest(){
        // given
        val testInvestment = Investment("first", BigDecimal("1.06"), CapitalizationPeriodInMonths.SIX,
            LocalDate.parse("2022-04-18"), LocalDate.parse("2023-08-15") )
        // when
        underTest.addInvestment(testInvestment)
        val investment = underTest.getInvestmentWithId(testInvestment.getId()!!)
        // then
        assertThat(investment).isEqualTo(testInvestment)
    }
}