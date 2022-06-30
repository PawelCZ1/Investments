package com.pawelcz.investments.investment

import org.springframework.stereotype.Service
import java.util.*


@Service
class InvestmentServiceImpl(private val investmentRepository: InvestmentRepository) : InvestmentService {

    override fun availableInvestments(): List<Investment> = allInvestments().filter { element -> element.isActual() }


    override fun allInvestments(): List<Investment> = investmentRepository.findAll()


    override fun addInvestment(investment: Investment): Investment = investmentRepository.save(investment)


    override fun getInvestmentWithId(id: Long): Optional<Investment> = investmentRepository.findById(id)

}