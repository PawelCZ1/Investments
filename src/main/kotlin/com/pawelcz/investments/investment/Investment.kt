package com.pawelcz.investments.investment

import com.fasterxml.jackson.annotation.JsonIgnore
import com.pawelcz.investments.AbstractJpaPersistable
import com.pawelcz.investments.calculation.Calculation
import java.math.BigDecimal
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import javax.persistence.*


@Entity
@Table(name= "investment")
class Investment(
    @Column(name = "name", nullable = false)
    private var name: String,
    @Column(name = "interest_rate", nullable = false)
    private var interestRate: BigDecimal,
    @Enumerated(EnumType.STRING)
    @Column(name = "capitalization_period", nullable = false)
    private var capitalizationPeriod: CapitalizationPeriodInMonths,
    @Column(name = "start_date", nullable = false)
    private var startDate: LocalDate,
    @Column(name = "end_date", nullable = false)
    private var endDate: LocalDate
) : AbstractJpaPersistable<Long>() {

    @OneToMany(mappedBy = "investment", fetch = FetchType.LAZY)
    private lateinit var calculations : List<Calculation>

    @Transient
    private val periodInDays : Int = ChronoUnit.DAYS.between(startDate, endDate).toInt()
    fun getName() = name
    fun getInterestRate() = interestRate

    fun getCapitalizationPeriod() = capitalizationPeriod
    fun getStartDate() = startDate
    fun getEndDate() = endDate
    @JsonIgnore
    fun getPeriodInDays() = ChronoUnit.DAYS.between(startDate, endDate).toInt()

    fun available() = endDate >= LocalDate.now()

    fun calculationList() = calculations

}