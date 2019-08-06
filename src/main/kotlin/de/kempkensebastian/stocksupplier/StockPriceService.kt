package de.kempkensebastian.stocksupplier

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.stereotype.Service

@Service
class StockPriceService(val kParser: KParser) {

    fun deliverStockPrice(stock: String):OutputTO  {
        val document = Jsoup.connect("http://www.finanzen.net/aktien/${stock}").get()
        return kParser.parse(document)
    }
}