package com.pawelcz.investments.calculationAlgorithm

import com.pawelcz.investments.investment.Investment
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class OnTheDayOfTheCalculationAlgorithm : Algorithm("OnTheDayOfTheCalculationAlgorithm") {
    override fun calculation(investment: Investment) : Double {
        var interest = 1.0
        val interestRate = investment.getInterestRate()
        var periodInDays = ChronoUnit.DAYS.between(investment.getStartDate(), LocalDate.now())
        val capitalizationPeriod = investment.getCapitalizationPeriod().toDays
        while (periodInDays - capitalizationPeriod >= 0){
            interest *= interestRate
            periodInDays -= capitalizationPeriod
        }
        return interest
    }
}