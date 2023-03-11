package com.ssabi.springboottutorial.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.*

@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest {

  @Autowired
  lateinit var mockMvc: MockMvc

  @Test
  fun `should return all banks`() {
    mockMvc.get("/api/banks")
      .andDo { print() }
      .andExpect {
        status { isOk() }
        content { contentType(MediaType.APPLICATION_JSON) }
        jsonPath("$[0].accountNumber") { value("1234") }
      }
  }

  @Test
  fun `should return the bank the given account number`() {
    val accountNumber = 1234

    mockMvc.get("/api/banks/$accountNumber")
      .andDo { print() }
      .andExpect {
        status { isOk() }
        content { contentType(MediaType.APPLICATION_JSON) }
        jsonPath("$.trust") { value("3.14")}
        jsonPath("$.transactionFee") { value("7")}
      }
  }
}