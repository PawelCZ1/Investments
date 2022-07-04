package com.pawelcz.investments.investment

import java.util.*


interface InvestmentService {

    fun availableInvestments() : List<Any>

    fun allInvestments() : List<Any>

    fun addInvestment(investment: Investment) : Any

    fun getInvestmentWithId(id : Long) : Optional<Investment>

    fun getInvestmentById(id : Long) : Any

    fun getInvestment(id: Long) : Any

    fun clearTable()
}