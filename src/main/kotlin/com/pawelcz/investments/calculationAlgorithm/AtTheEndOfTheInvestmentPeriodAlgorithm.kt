package com.pawelcz.investments.calculationAlgorithm

import com.pawelcz.investments.investment.Investment
import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode

class AtTheEndOfTheInvestmentPeriodAlgorithm : Algorithm("AtTheEndOfTheInvestmentPeriodAlgorithm") {
    override fun calculation(investment: Investment) : BigDecimal {
        val capitalizationsPerYear = investment.CapitalizationPeriod().capitalizationsPerYear
        val interest = BigDecimal.ONE.add(investment.getInterestRate()
            .divide(BigDecimal(100).multiply(BigDecimal(capitalizationsPerYear)),
                10, RoundingMode.HALF_UP).stripTrailingZeros())
        val periodInDays = investment.getPeriodInDays()
        var daysPerCapitalization = 0
        when(capitalizationsPerYear){
            12 -> daysPerCapitalization = 30
            4 -> daysPerCapitalization = 90
            2 -> daysPerCapitalization = 180
            1 -> daysPerCapitalization = 360
        }

        return when(val numberOfCapitalizations = periodInDays / daysPerCapitalization){
            0 -> BigDecimal("1.0")
            else -> interest.pow(numberOfCapitalizations)
        }
    }
}
