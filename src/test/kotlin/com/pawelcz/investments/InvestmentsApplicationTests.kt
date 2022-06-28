package com.pawelcz.investments

import com.pawelcz.investments.calculationAlgorithm.AtTheEndOfTheInvestmentPeriodAlgorithm
import com.pawelcz.investments.calculationAlgorithm.OnTheDayOfTheCalculationAlgorithm
import com.pawelcz.investments.investment.CapitalizationPeriodInMonths
import com.pawelcz.investments.investment.Investment
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate


class InvestmentsApplicationTests {



	val testInvestment = Investment("test", 1.06, CapitalizationPeriodInMonths.SIX,
		LocalDate.parse("2022-01-15"), LocalDate.parse("2022-08-15") )
	val secondTestInvestment = Investment("secondTest", 1.08, CapitalizationPeriodInMonths.THREE,
	LocalDate.parse("2022-01-15"), LocalDate.parse("2022-08-15") )

	val algorithm = AtTheEndOfTheInvestmentPeriodAlgorithm()
	val secondAlgorithm = OnTheDayOfTheCalculationAlgorithm()



	@Test
	fun atTheEndOfTheInvestmentPeriodInterestRate(){
		val result = algorithm.calculation(testInvestment)
		val expected = 1.06
		assertThat(result).isEqualTo(expected)
	}

	@Test
	fun onTheDayOfTheCalculationInterestRate(){
		val result = secondAlgorithm.calculation(secondTestInvestment)
		val expected = 1.08
		assertThat(result).isEqualTo(expected)
	}





}
