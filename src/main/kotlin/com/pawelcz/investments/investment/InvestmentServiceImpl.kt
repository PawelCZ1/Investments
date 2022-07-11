package com.pawelcz.investments.investment

import com.pawelcz.investments.dto.allAndAvailableInvestmentsDTO
import com.pawelcz.investments.dto.selectEverythingFromInvestmentDTO
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.sql.ResultSet


@Service
class InvestmentServiceImpl(private val investmentRepository: InvestmentRepository) : InvestmentService {

    override fun availableInvestments(): List<allAndAvailableInvestmentsDTO> = investmentRepository.availableInvestments()

    override fun allInvestments(): List<allAndAvailableInvestmentsDTO> = investmentRepository.allInvestments()

    override fun addInvestment(investment: Investment): Any{
        if(investment.getInterestRate().compareTo(BigDecimal.ONE) == -1)
            throw IllegalArgumentException("Interest rate shouldn't be lesser than one")

        investmentRepository.save(investment)
        return investmentRepository.selectLessFromInvestment(investment.getId()!!)
    }


    override fun getInvestmentWithId(id: Long): Investment {
        val investment = investmentRepository.findById(id)
        if(investment.isEmpty)
            throw IllegalArgumentException("Investment with the specified ID does not exist")
        return investment.get()
    }

    override fun selectEverythingFromInvestment(id: Long): selectEverythingFromInvestmentDTO {
        val investment = investmentRepository.selectEverythingFromInvestment(id)
        if(investment.equals(null))
            throw IllegalArgumentException("Investment with the specified ID does not exist")
        return investment
    }


    override fun selectLessFromInvestment(id: Long) = investmentRepository.selectLessFromInvestment(id)

    override fun clearTable() {
        investmentRepository.deleteAll()
    }





}