package com.pawelcz.investments.investment

import com.pawelcz.investments.dto.allAndAvailableInvestmentsDTO
import com.pawelcz.investments.dto.selectEverythingFromInvestmentDTO
import com.pawelcz.investments.dto.selectLessFromInvestmentDTO


interface InvestmentService {

    fun availableInvestments() : List<allAndAvailableInvestmentsDTO>

    fun allInvestments() : List<allAndAvailableInvestmentsDTO>

    fun addInvestment(investment: Investment) : Any

    fun getInvestmentWithId(id : Long) : Investment

    fun selectEverythingFromInvestment(id : Long) : selectEverythingFromInvestmentDTO

    fun selectLessFromInvestment(id: Long) : selectLessFromInvestmentDTO

    fun clearTable()
}