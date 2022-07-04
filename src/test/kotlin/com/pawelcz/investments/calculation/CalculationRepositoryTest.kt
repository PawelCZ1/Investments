package com.pawelcz.investments.calculation

import com.pawelcz.investments.investment.CapitalizationPeriodInMonths
import com.pawelcz.investments.investment.Investment
import com.pawelcz.investments.investment.InvestmentRepository
import io.mockk.InternalPlatformDsl.toArray
import io.mockk.InternalPlatformDsl.toStr
import io.mockk.boxedValue
import org.assertj.core.api.AssertionsForClassTypes
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal
import java.sql.Date
import java.time.LocalDate
import kotlin.streams.toList


@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
internal class CalculationRepositoryTest(
    @Autowired
    private val underTest : CalculationRepository,
    @Autowired
    private val mockInvestmentRepository : InvestmentRepository
) {

    @AfterEach
    internal fun tearDown() {
        underTest.deleteAll()
        mockInvestmentRepository.deleteAll()
    }

    @Test
    fun getCalculationByIdTest(){
        // given
        val testInvestment = Investment("first", BigDecimal("1.06"), CapitalizationPeriodInMonths.SIX,
            LocalDate.parse("2022-04-18"), LocalDate.parse("2023-08-15") )
        val testCalculation = Calculation(BigDecimal(5000), testInvestment, '1')
        // when
        mockInvestmentRepository.save(testInvestment)
        underTest.save(testCalculation)
        val result = underTest.getCalculationById(2)
        val testObject = arrayOf(BigDecimal("5000.00")
        , LocalDate.now(), testInvestment.getId(), '1', testCalculation.getProfit())
        // then
        assertThat(result).isNotNull
        assertThat(result.toArray().contentToString()).isEqualTo(testObject.contentToString())
        assertThat(result).isExactlyInstanceOf(testObject.javaClass)
    }

    @Test
    fun calculationListForTheParticularInvestmentTest(){
        // given
        val testInvestment = Investment("first", BigDecimal("1.06"), CapitalizationPeriodInMonths.SIX,
            LocalDate.parse("2022-04-18"), LocalDate.parse("2023-08-15") )
        val firstTestCalculation = Calculation(BigDecimal(5000), testInvestment, '1')
        val secondTestCalculation = Calculation(BigDecimal(4000), testInvestment, '2')
        val thirdTestCalculation = Calculation(BigDecimal(3000), testInvestment, '1')
        // when
        mockInvestmentRepository.save(testInvestment)
        underTest.save(firstTestCalculation)
        underTest.save(secondTestCalculation)
        underTest.save(thirdTestCalculation)
        val result = underTest.calculationListForTheParticularInvestment(testInvestment.getId()!!)
        val testObjectPartOne = arrayOf(BigDecimal("5000.00")
            , Date.valueOf(LocalDate.now()),  '1', firstTestCalculation.getProfit())
        val testObjectPartTwo = arrayOf(BigDecimal("4000.00")
            , Date.valueOf(LocalDate.now()),  '2', secondTestCalculation.getProfit())
        val testObjectPartThree = arrayOf(BigDecimal("3000.00")
            , Date.valueOf(LocalDate.now()),  '1', thirdTestCalculation.getProfit())
        val testObject = listOf(testObjectPartOne, testObjectPartTwo, testObjectPartThree)
        // then
        assertThat(result).isNotNull
        assertThat(result.toTypedArray()).isEqualTo(testObject.toTypedArray())
        // shows same class but fail// assertThat(result).hasSameClassAs(testObject)

    }
}