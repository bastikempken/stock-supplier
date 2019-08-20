package de.kempkensebastian.stocksupplier

import com.beust.klaxon.Klaxon
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.stereotype.Service
import org.springframework.util.ResourceUtils
import java.io.File

class DeliveringService(
        private val finanzenNetParser: FinanzenNetParser,
        private val jsoupConnector: JsoupConnector = JsoupConnector()
) {

    fun deliverStockPrice(stock: String): FinanzenNetTO {
        val document = jsoupConnector.connect(stock)
        return finanzenNetParser.parse(document)
    }
}

class SchedulingService(
        private val deliveringService: DeliveringService,
        private val stockRepository: StockRepository
) {
    fun process(stocks: List<String>): Unit {
        stocks.map { deliveringService.deliverStockPrice(it) }
                .map { mapFinanzenNet(it) }
                .forEach { stockRepository.save(it) }
    }
}

class StockDataService(private val path: String) {
    fun readIn(): List<StockData> = Klaxon().parseArray(
            ResourceUtils.getFile("classpath:${path}").inputStream())!!
}

class StockData(
        val name: String,
        val finanzenNetEntry: String
)

open class JsoupConnector {
    fun connect(stock: String): Document = Jsoup.connect("http://www.finanzen.net/aktien/$stock").get()
}