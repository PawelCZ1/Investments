package com.pawelcz.investments.dto

import com.pawelcz.investments.investment.CapitalizationPeriodInMonths
import java.math.BigDecimal
import java.time.LocalDate

interface selectEverythingFromInvestmentDTO {
    fun getId() : Long
    fun getName() : String
    fun getInterest_Rate() : BigDecimal
    fun getCapitalization_Period() : CapitalizationPeriodInMonths
    fun getStart_Date() : LocalDate
    fun getEnd_Date() : LocalDate
}