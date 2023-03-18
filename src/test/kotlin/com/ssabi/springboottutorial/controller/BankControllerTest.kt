package com.ssabi.springboottutorial.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.ssabi.springboottutorial.model.Bank
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.*

@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest @Autowired constructor(
  @Autowired val mockMvc: MockMvc,
  @Autowired val objectMapper: ObjectMapper
) {


  val baseUrl = "/api/banks"

  @Nested
  @DisplayName("GET /api/banks")
  @TestInstance(TestInstance.Lifecycle.PER_CLASS)
  inner class GetBanks {
    @Test
    fun `should return all banks`() {
      mockMvc.get(baseUrl)
        .andDo { print() }
        .andExpect {
          status { isOk() }
          content { contentType(MediaType.APPLICATION_JSON) }
          jsonPath("$[0].accountNumber") { value("1234") }
        }
    }
  }

  @Nested
  @DisplayName("GET /api/banks/{accountNumber}")
  @TestInstance(Lifecycle.PER_CLASS)
  inner class GetBank {
    @Test
    fun `should return the bank the given account number`() {
      val accountNumber = 1234

      mockMvc.get("$baseUrl/$accountNumber")
        .andDo { print() }
        .andExpect {
          status { isOk() }
          content { contentType(MediaType.APPLICATION_JSON) }
          jsonPath("$.trust") { value("3.14") }
          jsonPath("$.transactionFee") { value("17") }
        }
    }

    @Test
    fun `should return NOT FOUND if the account number does not exist`() {
      val accountNumber = "deos_not_exist"

      mockMvc.get("$baseUrl/$accountNumber")
        .andDo { print() }
        .andExpect { status { isNotFound() } }
    }
  }

  @Nested
  @DisplayName("addBank()")
  @TestInstance(Lifecycle.PER_CLASS)
  inner class PostNewBank {
    @Test
    fun `should add the new bank`() {
      val newBank = Bank("acc123", 31.415, 2)

      val performPost = mockMvc.post(baseUrl) {
        contentType = MediaType.APPLICATION_JSON
        content = objectMapper.writeValueAsString(newBank)
      }

      performPost
        .andDo { print() }
        .andExpect {
          status { isCreated() }
          content { contentType(MediaType.APPLICATION_JSON) }
          jsonPath("$.accountNumber") { value("acc123") }
          jsonPath("$.trust") { value("31.415") }
          jsonPath("$.transactionFee") { value("2") }
        }
    }

    @Test
    fun `should return BAD REQUEST if bank given account number already exists`() {
      val invalidBank = Bank("1234", 1.0, 1)

      val performPost = mockMvc.post(baseUrl) {
        contentType = MediaType.APPLICATION_JSON
        content = objectMapper.writeValueAsString(invalidBank)
      }

      performPost
        .andDo { print() }
        .andExpect {status { isBadRequest() } }
    }
  }
}