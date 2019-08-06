package de.kempkensebastian.stocksupplier

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
@EnableMongoRepositories
open class StocksupplierApplication

fun main(args: Array<String>) {
    runApplication<StocksupplierApplication>(*args)
}

@Bean
fun finanzenNetParser(): FinanzenNetParser = FinanzenNetParser()

@Bean
fun stockDataService(): StockDataService = StockDataService(finanzenNetParser())

@Bean
@Profile("!test")
fun scheduler(stockRepository: StockRepository): Scheduler = Scheduler(stockDataService(),stockRepository)