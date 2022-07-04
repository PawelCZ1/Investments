package com.pawelcz.investments.investment

import com.fasterxml.jackson.annotation.JsonFormat
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
    @JoinColumn(name = "name", nullable = false)
    private var name: String,
    @JoinColumn(name = "interest_rate", nullable = false)
    private var interestRate: BigDecimal,
    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "capitalization_period", nullable = false)
    private var capitalizationPeriod: CapitalizationPeriodInMonths,
    @JoinColumn(name = "start_date", nullable = false)
    private var startDate: LocalDate,
    @JoinColumn(name = "end_date", nullable = false)
    private var endDate: LocalDate
) : AbstractJpaPersistable<Long>() {

    @OneToMany(mappedBy = "investment", fetch = FetchType.LAZY)
    private lateinit var calculations : List<Calculation>

    @Transient
    private val periodInDays : Int = 0
    fun getName() = name
    fun getInterestRate() = interestRate

    fun CapitalizationPeriod() = capitalizationPeriod
    fun StartDate() = startDate
    fun EndDate() = endDate

    fun getPeriodInDays() = ChronoUnit.DAYS.between(startDate, endDate).toInt()

    fun Available() = endDate >= LocalDate.now()





    fun calculationList() = calculations
    override fun toString(): String {
        return "{" +
                "id='${getId()}'" +
                ", name='$name'" +
                ", interestRate=$interestRate" +
                ", periodInDays=$periodInDays" +
                "}"

    }


}