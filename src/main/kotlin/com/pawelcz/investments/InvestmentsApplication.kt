package com.pawelcz.investments

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class InvestmentsApplication

fun main(args: Array<String>) {
	runApplication<InvestmentsApplication>(*args)
}
