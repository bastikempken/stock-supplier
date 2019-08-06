package de.kempkensebastian.stocksupplier



import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File


import org.hamcrest.MatcherAssert.assertThat



class KParserTest {

    private val FINANZEN_NET_EXAMPLE_1_HTML = "Finanzen-Net-Example-1.html"

    private val BASE_URL = "http://www.finanzen.net/aktien/Daimler-Aktie"

    private val EXPECTED_PRICE = "59,29"

    private val EXPECTED_CURRENCY = "EUR"

    private val EXPECTED_ABS_DIFF = "-1,00"

    private val EXPECTED_REL_DIFF = "-1,66"

    private val EXPECTED_TIME = "12:48:01"

    private val EXPECTED_MARKET = "XETRA"

    val kParser = KParser()


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
    fun test()  {
        val document: Document? = Jsoup.connect("http://www.finanzen.net/aktien/Daimler-Aktie").get();
        val outputTO = kParser.parse(document!!);
        System.out.println(outputTO);
    }
}