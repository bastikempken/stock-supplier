package de.kempkensebastian.stocksupplier

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.stereotype.Service

@Service
class StockDataService(private val finanzenNetParser: FinanzenNetParser, private val jsoupConnector: JsoupConnector = JsoupConnector()) {
    fun deliverStockPrice(stock: String): FinanzenNetTO {
        val document =  jsoupConnector.connect(stock)
        return finanzenNetParser.parse(document)
    }
}

open class JsoupConnector {
    fun connect(stock: String): Document = Jsoup.connect("http://www.finanzen.net/aktien/$stock").get()
}