package com.pawelcz.investments.calculation

import org.springframework.data.jpa.repository.JpaRepository

interface CalculationRepository : JpaRepository<Calculation, Long> { }