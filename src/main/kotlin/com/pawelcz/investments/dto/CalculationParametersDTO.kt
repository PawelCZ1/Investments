package com.pawelcz.investments.dto

import com.pawelcz.investments.calculationAlgorithm.AlgorithmType
import java.math.BigDecimal

class CalculationParametersDTO(private val amount : BigDecimal,
                               private val algorithmType : AlgorithmType
) {
    fun getAmount() = amount
    fun getAlgorithmType() = algorithmType
}