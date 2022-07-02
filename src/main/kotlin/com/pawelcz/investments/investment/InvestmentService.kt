package com.pawelcz.investments.investment

import java.util.*


interface InvestmentService {

    fun availableInvestments() : Collection<Any>

    fun allInvestments() : Collection<Any>

    fun addInvestment(investment: Investment) : Any

    fun getInvestmentWithId(id : Long) : Optional<Investment>

    fun getInvestmentById(id : Long) : Any

    fun getInvestment(id: Long) : Any
}