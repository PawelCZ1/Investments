package com.pawelcz.investments.investment


interface InvestmentService {

    fun availableInvestments() : List<Any>

    fun allInvestments() : List<Any>

    fun addInvestment(investment: Investment) : Any

    fun getInvestmentWithId(id : Long) : Investment

    fun selectEverythingFromInvestment(id : Long) : Any

    fun selectLessFromInvestment(id: Long) : Any

    fun clearTable()
}