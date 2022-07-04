package com.pawelcz.investments.investment

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query


interface InvestmentRepository : JpaRepository<Investment, Long> {
    @Query("SELECT id, name FROM investment", nativeQuery = true)
    fun allInvestments() : List<Any>

    @Query("SELECT id, name FROM investment WHERE end_date >= CURRENT_DATE", nativeQuery = true)
    fun availableInvestments() : List<Any>

    @Query("SELECT id, name , interest_rate, capitalization_period, start_date, end_date FROM investment WHERE id = ?1", nativeQuery = true)
    fun selectEverythingFromInvestment(id : Long) : Any

    @Query("SELECT id, name, interest_rate, (end_date - start_date) as days FROM investment WHERE id = ?1", nativeQuery = true)
    fun selectLessFromInvestment(id: Long) : Any

}