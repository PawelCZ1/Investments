package com.pawelcz.investments


import com.pawelcz.investments.calculation.CalculationParameters
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

    @GetMapping("/investments")
    fun availableInvestments() = investmentService.availableInvestments()

    @GetMapping("/investments/archive")
    fun allInvestments() = investmentService.allInvestments()

    @PostMapping("/investments")
    fun addInvestment(@RequestBody investment: Investment) = investmentService.addInvestment(investment)

    @PostMapping("/investments/{id}/calculations")
    fun calculation(@PathVariable id : Long,
                    @RequestBody calculationParameters: CalculationParameters) =
        calculationService.addCalculation(id, calculationParameters)

    @GetMapping("/investments/{id}/calculations")
    fun historicalCalculationsOfTheParticularInvestment(
        @PathVariable id: Long) = calculationService.historicalCalculationsOfTheParticularInvestment(id)

}