package com.pawelcz.investments.calculationAlgorithm

class AlgorithmFactory {

    fun makeAlgorithm(algorithmType : Char) : Algorithm{
        return when(algorithmType){
            '1' -> AtTheEndOfTheInvestmentPeriodAlgorithm()
            '2' -> OnTheDayOfTheCalculationAlgorithm()
            else -> throw IllegalArgumentException("Wrong option")
        }
    }
}