package com.pawelcz.investments.investment

import org.springframework.stereotype.Service
import java.util.*



@Service
class InvestmentServiceImpl(private val investmentRepository: InvestmentRepository) : InvestmentService {

    override fun availableInvestments(): List<Any> = investmentRepository.availableInvestments().toList()


    override fun allInvestments(): List<Any> = investmentRepository.allInvestments().toList()


    override fun addInvestment(investment: Investment): Any{
        investmentRepository.save(investment)
        return investment.getId()?.let { getInvestment(it) }!!
    }


    override fun getInvestmentWithId(id: Long): Optional<Investment> = investmentRepository.findById(id)

    override fun getInvestmentById(id: Long) = investmentRepository.getInvestmentById(id)

    override fun getInvestment(id: Long) = investmentRepository.getInvestment(id)

    override fun clearTable() {
        investmentRepository.deleteAll()
    }





}