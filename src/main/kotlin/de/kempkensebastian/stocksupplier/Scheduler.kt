package de.kempkensebastian.stocksupplier

import org.springframework.scheduling.annotation.Scheduled

class Scheduler(
        private val schedulingService: SchedulingService,
        private val stocks: List<String>
) {
    @Scheduled(fixedRate = 5000)
    fun gatherStockData():Unit = schedulingService.process(stocks)
}