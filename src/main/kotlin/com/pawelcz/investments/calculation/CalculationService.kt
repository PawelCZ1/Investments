package com.pawelcz.investments.calculation



import com.pawelcz.investments.dto.CalculationParametersDTO
import com.pawelcz.investments.dto.calculationListForTheParticularInvestmentDTO
import com.pawelcz.investments.dto.getCalculationByIdDTO

interface CalculationService {

    fun historicalCalculationsOfTheParticularInvestment(investmentId : Long) : Pair<Any, List<Any>>
    fun addCalculation(calculation: Calculation) : Calculation
    fun addCalculation(investmentId : Long, calculationParametersDTO: CalculationParametersDTO) : Any
    fun getCalculationById(id : Long) : getCalculationByIdDTO
    fun calculationListForTheParticularInvestment(id : Long) : List<calculationListForTheParticularInvestmentDTO>

    fun clearTable()
}