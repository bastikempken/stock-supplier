package de.kempkensebastian.stocksupplier

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller(val stockPriceService: StockPriceService) {

    @GetMapping("/stockprice")
    fun getStockPrice(@RequestParam("stock") stock:String) : ResponseEntity<OutputTO>
            = ResponseEntity.ok(stockPriceService.deliverStockPrice(stock))



}