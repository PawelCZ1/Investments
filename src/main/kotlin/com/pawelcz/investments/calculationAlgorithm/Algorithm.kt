package com.pawelcz.investments.calculationAlgorithm


import com.pawelcz.investments.investment.Investment
import java.math.BigDecimal



abstract class Algorithm(
    var name : String

) {


    abstract fun calculation(investment: Investment) : BigDecimal








}