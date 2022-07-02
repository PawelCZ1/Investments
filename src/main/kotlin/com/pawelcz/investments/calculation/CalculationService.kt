package com.pawelcz.investments.calculation



import com.pawelcz.investments.investment.Investment
import java.util.*

interface CalculationService {

    fun allCalculations() : List<Calculation>
    fun calculationList() : List<Calculation>
    fun calculationListWithTheParticularId(investmentId : Long) : List<Calculation>
    fun historicalCalculationsOfTheParticularInvestment(investmentId : Long) : Pair<Any, Collection<Any>>
    fun addCalculation(calculation: Calculation) : Calculation
    fun addCalculation(investmentId : Long, calculationParameters: CalculationParameters) : Any
    fun getCalculation(calculationId : Long) : Optional<Calculation>

    fun getCalculationById(id : Long) : Any

    fun calculationListForTheParticularInvestment(id : Long) : Collection<Any>
}