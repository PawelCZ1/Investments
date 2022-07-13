package com.pawelcz.investments

import com.fasterxml.jackson.databind.ObjectMapper
import com.pawelcz.investments.calculation.CalculationService
import com.pawelcz.investments.calculationAlgorithm.AlgorithmType
import com.pawelcz.investments.dto.CalculationParametersDTO
import com.pawelcz.investments.investment.CapitalizationPeriodInMonths
import com.pawelcz.investments.investment.Investment
import com.pawelcz.investments.investment.InvestmentService

import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import java.math.BigDecimal
import java.time.LocalDate



@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
internal class InvestmentControllerTest @Autowired constructor(
    val mockMvc : MockMvc,
    val investmentService: InvestmentService,
    val calculationService: CalculationService,
    val objectMapper: ObjectMapper
) {

    @Nested
    @DisplayName("GET /api/investments/archive")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class AllInvestments{

        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        @Test
        fun shouldReturnAllInvestments(){
            // given
            val firstTestInvestment = Investment("first", BigDecimal("6"), CapitalizationPeriodInMonths.SIX,
                LocalDate.parse("2021-04-18"), LocalDate.parse("2024-04-15") )
            val secondTestInvestment = Investment("second", BigDecimal("8"), CapitalizationPeriodInMonths.THREE,
                LocalDate.parse("2021-01-15"), LocalDate.parse("2022-06-15") )
            investmentService.addInvestment(firstTestInvestment)
            investmentService.addInvestment(secondTestInvestment)
            // when/then
            mockMvc.get("/api/investments/archive")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].id") {value(firstTestInvestment.getId())}
                    jsonPath("$[0].name") {value("first")}
                    jsonPath("$[1].id") {value(secondTestInvestment.getId())}
                    jsonPath("$[1].name") {value("second")}
                }

        }


    }

    @Nested
    @DisplayName("GET /api/investments")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class AvailableInvestments{
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        @Test
        fun shouldReturnAvailableInvestments(){
            // given
            val firstTestInvestment = Investment("first", BigDecimal("6"), CapitalizationPeriodInMonths.SIX,
                LocalDate.parse("2022-04-18"), LocalDate.parse("2023-08-15") )
            val secondTestInvestment = Investment("second", BigDecimal("8"), CapitalizationPeriodInMonths.THREE,
                LocalDate.parse("2021-01-15"), LocalDate.parse("2022-06-15") )
            investmentService.addInvestment(firstTestInvestment)
            investmentService.addInvestment(secondTestInvestment)
            // when/then
            mockMvc.get("/api/investments")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].id") {value(firstTestInvestment.getId())}
                    jsonPath("$[0].name") {value("first")}
                    jsonPath("$[1].id") {doesNotExist()}
                    jsonPath("$[1].name") {doesNotExist()}
                }

        }

    }



    @Nested
    @DisplayName("POST /api/investments")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class AddInvestment{

        @Test
        fun shouldAddNewInvestment(){
            // given
            val testInvestment = Investment("test", BigDecimal("8"), CapitalizationPeriodInMonths.THREE,
                LocalDate.parse("2021-01-15"), LocalDate.parse("2022-06-15") )
            // when/then
            mockMvc.post("/api/investments") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(testInvestment)

            }
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.id") {testInvestment.getId()}
                    jsonPath("$.name") {value("test")}
                    jsonPath("$.interest_Rate") {value(BigDecimal("8.0"))}
                    jsonPath("$.days") {value(516)}
                }



        }

        @Test
        fun shouldReturnBadRequest(){
            // given
            val testInvestment = Investment("test", BigDecimal(-1), CapitalizationPeriodInMonths.THREE,
                LocalDate.parse("2021-01-15"), LocalDate.parse("2022-06-15") )
            // when/then
            mockMvc.post("/api/investments") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(testInvestment)

            }
                .andDo { print() }
                .andExpect {
                    status { isBadRequest() }
                }

        }


    }

    @Nested
    @DisplayName("POST /api/investments/{id}/calculations")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class Calculation{

        @Test
        fun shouldAddNewCalculation(){
            // given
            val testInvestment = Investment("test", BigDecimal("8"), CapitalizationPeriodInMonths.THREE,
                LocalDate.parse("2021-01-15"), LocalDate.parse("2023-06-15") )
            val calculationParametersDTO = CalculationParametersDTO(BigDecimal("5000")
                , AlgorithmType.AT_END_OF_THE_INVESTMENT_PERIOD)
            testInvestment.setId(1L)
            val id = testInvestment.getId()
            investmentService.addInvestment(testInvestment)
            // when/then
            mockMvc.post("/api/investments/$id/calculations") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(calculationParametersDTO)
            }
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                }


        }

        @Test
        fun shouldReturnBadRequest(){
            // given
            val testInvestment = Investment("test", BigDecimal("8"), CapitalizationPeriodInMonths.THREE,
                LocalDate.parse("2021-01-15"), LocalDate.parse("2022-06-15") )
            val calculationParametersDTO = CalculationParametersDTO(BigDecimal("5000")
                , AlgorithmType.AT_END_OF_THE_INVESTMENT_PERIOD)
            testInvestment.setId(1L)
            val id = 50L
            investmentService.addInvestment(testInvestment)
            // when/then
            mockMvc.post("/api/investments/$id/calculations") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(calculationParametersDTO)
            }
                .andDo { print() }
                .andExpect {
                    status { isBadRequest() }
                }


        }

    }

    @Nested
    @DisplayName("GET /api/investments/{id}/calculations")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class HistoricalCalculationsOfTheParticularInvestment{

        @Test
        fun shouldReturnInvestmentAndItsHistoricalCalculations(){
            // given
            val testInvestment = Investment("test", BigDecimal("8"), CapitalizationPeriodInMonths.THREE,
                LocalDate.parse("2021-01-15"), LocalDate.parse("2022-09-15") )
            val firstCalculationParametersDTO = CalculationParametersDTO(BigDecimal("5000")
                , AlgorithmType.AT_END_OF_THE_INVESTMENT_PERIOD)
            val secondCalculationParametersDTO = CalculationParametersDTO(BigDecimal("6000")
                , AlgorithmType.ON_THE_DAY_OF_THE_CALCULATION)
            val thirdCalculationParametersDTO = CalculationParametersDTO(BigDecimal("7000")
                , AlgorithmType.AT_END_OF_THE_INVESTMENT_PERIOD)
            testInvestment.setId(1L)
            val id = testInvestment.getId()
            investmentService.addInvestment(testInvestment)
            if (id != null) {
                calculationService.addCalculation(id, firstCalculationParametersDTO)
                calculationService.addCalculation(id, secondCalculationParametersDTO)
                calculationService.addCalculation(id, thirdCalculationParametersDTO)
            }

            // when/then
            mockMvc.get("/api/investments/$id/calculations")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                }
        }

        @Test
        fun shouldReturnBadRequest(){
            // given
            val id = 50L
            // when/then
            mockMvc.get("/api/investments/$id/calculations")
                .andDo { print() }
                .andExpect {
                    status { isBadRequest() }
                }

        }

    }

}