package com.pawelcz.investments.calculation

import com.pawelcz.investments.AbstractJpaPersistable
import com.pawelcz.investments.calculationAlgorithm.AlgorithmFactory
import com.pawelcz.investments.calculationAlgorithm.AlgorithmType
import com.pawelcz.investments.investment.Investment
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name= "calculation")
class Calculation(
    @Column(name = "amount", nullable = false)
    private var amount : BigDecimal,
    @ManyToOne
    @JoinColumn(name = "investment_id", nullable = false)
    private var investment: Investment,
    @Column(name = "algorithm_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private var algorithmType: AlgorithmType,

) : AbstractJpaPersistable<Long>()  {


    companion object{
        fun calculateProfit(amount : BigDecimal, investment: Investment, algorithmType: AlgorithmType) : BigDecimal {
            val algorithmFactory = AlgorithmFactory()
            val algorithm = algorithmFactory.makeAlgorithm(algorithmType)
            val profit = (amount.multiply(algorithm.calculation(investment))).subtract(amount)
            return profit.setScale(2, RoundingMode.CEILING)
        }

    }
    @Column(name = "profit", nullable = false)
    private var profit = calculateProfit(amount, investment, algorithmType)

    @Column(name = "calculation_date", nullable = false)
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