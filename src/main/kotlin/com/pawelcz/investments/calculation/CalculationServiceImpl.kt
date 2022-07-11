package com.pawelcz.investments.calculation

import com.fasterxml.jackson.databind.RuntimeJsonMappingException
import com.pawelcz.investments.dto.calculationListForTheParticularInvestmentDTO
import com.pawelcz.investments.dto.selectEverythingFromInvestmentDTO
import com.pawelcz.investments.investment.InvestmentService
import org.springframework.stereotype.Service
import java.math.BigDecimal


@Service
class CalculationServiceImpl(private val calculationRepository: CalculationRepository,
                             private val investmentService: InvestmentService
) : CalculationService {

    override fun historicalCalculationsOfTheParticularInvestment(investmentId: Long): Pair<selectEverythingFromInvestmentDTO, List<calculationListForTheParticularInvestmentDTO>>{
        try{
            return Pair(investmentService.selectEverythingFromInvestment(investmentId), calculationListForTheParticularInvestment(investmentId))
        }catch (e : RuntimeException){
            throw IllegalArgumentException("Investment with the specified ID does not exist")
        }
    }

    override fun addCalculation(calculation: Calculation) = calculationRepository.save(calculation)

    override fun addCalculation(investmentId: Long, calculationParameters: CalculationParameters): Any {
        val investment = investmentService.getInvestmentWithId(investmentId)
        val amount = calculationParameters.getAmount()
        if(amount.compareTo(BigDecimal.ZERO) == -1)
            throw IllegalArgumentException("Amount cannot be negative")
        val algorithmType = calculationParameters.getAlgorithmType()
        when(investment.Available()){
            true -> {
                val calculation = Calculation(amount, investment, algorithmType)
                addCalculation(calculation)
                return getCalculationById(calculation.getId()!!)
            }
            false -> throw IllegalArgumentException("Investment with the specified ID is no longer available")
        }
    }



    override fun getCalculationById(id: Long) = calculationRepository.getCalculationById(id)

    override fun calculationListForTheParticularInvestment(id: Long)
    = calculationRepository.calculationListForTheParticularInvestment(id)

    override fun clearTable() {
        calculationRepository.deleteAll()
    }





}