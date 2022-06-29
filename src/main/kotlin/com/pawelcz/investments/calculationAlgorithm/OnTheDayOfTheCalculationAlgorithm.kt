package com.pawelcz.investments.calculationAlgorithm

import com.pawelcz.investments.investment.Investment
import java.math.BigDecimal
import java.time.LocalDate
import java.time.temporal.ChronoUnit



class OnTheDayOfTheCalculationAlgorithm : Algorithm("OnTheDayOfTheCalculationAlgorithm") {
    override fun calculation(investment: Investment) : BigDecimal {

        val interestRate = investment.getInterestRate()
        var periodInDays = ChronoUnit.DAYS.between(investment.getStartDate(), LocalDate.now()).toInt()
        val capitalizationPeriod = investment.getCapitalizationPeriod().inDays
        when(val numberOfCapitalizations = periodInDays / capitalizationPeriod){
            0 -> return BigDecimal("1.0")
            else -> return interestRate.pow(numberOfCapitalizations)
        }
    }
}