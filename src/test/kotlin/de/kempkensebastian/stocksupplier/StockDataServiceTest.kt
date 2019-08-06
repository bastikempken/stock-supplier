package de.kempkensebastian.stocksupplier

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.jsoup.nodes.Document
import org.junit.jupiter.api.Test

class StockDataServiceTest {

    private val kParser = mockk<FinanzenNetParser>(relaxed = true)
    private val jSoupConnector = mockk<JsoupConnector>()
    private val stockPriceService = StockDataService(kParser, jSoupConnector)

    @Test
    fun deliverStockPrice() {
        val stock = "stock"
        val document = Document("")
        every { jSoupConnector.connect(stock) } returns document
        stockPriceService.deliverStockPrice(stock)
        verify { kParser.parse(document) }
    }
}