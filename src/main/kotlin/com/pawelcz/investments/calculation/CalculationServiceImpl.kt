package com.pawelcz.investments.calculation

import com.pawelcz.investments.investment.InvestmentService
import org.springframework.stereotype.Service
import java.util.*

@Service
class CalculationServiceImpl(private val calculationRepository: CalculationRepository,
                             private val investmentService: InvestmentService
) : CalculationService {

    override fun historicalCalculationsOfTheParticularInvestment(investmentId: Long): Pair<Any, List<Any>>{
        val investment = investmentService.getInvestmentWithId(investmentId)
        if(investment.isEmpty)
            throw RuntimeException("Investment with the specified ID does not exist")

        return Pair(investmentService.getInvestmentById(investmentId), calculationListForTheParticularInvestment(investmentId))
    }

    override fun addCalculation(calculation: Calculation) = calculationRepository.save(calculation)

    override fun addCalculation(investmentId: Long, calculationParameters: CalculationParameters): Any {
        val investment = investmentService.getInvestmentWithId(investmentId)
        if(investment.isEmpty)
            throw RuntimeException("Investment with the specified ID does not exist")

        val amount = calculationParameters.getAmount()
        val algorithmType = calculationParameters.getAlgorithmType()

        when(investment.get().Available()){
            true -> {
                val calculation = Calculation(amount, investment.get(), algorithmType, Calculation.calculateProfit
                    (amount, investment.get(), algorithmType))
                addCalculation(calculation)
                return getCalculationById(calculation.getId()!!)
            }
            false -> throw RuntimeException("Investment with the specified ID is no longer available")
        }
    }



    override fun getCalculationById(id: Long) = calculationRepository.getCalculationById(id)

    override fun calculationListForTheParticularInvestment(id: Long)
    = calculationRepository.calculationListForTheParticularInvestment(id).toList()





}