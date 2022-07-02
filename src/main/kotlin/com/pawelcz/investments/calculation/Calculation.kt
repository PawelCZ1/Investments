package com.pawelcz.investments.calculation

import com.pawelcz.investments.AbstractJpaPersistable
import com.pawelcz.investments.calculationAlgorithm.AlgorithmFactory
import com.pawelcz.investments.investment.Investment
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Calculation(
    private var amount : BigDecimal,
    @ManyToOne
    @JoinColumn(name = "investmentId", nullable = false)
    private var investment: Investment,
    private var algorithmType: Char,
    private var profit : BigDecimal
) : AbstractJpaPersistable<Long>()  {

    companion object{
        fun calculateProfit(amount : BigDecimal, investment: Investment, algorithmType: Char) : BigDecimal {
            val algorithmFactory = AlgorithmFactory()
            val algorithm = algorithmFactory.makeAlgorithm(algorithmType)
            val profit = (amount.multiply(algorithm.calculation(investment))).subtract(amount)
            return profit.setScale(2, RoundingMode.CEILING)
        }

    }


    private var calculationDate : LocalDate = LocalDate.now()


    fun getAmount() = amount
    fun getCalculationDate() = calculationDate
    fun getInvestment() = investment
    fun getAlgorithmType() = algorithmType
    fun getProfit() = profit







    override fun toString(): String {
        return "{" +
                "amount=$amount" +
                ", investmentId=${investment.getId()}" +
                ", algorithmType=$algorithmType" +
                ", profit=$profit" +
                ", calculationDate=$calculationDate" +
                "}"
    }


}