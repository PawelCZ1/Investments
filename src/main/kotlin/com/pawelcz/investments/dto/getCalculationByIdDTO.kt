package com.pawelcz.investments.dto

import java.math.BigDecimal
import java.time.LocalDate

interface getCalculationByIdDTO {
    fun getAmount() : BigDecimal
    fun getCalculation_Date() : LocalDate
    fun getInvestment_Id() : Long
    fun getAlgorithm_Type() : Char
    fun getProfit() : BigDecimal
}