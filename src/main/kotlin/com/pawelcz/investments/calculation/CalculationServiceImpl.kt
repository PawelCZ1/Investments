package com.pawelcz.investments.calculation

import com.pawelcz.investments.investment.Investment
import com.pawelcz.investments.investment.InvestmentService
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*

@Service
class CalculationServiceImpl(private val calculationRepository: CalculationRepository,
                             private val investmentService: InvestmentService
) : CalculationService {

    override fun allCalculations(): List<Calculation> = calculationRepository.findAll()

    override fun historicalCalculationsOfTheParticularInvestment(investmentId: Long): List<Calculation> {
        val investment = investmentService.getInvestmentWithId(investmentId)
        if(investment.isEmpty)
            throw RuntimeException("Investment with the specified ID does not exist")
        return investment.get().calculationList()
    }

    override fun addCalculation(calculation: Calculation) = calculationRepository.save(calculation)

    override fun addCalculation(investmentId: Long, amount: BigDecimal, algorithmType: Char): Calculation {
        val optionalInvestment = investmentService.getInvestmentWithId(investmentId)
        if(optionalInvestment.isEmpty)
            throw RuntimeException("Investment with the specified ID does not exist")
        val investment = optionalInvestment.get()
        when(investment.isActual()){
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