package com.ssabi.springboottutorial.datasource

import com.ssabi.springboottutorial.model.Bank

interface BankDataSource {
  fun getBanks(): Collection<Bank>
}