package com.ssabi.springboottutorial.service

import com.ssabi.springboottutorial.datasource.BankDataSource
import com.ssabi.springboottutorial.model.Bank
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class BankService(@Autowired private val dataSource: BankDataSource) {
  fun getBanks(): Collection<Bank> = dataSource.retrieveBanks()
}