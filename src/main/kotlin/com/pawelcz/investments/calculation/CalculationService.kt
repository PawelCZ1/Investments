package com.pawelcz.investments.calculation


import java.math.BigDecimal

interface CalculationService {

    fun historicalCalculationsOfTheParticularInvestment(investmentId : Long) : List<Calculation>

    fun addCalculation(investmentId : Long, amount : BigDecimal ,algorithmType : Char) : Calculation
}