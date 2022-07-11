package com.pawelcz.investments.dto

import com.pawelcz.investments.calculationAlgorithm.AlgorithmType
import java.math.BigDecimal
import java.time.LocalDate

interface getCalculationByIdDTO {
    fun getAmount() : BigDecimal
    fun getCalculation_Date() : LocalDate
    fun getInvestment_Id() : Long
    fun getAlgorithm_Type() : AlgorithmType
    fun getProfit() : BigDecimal
}