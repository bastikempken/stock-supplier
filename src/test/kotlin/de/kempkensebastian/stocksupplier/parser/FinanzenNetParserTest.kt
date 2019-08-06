package de.kempkensebastian.stocksupplier.parser


import de.kempkensebastian.stocksupplier.FinanzenNetParser
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File


class FinanzenNetParserTest {

    companion object {
        private const val FINANZEN_NET_EXAMPLE_1_HTML = "Finanzen-Net-Example-1.html"
        private const val BASE_URL = "http://www.finanzen.net/aktien/Daimler-Aktie"
        private const val EXPECTED_PRICE = "59,29"
        private const val EXPECTED_CURRENCY = "EUR"
        private const val EXPECTED_ABS_DIFF = "-1,00"
        private const val EXPECTED_REL_DIFF = "-1,66"
        private const val EXPECTED_TIME = "12:48:01"
        private const val EXPECTED_MARKET = "XETRA"
    }

    private val kParser = FinanzenNetParser()

    @BeforeEach
    fun setUp() {
    }

    @Test
    @Throws(Exception::class)
    fun parse() {
        val input = File(javaClass.classLoader.getResource(FINANZEN_NET_EXAMPLE_1_HTML)!!.toURI())
        val doc = Jsoup.parse(input, "UTF-8", BASE_URL)
        val outputTO = kParser.parse(doc)
        assertThat(outputTO.price, equalTo(EXPECTED_PRICE))
        assertThat(outputTO.currency, equalTo(EXPECTED_CURRENCY))
        assertThat(outputTO.absoluteDiff, equalTo(EXPECTED_ABS_DIFF))
        assertThat(outputTO.relativeDiff, equalTo(EXPECTED_REL_DIFF))
        assertThat(outputTO.time, equalTo(EXPECTED_TIME))
        assertThat(outputTO.market, `is`(equalTo(EXPECTED_MARKET)))
    }

    @Test
    fun parseOnlineTest() {
        val document: Document? = Jsoup.connect("http://www.finanzen.net/aktien/Daimler-Aktie").get();
        val outputTO = kParser.parse(document!!);
        println(outputTO)
        val timeRegex = """\d{2}:\d{2}:\d{2}""".toRegex()
        val diffRegex = """^-?\d+,\d+""".toRegex()
        val priceRegex = """^\d+,\d+""".toRegex()
        assertTrue(outputTO.time.matches(timeRegex))
        assertTrue(outputTO.absoluteDiff.matches(diffRegex))
        assertTrue(outputTO.relativeDiff.matches(diffRegex))
        assertTrue(outputTO.price.matches(priceRegex))
    }
}