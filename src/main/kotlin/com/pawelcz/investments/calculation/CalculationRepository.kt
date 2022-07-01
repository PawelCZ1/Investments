package com.pawelcz.investments.calculation

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CalculationRepository : JpaRepository<Calculation, Long> {

    @Query("SELECT amount, calculationDate, algorithmType, profit FROM Calculation")
    fun calculationList() : List<Calculation>
}