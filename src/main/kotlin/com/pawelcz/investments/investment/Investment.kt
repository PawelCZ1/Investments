package com.pawelcz.investments.investment

import com.pawelcz.investments.AbstractJpaPersistable
import com.pawelcz.investments.calculation.Calculation
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.OneToMany

@Entity
class Investment(
    private var name: String,
    private var interestRate: BigDecimal,
    private var capitalizationPeriod: CapitalizationPeriodInMonths,
    private var startDate: LocalDate,
    private var endDate: LocalDate
) : AbstractJpaPersistable<Long>() {

    @OneToMany(mappedBy = "investment", fetch = FetchType.LAZY)
    private lateinit var calculations : List<Calculation>
    fun getName() = name
    fun getInterestRate() = interestRate
    fun getCapitalizationPeriod() = capitalizationPeriod
    fun getStartDate() = startDate
    fun getEndDate() = endDate

    fun calculationList() = calculations


}