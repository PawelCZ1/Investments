package com.pawelcz.investments.investment

import com.fasterxml.jackson.annotation.JsonFormat
import com.pawelcz.investments.AbstractJpaPersistable
import com.pawelcz.investments.calculation.Calculation
import java.math.BigDecimal
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import javax.persistence.*

@Entity
class Investment(
    private var name: String,
    private var interestRate: BigDecimal,
    @Enumerated(EnumType.STRING)
    private var capitalizationPeriod: CapitalizationPeriodInMonths,
    @JsonFormat(pattern="yyyy-MM-dd")
    private var startDate: LocalDate,
    @JsonFormat(pattern="yyyy-MM-dd")
    private var endDate: LocalDate
) : AbstractJpaPersistable<Long>() {

    @OneToMany(mappedBy = "investment", fetch = FetchType.LAZY)
    private lateinit var calculations : List<Calculation>
    @Transient
    private val periodInDays = ChronoUnit.DAYS.between(startDate, endDate).toInt()
    fun getName() = name
    fun getInterestRate() = interestRate

    fun CapitalizationPeriod() = capitalizationPeriod
    fun StartDate() = startDate
    fun EndDate() = endDate

    fun getPeriodInDays() = periodInDays

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