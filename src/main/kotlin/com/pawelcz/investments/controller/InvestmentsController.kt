package com.pawelcz.investments.controller


import com.pawelcz.investments.dto.CalculationParametersDTO
import com.pawelcz.investments.calculation.CalculationService
import com.pawelcz.investments.investment.Investment
import com.pawelcz.investments.investment.InvestmentService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class InvestmentsController(private val investmentService: InvestmentService,
                            private val calculationService: CalculationService
) {

    // shows an id and the name of the available investments
    @GetMapping("/investments")
    fun availableInvestments() = investmentService.availableInvestments()

    // shows an id and the name of the all investments
    @GetMapping("/investments/archive")
    fun allInvestments() = investmentService.allInvestments()

    // adding new investment and returning its id, name, interest rate and period in days
    @PostMapping("/investments")
    fun addInvestment(@RequestBody investment: Investment) = investmentService.addInvestment(investment)

    // adding new calculation and returning its properties
    @PostMapping("/investments/{id}/calculations")
    fun calculation(@PathVariable id : Long,
                    @RequestBody calculationParametersDTO: CalculationParametersDTO
    ) =
        calculationService.addCalculation(id, calculationParametersDTO)

    // shows the particular investment and its historical calculations
    @GetMapping("/investments/{id}/calculations")
    fun historicalCalculationsOfTheParticularInvestment(
        @PathVariable id: Long) = calculationService.historicalCalculationsOfTheParticularInvestment(id)


}