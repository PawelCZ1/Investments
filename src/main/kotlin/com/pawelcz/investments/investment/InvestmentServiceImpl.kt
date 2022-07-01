package com.pawelcz.investments.investment

import org.springframework.stereotype.Service
import java.util.*



@Service
class InvestmentServiceImpl(private val investmentRepository: InvestmentRepository) : InvestmentService {

    override fun availableInvestments(): Collection<Any> = investmentRepository.availableInvestments()


    override fun allInvestments(): Collection<Any> = investmentRepository.allInvestments()


    override fun addInvestment(investment: Investment): Investment = investmentRepository.save(investment)


    override fun getInvestmentWithId(id: Long): Optional<Investment> = investmentRepository.findById(id)

    override fun getInvestmentById(id: Long) = investmentRepository.getInvestmentById(id)



}