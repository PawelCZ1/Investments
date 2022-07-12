package com.pawelcz.investments.investment

import com.pawelcz.investments.dto.AllAndAvailableInvestmentsDTO
import com.pawelcz.investments.dto.SelectEverythingFromInvestmentDTO
import com.pawelcz.investments.dto.SelectLessFromInvestmentDTO
import org.springframework.stereotype.Service
import java.math.BigDecimal


@Service
class InvestmentServiceImpl(private val investmentRepository: InvestmentRepository) : InvestmentService {

    override fun availableInvestments(): List<AllAndAvailableInvestmentsDTO> = investmentRepository.availableInvestments()

    override fun allInvestments(): List<AllAndAvailableInvestmentsDTO> = investmentRepository.allInvestments()

    override fun addInvestment(investment: Investment): SelectLessFromInvestmentDTO {
        if(investment.getInterestRate().compareTo(BigDecimal.ZERO) == -1)
            throw IllegalArgumentException("Interest rate shouldn't be lesser than zero")

        investmentRepository.save(investment)
        return investmentRepository.selectLessFromInvestment(investment.getId()!!)
    }


    override fun getInvestmentWithId(id: Long): Investment {
        val investment = investmentRepository.findById(id)
        if(investment.isEmpty)
            throw IllegalArgumentException("Investment with the specified ID does not exist")
        return investment.get()
    }

    override fun selectEverythingFromInvestment(id: Long): SelectEverythingFromInvestmentDTO {
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