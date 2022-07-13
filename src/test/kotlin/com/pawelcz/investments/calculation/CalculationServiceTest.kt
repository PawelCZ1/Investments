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
import org.springframework.test.annotation.DirtiesContext
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

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @Test
    fun historicalCalculationsOfTheParticularInvestmentTest(){
        // given
        val testInvestment = Investment("first", BigDecimal("1.06"), CapitalizationPeriodInMonths.SIX,
            LocalDate.parse("2022-04-18"), LocalDate.parse("2023-08-15"))
        val firstTestCalculation = Calculation(BigDecimal(5000), testInvestment,
            AlgorithmType.AT_END_OF_THE_INVESTMENT_PERIOD)
        val secondTestCalculation = Calculation(BigDecimal(4000), testInvestment,
            AlgorithmType.ON_THE_DAY_OF_THE_CALCULATION)
        val thirdTestCalculation = Calculation(BigDecimal(3000), testInvestment,
            AlgorithmType.AT_END_OF_THE_INVESTMENT_PERIOD)
        // when
        investmentService.addInvestment(testInvestment)
        underTest.addCalculation(firstTestCalculation)
        underTest.addCalculation(secondTestCalculation)
        underTest.addCalculation(thirdTestCalculation)
        val result = underTest.historicalCalculationsOfTheParticularInvestment(testInvestment.getId()!!)
        val firstTestCalculationObject = arrayOf(BigDecimal("5000.00")
            , LocalDate.now(),  AlgorithmType.AT_END_OF_THE_INVESTMENT_PERIOD, firstTestCalculation.getProfit())
        val secondTestCalculationObject = arrayOf(BigDecimal("4000.00")
            , LocalDate.now(),  AlgorithmType.ON_THE_DAY_OF_THE_CALCULATION, secondTestCalculation.getProfit())
        val thirdTestCalculationObject = arrayOf(BigDecimal("3000.00")
            , LocalDate.now(),  AlgorithmType.AT_END_OF_THE_INVESTMENT_PERIOD, thirdTestCalculation.getProfit())
        val testCalculationList = arrayOf(firstTestCalculationObject, secondTestCalculationObject,
            thirdTestCalculationObject)
        val expectedValue = Pair(arrayOf(1, "first", BigDecimal("1.06"),"SIX",
            Date.valueOf(LocalDate.parse("2022-04-18")), Date.valueOf(LocalDate.parse("2023-08-15"))),
            testCalculationList)
        // then
        assertThat(arrayOf(result.first.getId(),result.first.getName(),result.first.getInterest_Rate(),
            result.first.getCapitalization_Period(),result.first.getStart_Date(),result.first.getEnd_Date())
            .contentToString()).isEqualTo(expectedValue.first.contentToString())
        assertThat(arrayOf(arrayOf(result.second[0].getAmount(), result.second[0].getCalculation_Date(),
            result.second[0].getAlgorithm_Type(), result.second[0].getProfit()), arrayOf(result.second[1].getAmount(),
            result.second[1].getCalculation_Date(), result.second[1].getAlgorithm_Type(), result.second[1].getProfit()),
            arrayOf(result.second[2].getAmount(), result.second[2].getCalculation_Date(), result.second[2]
                .getAlgorithm_Type(),
                result.second[2].getProfit()))).isEqualTo(expectedValue.second)
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @Test
    fun addCalculationTest(){
        // given
        val testInvestment : Investment = Investment("first", BigDecimal("1.06"),
            CapitalizationPeriodInMonths.SIX,
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
        assertThat(arrayOf(result.getAmount(),result.getCalculation_Date(),result.getInvestment_Id(),
            result.getAlgorithm_Type(),result.getProfit())).isEqualTo(arrayOf(expectedValue.getAmount(),
            expectedValue.getCalculation_Date(),expectedValue.getInvestment_Id(),
            expectedValue.getAlgorithm_Type(),expectedValue.getProfit()))
    }

}