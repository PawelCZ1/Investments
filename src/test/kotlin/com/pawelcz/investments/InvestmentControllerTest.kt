package com.pawelcz.investments

import com.fasterxml.jackson.databind.ObjectMapper
import com.pawelcz.investments.investment.CapitalizationPeriodInMonths
import com.pawelcz.investments.investment.Investment
import com.pawelcz.investments.investment.InvestmentService
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
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
    val objectMapper: ObjectMapper
) {




    @AfterEach
    internal fun tearDown() {
        investmentService.clearTable()
    }

    @Nested
    @DisplayName("GET /api/investments/archive")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class AllInvestments{

        @Test
        fun shouldReturnAllInvestments(){
            // given
            val firstTestInvestment = Investment("first", BigDecimal("6"), CapitalizationPeriodInMonths.SIX,
                LocalDate.parse("2022-04-18"), LocalDate.parse("2023-08-15") )
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
                    jsonPath("$[0].id") {isNumber()}
                    jsonPath("$[0].name") {value("first")}
                    jsonPath("$[1].id") {isNumber()}
                    jsonPath("$[1].name") {value("second")}
                }
        }

    }

    @Nested
    @DisplayName("GET /api/investments")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class AvailableInvestments{
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
                    jsonPath("$[0].id") {isNumber()}
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
            val testInvestment = Investment("first", BigDecimal("6"), CapitalizationPeriodInMonths.SIX,
                LocalDate.parse("2022-04-18"), LocalDate.parse("2023-08-15") )
            // when/then
            mockMvc.post("/api/investments") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(testInvestment)

            }
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                }


        }


    }

}