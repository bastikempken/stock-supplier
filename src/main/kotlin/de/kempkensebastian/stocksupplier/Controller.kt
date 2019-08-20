package de.kempkensebastian.stocksupplier

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller @Autowired constructor(private val deliveringService: DeliveringService) {

    @GetMapping("/stockprice")
    fun getStockPrice(@RequestParam("stock") stock:String) : ResponseEntity<FinanzenNetTO>
            = ResponseEntity.ok(deliveringService.deliverStockPrice(stock))
}