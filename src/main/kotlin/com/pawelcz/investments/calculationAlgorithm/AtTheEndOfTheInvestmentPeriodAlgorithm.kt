package com.pawelcz.investments.calculationAlgorithm

import com.pawelcz.investments.investment.Investment
import java.math.BigDecimal
import java.time.temporal.ChronoUnit

class AtTheEndOfTheInvestmentPeriodAlgorithm : Algorithm("AtTheEndOfTheInvestmentPeriodAlgorithm") {
    override fun calculation(investment: Investment) : BigDecimal {
        val interestRate = investment.getInterestRate()
        var periodInDays = ChronoUnit.DAYS.between(investment.getStartDate(),investment.getEndDate()).toInt()
        val capitalizationPeriod = investment.getCapitalizationPeriod().inDays
        return when(val numberOfCapitalizations = periodInDays / capitalizationPeriod){
            0 -> BigDecimal("1.0")
            else -> interestRate.pow(numberOfCapitalizations)
        }
    }
}