package com.pawelcz.investments.investment

import com.pawelcz.investments.dto.AllAndAvailableInvestmentsDTO
import com.pawelcz.investments.dto.SelectEverythingFromInvestmentDTO
import com.pawelcz.investments.dto.SelectLessFromInvestmentDTO


interface InvestmentService {

    fun availableInvestments() : List<AllAndAvailableInvestmentsDTO>

    fun allInvestments() : List<AllAndAvailableInvestmentsDTO>

    fun addInvestment(investment: Investment) : Any

    fun getInvestmentWithId(id : Long) : Investment

    fun selectEverythingFromInvestment(id : Long) : SelectEverythingFromInvestmentDTO

    fun selectLessFromInvestment(id: Long) : SelectLessFromInvestmentDTO

    fun clearTable()
}