package com.pawelcz.investments.calculationAlgorithm

import com.pawelcz.investments.investment.Investment
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import java.time.temporal.ChronoUnit



class OnTheDayOfTheCalculationAlgorithm : Algorithm("OnTheDayOfTheCalculationAlgorithm") {
    override fun calculation(investment: Investment) : BigDecimal {

        val capitalizationsPerYear = investment.CapitalizationPeriod().capitalizationsPerYear
        val interestRate = BigDecimal("1.0").add(investment.getInterestRate()
            .divide(BigDecimal("100.0").multiply(BigDecimal(capitalizationsPerYear.toString())), 2 , RoundingMode.CEILING))
        val periodInDays = ChronoUnit.DAYS.between(investment.StartDate(), LocalDate.now()).toInt()
        var daysPerCapitalization = 0
        when(capitalizationsPerYear){
            12 -> daysPerCapitalization = 30
            4 -> daysPerCapitalization = 90
            2 -> daysPerCapitalization = 180
            1 -> daysPerCapitalization = 360
        }

        return when(val numberOfCapitalizations = periodInDays / daysPerCapitalization){
            0 -> BigDecimal("1.0")
            else -> interestRate.pow(numberOfCapitalizations)
        }
    }
}