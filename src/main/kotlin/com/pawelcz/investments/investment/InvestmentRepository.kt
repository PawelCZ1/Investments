package com.pawelcz.investments.investment

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query


interface InvestmentRepository : JpaRepository<Investment, Long> {
    @Query("SELECT id, name FROM Investment")
    fun allInvestments() : Collection<Any>

    @Query("SELECT id, name FROM Investment WHERE endDate >= current_date")
    fun availableInvestments() : Collection<Any>

    @Query("SELECT id, name, interestRate, capitalizationPeriod, startDate, endDate FROM Investment WHERE id = ?1")
    fun getInvestmentById(id : Long) : Any

    @Query("SELECT id, name, interest_rate, (end_date - start_date) as days FROM Investment WHERE id = ?1", nativeQuery = true)
    fun getInvestment(id: Long) : Any

}