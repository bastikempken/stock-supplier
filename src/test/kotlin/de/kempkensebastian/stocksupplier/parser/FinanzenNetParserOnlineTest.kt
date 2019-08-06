package de.kempkensebastian.stocksupplier.parser



import de.kempkensebastian.stocksupplier.FinanzenNetParser
import org.jsoup.Jsoup
import org.jsoup.nodes.Document


import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource

class FinanzenNetParserOnlineTest {

    private val timeRegex = """\d{2}:\d{2}:\d{2}""".toRegex()
    private val diffRegex = """^[+,-]?\d+,\d+""".toRegex()
    private val priceRegex = """^\d+,\d+""".toRegex()
    private val kParser = FinanzenNetParser()

    @DisplayName("Parse online stocks")
    @ParameterizedTest(name = "should parse \"{0}\"")
    @CsvFileSource(resources = ["/online-parser.csv"])
    fun parseOnlineTest(stock:String) {
        val document: Document? = Jsoup.connect("http://www.finanzen.net/aktien/$stock").get();
        val outputTO = kParser.parse(document!!);
        println(outputTO)
        assertTrue(outputTO.time.matches(timeRegex))
        assertTrue(outputTO.absoluteDiff.matches(diffRegex))
        assertTrue(outputTO.relativeDiff.matches(diffRegex))
        assertTrue(outputTO.price.matches(priceRegex))
    }
}