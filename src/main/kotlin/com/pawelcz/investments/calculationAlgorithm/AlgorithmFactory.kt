package com.pawelcz.investments.calculationAlgorithm

class AlgorithmFactory {

    fun makeAlgorithm(algorithmType : Char) : Algorithm{
        when(algorithmType){
            '1' -> return AtTheEndOfTheInvestmentPeriodAlgorithm()
            '2' -> return OnTheDayOfTheCalculationAlgorithm()
            else -> throw RuntimeException("Wrong option")
        }
    }
}