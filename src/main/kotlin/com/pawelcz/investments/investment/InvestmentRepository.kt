package com.pawelcz.investments.investment

import org.springframework.data.jpa.repository.JpaRepository

interface InvestmentRepository : JpaRepository<Investment, Long> { }