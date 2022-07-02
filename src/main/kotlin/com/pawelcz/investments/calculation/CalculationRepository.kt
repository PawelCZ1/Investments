package com.pawelcz.investments.calculation

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CalculationRepository : JpaRepository<Calculation, Long> {

    @Query("SELECT amount, calculationDate, algorithmType, profit FROM Calculation")
    fun calculationList() : List<Calculation>

    @Query("SELECT amount, calculation_date, investment_id, algorithm_type, profit FROM Calculation WHERE id = ?1", nativeQuery = true)
    fun getCalculationById(id : Long) : Any
}