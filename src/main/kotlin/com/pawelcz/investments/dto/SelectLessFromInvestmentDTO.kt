package com.pawelcz.investments.dto

import java.math.BigDecimal

interface SelectLessFromInvestmentDTO {
    fun getId() : Long
    fun getName(): String
    fun getInterest_Rate() : BigDecimal
    fun getDays() : Int
}