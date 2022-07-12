package com.pawelcz.investments.investment

import io.mockk.InternalPlatformDsl.toArray
import io.mockk.InternalPlatformDsl.toStr
import io.mockk.boxedClass
import io.mockk.boxedValue
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal
import java.sql.Date
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
internal class InvestmentRepositoryTest(
    @Autowired
    private val underTest: InvestmentRepository
) {

    @AfterEach
    internal fun tearDown() {
        underTest.deleteAll()
    }

    @Test
    fun allInvestmentsTest(){
        // given
        val firstTestInvestment = Investment("first", BigDecimal("1.06"), CapitalizationPeriodInMonths.SIX,
            LocalDate.parse("2022-04-18"), LocalDate.parse("2023-08-15") )
        val secondTestInvestment = Investment("second", BigDecimal("1.08"), CapitalizationPeriodInMonths.THREE,
            LocalDate.parse("2021-01-15"), LocalDate.parse("2022-06-15") )
        // when
        underTest.save(firstTestInvestment)
        underTest.save(secondTestInvestment)
        val result = underTest.allInvestments()
        val expected = 2
        val firstObject = arrayOf(1L, "first")
        val secondObject = arrayOf(2L, "second")

        // then
        assertThat(result.size).isEqualTo(expected)
        assertThat(result[0]).isNotNull
        assertThat(result[1]).isNotNull
        assertThat(arrayOf(result[0].getId(), result[0].getName())).isEqualTo(firstObject)
        assertThat(arrayOf(result[1].getId(), result[1].getName())).isEqualTo(secondObject)
    }

    @Test
    fun availableInvestmentsTest(){
        // given
        val firstTestInvestment = Investment("first", BigDecimal("1.06"), CapitalizationPeriodInMonths.SIX,
            LocalDate.parse("2022-04-18"), LocalDate.parse("2023-08-15") )
        val secondTestInvestment = Investment("second", BigDecimal("1.08"), CapitalizationPeriodInMonths.THREE,
            LocalDate.parse("2021-01-15"), LocalDate.parse("2022-06-15") )
        // when
        underTest.save(firstTestInvestment)
        underTest.save(secondTestInvestment)
        val result = underTest.availableInvestments()
        val expected = 1
        val firstObject = arrayOf(1L, "first")
        // then
        assertThat(result.size).isEqualTo(expected)
        assertThat(result[0]).isNotNull
        assertThat(arrayOf(result[0].getId(), result[0].getName())).isEqualTo(firstObject)
    }

    @Test
    fun selectEverythingFromInvestmentTest(){
        // given
        val firstTestInvestment = Investment("first", BigDecimal("1.06"), CapitalizationPeriodInMonths.SIX,
            LocalDate.parse("2022-04-18"), LocalDate.parse("2023-08-15") )
        // when
        underTest.save(firstTestInvestment)
        val result = underTest.selectEverythingFromInvestment(1)
        val testObject = arrayOf(1L, "first", BigDecimal("1.06"),CapitalizationPeriodInMonths.SIX,
            LocalDate.parse("2022-04-18"), LocalDate.parse("2023-08-15"))
        // then
        assertThat(result).isNotNull
        assertThat(arrayOf(result.getId(), result.getName(), result.getInterest_Rate(),
            result.getCapitalization_Period(),result.getStart_Date(), result.getEnd_Date())).isEqualTo(testObject)
    }

    @Test
    fun selectLessFromInvestmentTest(){
        // given
        val firstTestInvestment = Investment("first", BigDecimal("1.06"), CapitalizationPeriodInMonths.SIX,
            LocalDate.parse("2022-04-18"), LocalDate.parse("2023-08-15") )
        // when
        underTest.save(firstTestInvestment)
        val result = underTest.selectLessFromInvestment(1)
        val testObject = arrayOf(1L, "first", BigDecimal("1.06"),
            ChronoUnit.DAYS.between(LocalDate.parse("2022-04-18"), LocalDate.parse("2023-08-15")).toInt())
        // then
        assertThat(result).isNotNull
        assertThat(arrayOf(result.getId(), result.getName(), result.getInterest_Rate(),
            result.getDays())).isEqualTo(testObject)

    }
}