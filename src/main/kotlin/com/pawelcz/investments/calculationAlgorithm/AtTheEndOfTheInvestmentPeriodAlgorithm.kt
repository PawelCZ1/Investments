package com.pawelcz.investments.calculationAlgorithm

import com.pawelcz.investments.investment.Investment
import java.time.temporal.ChronoUnit

class AtTheEndOfTheInvestmentPeriodAlgorithm : Algorithm("AtTheEndOfTheInvestmentPeriodAlgorithm") {
    override fun calculation(investment: Investment) : Double {
        var interest = 1.0
        val interestRate = investment.getInterestRate()
        var periodInDays = ChronoUnit.DAYS.between(investment.getStartDate(),investment.getEndDate())
        val capitalizationPeriod = investment.getCapitalizationPeriod().toDays
        while (periodInDays - capitalizationPeriod >= 0){
            interest *= interestRate
            periodInDays -= capitalizationPeriod
        }
        return interest
    }
}