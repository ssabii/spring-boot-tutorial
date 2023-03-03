package com.ssabi.springboottutorial

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootTutorialApplication

fun main(args: Array<String>) {
	runApplication<SpringBootTutorialApplication>(*args)
}
