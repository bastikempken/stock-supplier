package de.kempkensebastian.stocksupplier

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.tuple
import org.jsoup.nodes.Document
import org.junit.jupiter.api.Test

class DeliveringServiceTest {

    private val kParser = mockk<FinanzenNetParser>(relaxed = true)
    private val jSoupConnector = mockk<JsoupConnector>()
    private val deliveringService = DeliveringService(kParser, jSoupConnector)

    @Test
    fun deliverStockPrice() {
        val stock = "stock"
        val document = Document("")
        every { jSoupConnector.connect(stock) } returns document
        deliveringService.deliverStockPrice(stock)
        verify { kParser.parse(document) }
    }
}

class StockDataServiceTest {
    private val stockDataService = StockDataService("stock-data.json")

    @Test
    fun shouldReadStockData() {
        val readIn = stockDataService.readIn()
        assertThat(readIn)
                .extracting("name","finanzenNetEntry")
                .contains(tuple("Daimler","daimler-aktie"))
    }
}