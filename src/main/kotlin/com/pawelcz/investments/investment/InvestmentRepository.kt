package com.pawelcz.investments.investment

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface InvestmentRepository : JpaRepository<Investment, Long> {
    @Query("SELECT id, name FROM Investment")
    fun allInvestments() : List<Investment>

    @Query("SELECT id, name FROM Investment WHERE endDate >= current_date")
    fun availableInvestments() : List<Investment>

}