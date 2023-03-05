package com.ssabi.springboottutorial.controller

import com.ssabi.springboottutorial.model.Bank
import com.ssabi.springboottutorial.service.BankService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/banks")
class BankController(private val service: BankService) {

  @GetMapping
  fun getBanks(): Collection<Bank> = service.getBanks()
}