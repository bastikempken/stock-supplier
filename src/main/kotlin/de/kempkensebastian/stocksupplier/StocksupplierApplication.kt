package de.kempkensebastian.stocksupplier

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StocksupplierApplication

fun main(args: Array<String>) {
    runApplication<StocksupplierApplication>(*args)
}
