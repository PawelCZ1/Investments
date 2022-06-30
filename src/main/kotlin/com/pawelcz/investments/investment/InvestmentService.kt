package com.pawelcz.investments.investment

import java.util.*

interface InvestmentService {

    fun availableInvestments() : List<Investment>

    fun allInvestments() : List<Investment>

    fun addInvestment(investment: Investment) : Investment

    fun getInvestmentWithId(id : Long) : Optional<Investment>


}