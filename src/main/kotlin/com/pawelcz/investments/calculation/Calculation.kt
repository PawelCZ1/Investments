package com.pawelcz.investments.calculation

import com.pawelcz.investments.AbstractJpaPersistable
import com.pawelcz.investments.calculationAlgorithm.AlgorithmFactory
import com.pawelcz.investments.investment.Investment
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Calculation(
    private var amount : BigDecimal,
    private var calculationDate : LocalDate,
    @ManyToOne
    @JoinColumn(name = "investmentId", nullable = false)
    private var investment: Investment,
    private var algorithmType: Char,
    private var profit : BigDecimal
) : AbstractJpaPersistable<Long>()  {

    init{
        calculationDate = LocalDate.now()
        calculateProfit()
    }

    fun getAmount() = amount
    fun getCalculationDate() = calculationDate
    fun getInvestment() = investment
    fun getAlgorithmType() = algorithmType
    fun getProfit() = profit

    fun calculateProfit(){
        val algorithmFactory = AlgorithmFactory()
        val algorithm = algorithmFactory.makeAlgorithm(algorithmType)
        profit = (amount.multiply(algorithm.calculation(investment))).subtract(amount)
    }


}