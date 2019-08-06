package de.kempkensebastian.stocksupplier

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller(private val stockDataService: StockDataService) {

    @GetMapping("/stockprice")
    fun getStockPrice(@RequestParam("stock") stock:String) : ResponseEntity<FinanzenNetTO>
            = ResponseEntity.ok(stockDataService.deliverStockPrice(stock))
}