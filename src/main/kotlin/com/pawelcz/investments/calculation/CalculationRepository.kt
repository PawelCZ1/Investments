package com.pawelcz.investments.calculation

import com.pawelcz.investments.dto.calculationListForTheParticularInvestmentDTO
import com.pawelcz.investments.dto.getCalculationByIdDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CalculationRepository : JpaRepository<Calculation, Long> {
    @Query("SELECT amount, calculation_date, investment_id, algorithm_type, profit FROM calculation WHERE id = ?1", nativeQuery = true)
    fun getCalculationById(id : Long) : getCalculationByIdDTO
    @Query("SELECT amount, calculation_date, algorithm_type, profit FROM calculation WHERE investment_id = ?1", nativeQuery = true)
    fun calculationListForTheParticularInvestment(id : Long) : List<calculationListForTheParticularInvestmentDTO>
}