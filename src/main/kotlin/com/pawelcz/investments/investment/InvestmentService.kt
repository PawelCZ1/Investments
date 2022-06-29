package com.pawelcz.investments.investment

interface InvestmentService {

    fun availableInvestments() : List<Investment>

    fun addInvestment(investment: Investment) : Investment


}