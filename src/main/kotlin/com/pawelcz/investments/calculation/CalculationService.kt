package com.pawelcz.investments.calculation



import com.pawelcz.investments.investment.Investment
import java.util.*

interface CalculationService {

    fun historicalCalculationsOfTheParticularInvestment(investmentId : Long) : Pair<Any, List<Any>>
    fun addCalculation(calculation: Calculation) : Calculation
    fun addCalculation(investmentId : Long, calculationParameters: CalculationParameters) : Any
    fun getCalculationById(id : Long) : Any
    fun calculationListForTheParticularInvestment(id : Long) : List<Any>
}