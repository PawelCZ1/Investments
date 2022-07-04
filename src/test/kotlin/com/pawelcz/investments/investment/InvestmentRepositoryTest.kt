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
        val firstObject = arrayOf(1, "first")
        val secondObject = arrayOf(2, "second")

        // then
        assertThat(result.size).isEqualTo(expected)
        assertThat(result[0]).isNotNull
        assertThat(result[1]).isNotNull
        assertThat(result[0].toStr()).isEqualTo(firstObject.contentToString())
        assertThat(result[1].toStr()).isEqualTo(secondObject.contentToString())
        assertThat(result[0]).isExactlyInstanceOf(firstObject.javaClass)
        assertThat(result[0]).isExactlyInstanceOf(secondObject.javaClass)
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
        val firstObject = arrayOf(1, "first")
        // then
        assertThat(result.size).isEqualTo(expected)
        assertThat(result[0]).isNotNull
        assertThat(result[0].toStr()).isEqualTo(firstObject.contentToString())
        assertThat(result[0]).isExactlyInstanceOf(firstObject.javaClass)
    }

    @Test
    fun selectEverythingFromInvestmentTest(){
        // given
        val firstTestInvestment = Investment("first", BigDecimal("1.06"), CapitalizationPeriodInMonths.SIX,
            LocalDate.parse("2022-04-18"), LocalDate.parse("2023-08-15") )
        // when
        underTest.save(firstTestInvestment)
        val result = underTest.selectEverythingFromInvestment(1)
        val testObject = arrayOf(1, "first", BigDecimal("1.06"),CapitalizationPeriodInMonths.SIX,
            LocalDate.parse("2022-04-18"), LocalDate.parse("2023-08-15"))
        // then
        assertThat(result).isNotNull
        assertThat(result.toArray().contentToString()).isEqualTo(testObject.contentToString())
        assertThat(result).isExactlyInstanceOf(testObject.javaClass)
    }

    @Test
    fun selectLessFromInvestmentTest(){
        // given
        val firstTestInvestment = Investment("first", BigDecimal("1.06"), CapitalizationPeriodInMonths.SIX,
            LocalDate.parse("2022-04-18"), LocalDate.parse("2023-08-15") )
        // when
        underTest.save(firstTestInvestment)
        val result = underTest.selectLessFromInvestment(1)
        val testObject = arrayOf(1, "first", BigDecimal("1.06"),
            ChronoUnit.DAYS.between(LocalDate.parse("2022-04-18"), LocalDate.parse("2023-08-15")))
        // then
        assertThat(result).isNotNull
        assertThat(result.toArray().contentToString()).isEqualTo(testObject.contentToString())
        assertThat(result).isExactlyInstanceOf(testObject.javaClass)

    }
}