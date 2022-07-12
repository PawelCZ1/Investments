package com.pawelcz.investments.calculation

import com.pawelcz.investments.calculationAlgorithm.AlgorithmType
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
        val testInvestment = Investment("first", BigDecimal("6"), CapitalizationPeriodInMonths.SIX,
            LocalDate.parse("2022-04-18"), LocalDate.parse("2023-08-15") )
        val testCalculation = Calculation(BigDecimal(5000), testInvestment,
            AlgorithmType.AT_END_OF_THE_INVESTMENT_PERIOD)
        // when
        mockInvestmentRepository.save(testInvestment)
        underTest.save(testCalculation)
        val result = underTest.getCalculationById(2)
        val testObject = arrayOf(BigDecimal("5000.00")
        , LocalDate.now(), testInvestment.getId(), AlgorithmType.AT_END_OF_THE_INVESTMENT_PERIOD,
            testCalculation.getProfit())
        // then
        assertThat(result).isNotNull
        assertThat(arrayOf(result.getAmount(),result.getCalculation_Date(), result.getInvestment_Id(),
            result.getAlgorithm_Type(),result.getProfit())).isEqualTo(testObject)

    }

    @Test
    fun calculationListForTheParticularInvestmentTest(){
        // given
        val testInvestment = Investment("first", BigDecimal("1.06"), CapitalizationPeriodInMonths.SIX,
            LocalDate.parse("2022-04-18"), LocalDate.parse("2023-08-15") )
        val firstTestCalculation = Calculation(BigDecimal(5000), testInvestment,
            AlgorithmType.AT_END_OF_THE_INVESTMENT_PERIOD)
        val secondTestCalculation = Calculation(BigDecimal(4000), testInvestment,
            AlgorithmType.ON_THE_DAY_OF_THE_CALCULATION)
        val thirdTestCalculation = Calculation(BigDecimal(3000), testInvestment,
            AlgorithmType.AT_END_OF_THE_INVESTMENT_PERIOD)
        // when
        mockInvestmentRepository.save(testInvestment)
        underTest.save(firstTestCalculation)
        underTest.save(secondTestCalculation)
        underTest.save(thirdTestCalculation)
        val result = underTest.calculationListForTheParticularInvestment(testInvestment.getId()!!)
        val testObjectPartOne = arrayOf(BigDecimal("5000.00")
                , LocalDate.now(),  AlgorithmType.AT_END_OF_THE_INVESTMENT_PERIOD, firstTestCalculation.getProfit())
        val testObjectPartTwo = arrayOf(BigDecimal("4000.00")
            , LocalDate.now(),  AlgorithmType.ON_THE_DAY_OF_THE_CALCULATION, secondTestCalculation.getProfit())
        val testObjectPartThree = arrayOf(BigDecimal("3000.00")
            , LocalDate.now(),  AlgorithmType.AT_END_OF_THE_INVESTMENT_PERIOD, thirdTestCalculation.getProfit())
        val testObject = arrayOf(testObjectPartOne, testObjectPartTwo, testObjectPartThree)
        // then
        assertThat(result).isNotNull
        assertThat(arrayOf(arrayOf(result[0].getAmount(), result[0].getCalculation_Date(),
            result[0].getAlgorithm_Type(), result[0].getProfit()), arrayOf(result[1].getAmount(),
            result[1].getCalculation_Date(), result[1].getAlgorithm_Type(), result[1].getProfit()),
            arrayOf(result[2].getAmount(), result[2].getCalculation_Date(), result[2].getAlgorithm_Type(),
                result[2].getProfit()))).isEqualTo(testObject)
    }
}