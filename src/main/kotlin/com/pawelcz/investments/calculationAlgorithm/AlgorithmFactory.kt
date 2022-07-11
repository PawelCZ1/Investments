package com.pawelcz.investments.calculationAlgorithm

class AlgorithmFactory {

    fun makeAlgorithm(algorithmType : AlgorithmType) : Algorithm{
        return when(algorithmType){
            AlgorithmType.AT_END_OF_THE_INVESTMENT_PERIOD -> AtTheEndOfTheInvestmentPeriodAlgorithm()
            AlgorithmType.ON_THE_DAY_OF_THE_CALCULATION -> OnTheDayOfTheCalculationAlgorithm()
        }
    }
}