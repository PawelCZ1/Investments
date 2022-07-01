package com.pawelcz.investments.calculation

import com.pawelcz.investments.investment.Investment
import com.pawelcz.investments.investment.InvestmentService
import org.springframework.stereotype.Service
import java.util.*

@Service
class CalculationServiceImpl(private val calculationRepository: CalculationRepository,
                             private val investmentService: InvestmentService
) : CalculationService {

    override fun allCalculations(): List<Calculation> = calculationRepository.findAll()

    override fun calculationList(): List<Calculation> = calculationRepository.calculationList()

    override fun calculationListWithTheParticularId(investmentId: Long) = calculationList()
        .filter { element -> element.getInvestment().getId() == investmentId }

    override fun historicalCalculationsOfTheParticularInvestment(investmentId: Long): Pair<Any, List<Calculation>>{
        val investment = investmentService.getInvestmentWithId(investmentId)
        if(investment.isEmpty)
            throw RuntimeException("Investment with the specified ID does not exist")

        return Pair(investmentService.getInvestmentById(investmentId), calculationListWithTheParticularId(investmentId))
    }

    override fun addCalculation(calculation: Calculation) = calculationRepository.save(calculation)

    override fun addCalculation(investmentId: Long, calculationParameters: CalculationParameters): Calculation {
        val optionalInvestment = investmentService.getInvestmentWithId(investmentId)
        if(optionalInvestment.isEmpty)
            throw RuntimeException("Investment with the specified ID does not exist")
        val investment = optionalInvestment.get()
        val amount = calculationParameters.getAmount()
        val algorithmType = calculationParameters.getAlgorithmType()
        when(investment.Available()){
            true -> {
                val calculation = Calculation(amount, investment, algorithmType)
                addCalculation(calculation)
                return calculation
            }
            false -> throw RuntimeException("Investment with the specified ID is no longer available")
        }
    }

    override fun getCalculation(calculationId: Long): Optional<Calculation> = calculationRepository.findById(calculationId)


}