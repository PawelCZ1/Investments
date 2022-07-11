package com.pawelcz.investments.dto

import java.math.BigDecimal
import java.time.LocalDate

interface calculationListForTheParticularInvestmentDTO {
    fun getAmount() : BigDecimal
    fun getCalculation_Date() : LocalDate
    fun getAlgorithm_Type() : Char
    fun getProfit() : BigDecimal
}