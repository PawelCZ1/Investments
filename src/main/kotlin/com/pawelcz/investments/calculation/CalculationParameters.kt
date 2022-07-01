package com.pawelcz.investments.calculation

import java.math.BigDecimal

class CalculationParameters(private val amount : BigDecimal,
                            private val algorithmType : Char
) {
    fun getAmount() = amount
    fun getAlgorithmType() = algorithmType
}