package com.pawelcz.investments.calculation


import java.math.BigDecimal
import java.util.*

interface CalculationService {

    fun allCalculations() : List<Calculation>
    fun historicalCalculationsOfTheParticularInvestment(investmentId : Long) : List<Calculation>
    fun addCalculation(calculation: Calculation) : Calculation
    fun addCalculation(investmentId : Long, amount : BigDecimal ,algorithmType : Char) : Calculation
    fun getCalculation(calculationId : Long) : Optional<Calculation>
}