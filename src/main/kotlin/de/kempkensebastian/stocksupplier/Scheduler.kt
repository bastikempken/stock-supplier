package de.kempkensebastian.stocksupplier

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled

class Scheduler @Autowired constructor(
        private val stockDataService: StockDataService,
        private val stockRepository: StockRepository) {

    @Scheduled(fixedRate = 5000)
    fun gatherStockData() {
        //TODO
        val stocks = listOf<String>()
        stocks.map { stockDataService.deliverStockPrice(it) }
              .map { mapFinanzenNet(it) }
              .forEach { stockRepository.save(it) }
    }
}