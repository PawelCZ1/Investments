package com.pawelcz.investments

import com.pawelcz.investments.calculation.Calculation
import com.pawelcz.investments.calculationAlgorithm.AlgorithmFactory
import com.pawelcz.investments.calculationAlgorithm.AtTheEndOfTheInvestmentPeriodAlgorithm
import com.pawelcz.investments.calculationAlgorithm.OnTheDayOfTheCalculationAlgorithm
import com.pawelcz.investments.investment.CapitalizationPeriodInMonths
import com.pawelcz.investments.investment.Investment
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal
import java.time.LocalDate


class InvestmentsApplicationTests {



	val testInvestment = Investment("test", BigDecimal("1.06"), CapitalizationPeriodInMonths.SIX,
		LocalDate.parse("2022-01-15"), LocalDate.parse("2022-08-15") )
	val secondTestInvestment = Investment("secondTest", BigDecimal("1.08"), CapitalizationPeriodInMonths.THREE,
	LocalDate.parse("2022-01-15"), LocalDate.parse("2023-08-15") )

	val thirdTestInvestment = Investment("thirdTest", BigDecimal("1.03"), CapitalizationPeriodInMonths.ONE,
		LocalDate.parse("2022-06-30"), LocalDate.parse("2023-01-12") )

	val algorithm = AtTheEndOfTheInvestmentPeriodAlgorithm()
	val secondAlgorithm = OnTheDayOfTheCalculationAlgorithm()

	

	@Test
	fun atTheEndOfTheInvestmentPeriodInterestRate(){
		val result = algorithm.calculation(secondTestInvestment)
		val expected = BigDecimal("1.06")
		assertThat(result).isEqualTo(expected)
	}

	@Test
	fun onTheDayOfTheCalculationInterestRate(){
		val result = secondAlgorithm.calculation(secondTestInvestment)
		val expected = BigDecimal("1.08")
		assertThat(result).isEqualTo(expected)
	}

	@Test
	fun calculateProfitFirstAlgorithmTest(){
		val algorithmFactory = AlgorithmFactory()
		val algorithm = algorithmFactory.makeAlgorithm('1')
		val amount = BigDecimal("5000")
		val result = Calculation.calculateProfit(amount,thirdTestInvestment,'1')
		val expected = BigDecimal("300.00")
		assertThat(result).isEqualTo(expected)

	}
	@Test
	fun calculateProfitSecondAlgorithmTest(){
		val algorithmFactory = AlgorithmFactory()
		val algorithm = algorithmFactory.makeAlgorithm('2')
		val amount = BigDecimal("5000")
		val result = (amount.multiply(algorithm.calculation(secondTestInvestment))).subtract(amount)
		val expected = BigDecimal("300.00")
		assertThat(result).isEqualTo(expected)

	}

	@Test
	fun getInvestmentWithIdTest(){

	}



}
