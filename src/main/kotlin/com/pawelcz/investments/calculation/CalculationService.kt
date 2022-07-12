package com.pawelcz.investments.calculation



import com.pawelcz.investments.dto.CalculationParametersDTO
import com.pawelcz.investments.dto.CalculationListForTheParticularInvestmentDTO
import com.pawelcz.investments.dto.GetCalculationByIdDTO
import com.pawelcz.investments.dto.SelectEverythingFromInvestmentDTO

interface CalculationService {

    fun historicalCalculationsOfTheParticularInvestment(investmentId : Long) : Pair<SelectEverythingFromInvestmentDTO,
            List<CalculationListForTheParticularInvestmentDTO>>
    fun addCalculation(calculation: Calculation) : Calculation
    fun addCalculation(investmentId : Long, calculationParametersDTO: CalculationParametersDTO) : GetCalculationByIdDTO
    fun getCalculationById(id : Long) : GetCalculationByIdDTO
    fun calculationListForTheParticularInvestment(id : Long) : List<CalculationListForTheParticularInvestmentDTO>

    fun clearTable()
}