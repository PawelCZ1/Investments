package com.pawelcz.investments.calculationAlgorithm

import com.pawelcz.investments.investment.Investment
import java.math.BigDecimal

class AtTheEndOfTheInvestmentPeriodAlgorithm : Algorithm("AtTheEndOfTheInvestmentPeriodAlgorithm") {
    override fun calculation(investment: Investment) : BigDecimal {
        val interestRate = investment.getInterestRate()
        var periodInDays = investment.getPeriodInDays()
        val capitalizationPeriod = investment.CapitalizationPeriod().inDays
        return when(val numberOfCapitalizations = periodInDays / capitalizationPeriod){
            0 -> BigDecimal("1.0")
            else -> interestRate.pow(numberOfCapitalizations)
        }
    }
}