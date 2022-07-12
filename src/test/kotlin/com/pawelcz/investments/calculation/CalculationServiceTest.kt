package com.pawelcz.investments.calculation

import com.pawelcz.investments.calculationAlgorithm.AlgorithmType
import com.pawelcz.investments.dto.CalculationParametersDTO
import com.pawelcz.investments.investment.CapitalizationPeriodInMonths
import com.pawelcz.investments.investment.Investment
import com.pawelcz.investments.investment.InvestmentService
import io.mockk.InternalPlatformDsl.toArray
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal
import java.sql.Date
import java.time.LocalDate

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
internal class CalculationServiceTest(
    @Autowired
    private val underTest : CalculationService,
    @Autowired
    private val investmentService: InvestmentService
) {

    @AfterEach
    internal fun tearDown() {
        underTest.clearTable()
        investmentService.clearTable()
    }

    @Test
    fun historicalCalculationsOfTheParticularInvestmentTest(){
        // given
        val testInvestment = Investment("first", BigDecimal("1.06"), CapitalizationPeriodInMonths.SIX,
            LocalDate.parse("2022-04-18"), LocalDate.parse("2023-08-15"))
        val firstTestCalculation = Calculation(BigDecimal(5000), testInvestment, AlgorithmType.AT_END_OF_THE_INVESTMENT_PERIOD)
        val secondTestCalculation = Calculation(BigDecimal(4000), testInvestment, AlgorithmType.ON_THE_DAY_OF_THE_CALCULATION)
        val thirdTestCalculation = Calculation(BigDecimal(3000), testInvestment, AlgorithmType.AT_END_OF_THE_INVESTMENT_PERIOD)
        // when
        investmentService.addInvestment(testInvestment)
        underTest.addCalculation(firstTestCalculation)
        underTest.addCalculation(secondTestCalculation)
        underTest.addCalculation(thirdTestCalculation)
        val result = underTest.historicalCalculationsOfTheParticularInvestment(testInvestment.getId()!!)
        val firstTestCalculationObject = arrayOf(BigDecimal("5000.00")
            , Date.valueOf(LocalDate.now()),  '1', firstTestCalculation.getProfit())
        val secondTestCalculationObject = arrayOf(BigDecimal("4000.00")
            , Date.valueOf(LocalDate.now()),  '2', secondTestCalculation.getProfit())
        val thirdTestCalculationObject = arrayOf(BigDecimal("3000.00")
            , Date.valueOf(LocalDate.now()),  '1', thirdTestCalculation.getProfit())
        val testCalculationList = listOf(firstTestCalculationObject, secondTestCalculationObject, thirdTestCalculationObject)
        val expectedValue = Pair(arrayOf(1, "first", BigDecimal("1.06"),"SIX",
            Date.valueOf(LocalDate.parse("2022-04-18")), Date.valueOf(LocalDate.parse("2023-08-15"))), testCalculationList)
        // then
        assertThat(result.first.toArray().contentToString()).isEqualTo(expectedValue.first.contentToString())
        assertThat(result.second.toTypedArray()).isEqualTo(expectedValue.second.toTypedArray())
    }

    @Test
    fun addCalculationTest(){
        // given
        val testInvestment : Investment = Investment("first", BigDecimal("1.06"), CapitalizationPeriodInMonths.SIX,
            LocalDate.parse("2022-04-18"), LocalDate.parse("2023-08-15") )
        val testCalculation : Calculation = Calculation(BigDecimal(5000), testInvestment,
            AlgorithmType.AT_END_OF_THE_INVESTMENT_PERIOD)
        val testCalculationParametersDTO = CalculationParametersDTO(BigDecimal(5000),
            AlgorithmType.AT_END_OF_THE_INVESTMENT_PERIOD)
        // when
        investmentService.addInvestment(testInvestment)
        val result = underTest.addCalculation(testInvestment.getId()!!, testCalculationParametersDTO )
        val expectedValue = underTest.getCalculationById(2)
        // then
        assertThat(result).isEqualTo(expectedValue)
    }

}