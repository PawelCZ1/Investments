package com.pawelcz.investments.calculationAlgorithm

import com.pawelcz.investments.investment.Investment
import java.math.BigDecimal
import java.time.LocalDate
import java.time.temporal.ChronoUnit



class OnTheDayOfTheCalculationAlgorithm : Algorithm("OnTheDayOfTheCalculationAlgorithm") {
    override fun calculation(investment: Investment) : BigDecimal {

        val interestRate = investment.getInterestRate()
        var periodInDays = ChronoUnit.DAYS.between(investment.StartDate(), LocalDate.now()).toInt()
        val capitalizationPeriod = investment.CapitalizationPeriod().inDays
        return when(val numberOfCapitalizations = periodInDays / capitalizationPeriod){
            0 -> BigDecimal("1.0")
            else -> interestRate.pow(numberOfCapitalizations)
        }
    }
}