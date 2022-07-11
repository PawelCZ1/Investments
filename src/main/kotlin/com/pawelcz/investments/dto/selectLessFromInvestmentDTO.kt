package com.pawelcz.investments.dto

import java.math.BigDecimal

interface selectLessFromInvestmentDTO {
    fun getId() : Long
    fun getName(): String
    fun getInterest_Rate() : BigDecimal
    fun getDays() : Int
}