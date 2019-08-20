package de.kempkensebastian.stocksupplier

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
@EnableMongoRepositories
open class StocksupplierApplication {
    @Bean
    open fun finanzenNetParser(): FinanzenNetParser = FinanzenNetParser()

    @Bean
    open fun deliveringService(): DeliveringService = DeliveringService(finanzenNetParser())

    @Bean
    open fun schedulingService(stockRepository: StockRepository): SchedulingService = SchedulingService(deliveringService(), stockRepository)

    @Bean
    @Profile("!test")
    open fun scheduler(schedulingService: SchedulingService,
                       @Value("\${stocks}") stocks: Array<String>): Scheduler = Scheduler(schedulingService, stocks.asList())
}

fun main(args: Array<String>) {
    runApplication<StocksupplierApplication>(*args)
}


