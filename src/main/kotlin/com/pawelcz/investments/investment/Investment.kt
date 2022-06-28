package com.pawelcz.investments.investment

import java.time.LocalDate

class Investment(
    private var name: String,
    private var interestRate: Double,
    private var capitalizationPeriod: CapitalizationPeriodInMonths,
    private var startDate: LocalDate,
    private var endDate: LocalDate
) {

    fun getName() = name
    fun getInterestRate() = interestRate
    fun getCapitalizationPeriod() = capitalizationPeriod
    fun getStartDate() = startDate
    fun getEndDate() = endDate


}