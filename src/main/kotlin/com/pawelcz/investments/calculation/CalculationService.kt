package com.pawelcz.investments.calculation



import java.util.*

interface CalculationService {

    fun allCalculations() : List<Calculation>
    fun historicalCalculationsOfTheParticularInvestment(investmentId : Long) : List<Calculation>
    fun addCalculation(calculation: Calculation) : Calculation
    fun addCalculation(investmentId : Long, calculationParameters: CalculationParameters) : Calculation
    fun getCalculation(calculationId : Long) : Optional<Calculation>
}