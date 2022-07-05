package com.pawelcz.investments.investment

import org.springframework.stereotype.Service
import java.math.BigDecimal


@Service
class InvestmentServiceImpl(private val investmentRepository: InvestmentRepository) : InvestmentService {

    override fun availableInvestments(): List<Any> = investmentRepository.availableInvestments()


    override fun allInvestments(): List<Any> = investmentRepository.allInvestments()


    override fun addInvestment(investment: Investment): Any{
        if(investment.getInterestRate().compareTo(BigDecimal.ONE) == -1)
            throw RuntimeException("Interest rate shouldn't be lesser than one")

        investmentRepository.save(investment)
        return investmentRepository.selectLessFromInvestment(investment.getId()!!)
    }


    override fun getInvestmentWithId(id: Long): Investment {
        val investment = investmentRepository.findById(id)
        if(investment.isEmpty)
            throw RuntimeException("Investment with the specified ID does not exist")
        return investment.get()
    }

    override fun selectEverythingFromInvestment(id: Long): Any {
        val investment = investmentRepository.selectEverythingFromInvestment(id)
        if(investment.equals(null))
            throw RuntimeException("Investment with the specified ID does not exist")
        return investment
    }


    override fun selectLessFromInvestment(id: Long) = investmentRepository.selectLessFromInvestment(id)

    override fun clearTable() {
        investmentRepository.deleteAll()
    }





}