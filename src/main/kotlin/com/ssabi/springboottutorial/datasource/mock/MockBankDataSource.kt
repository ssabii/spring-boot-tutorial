package com.ssabi.springboottutorial.datasource.mock

import com.ssabi.springboottutorial.datasource.BankDataSource
import com.ssabi.springboottutorial.model.Bank
import org.springframework.stereotype.Repository

@Repository
class MockBankDataSource : BankDataSource {

  val banks = mutableListOf(
    Bank("1234", 3.14, 17),
    Bank("1010", 17.0, 1),
    Bank("5343", 2.0, 100),
  )

  override fun retrieveBanks(): Collection<Bank> = banks

  override fun retrieveBank(accountNumber: String): Bank =
    banks.firstOrNull() { it.accountNumber == accountNumber }
      ?: throw NoSuchElementException("could not find a bank with account number $accountNumber")

  override fun createBank(bank: Bank): Bank {
    if (banks.any { it.accountNumber == bank.accountNumber }) {
      throw IllegalArgumentException("Bank with account number ${bank.accountNumber} already exsists")
    }
    banks.add(bank)

    return bank
  }

  override fun updateBank(bank: Bank): Bank {
    val currentBank = banks.firstOrNull { it.accountNumber == bank.accountNumber }
      ?: throw NoSuchElementException("Could not find a bank with account number ${bank.accountNumber}")

    banks.remove(currentBank)
    banks.add(bank)

    return bank
  }

}