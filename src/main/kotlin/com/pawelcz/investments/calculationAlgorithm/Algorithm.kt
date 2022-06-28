package com.pawelcz.investments.calculationAlgorithm

import com.pawelcz.investments.investment.Investment

abstract class Algorithm(
    var name : String
) {

    abstract fun calculation(investment: Investment) : Double






}