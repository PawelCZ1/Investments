package com.pawelcz.investments.dto

import com.pawelcz.investments.calculationAlgorithm.AlgorithmType
import java.math.BigDecimal
import java.time.LocalDate

interface CalculationListForTheParticularInvestmentDTO {
    fun getAmount() : BigDecimal
    fun getCalculation_Date() : LocalDate
    fun getAlgorithm_Type() : AlgorithmType
    fun getProfit() : BigDecimal
}