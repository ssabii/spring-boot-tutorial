package com.ssabi.springboottutorial.datasource.mock

import com.ssabi.springboottutorial.datasource.BankDataSource
import com.ssabi.springboottutorial.model.Bank
import org.springframework.stereotype.Repository

@Repository
class MockBankDataSource : BankDataSource {

  val banks = listOf(
    Bank("1234", 3.14, 17),
    Bank("1010", 17.0, 1),
    Bank("5343", 2.0, 100),
  )

  override fun retrieveBanks(): Collection<Bank> = banks

  override fun retrieveBank(accountNumber: String): Bank =
    banks.firstOrNull() { it.accountNumber == accountNumber }
      ?: throw NoSuchElementException("could not find a bank with account number $accountNumber")

}