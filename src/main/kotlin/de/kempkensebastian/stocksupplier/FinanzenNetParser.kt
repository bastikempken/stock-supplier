package de.kempkensebastian.stocksupplier

import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.springframework.stereotype.Repository

open class FinanzenNetParser {

    fun parse(document: Document): FinanzenNetTO {
        val quotebox = document.getElementsByClass("quotebox")?.first() ?: throw IllegalArgumentException()

        val time = parseTime(quotebox)
        val market = parseMarket(quotebox)
        val price = parsePrice(quotebox)
        val absoluteDiff = parseAbsolutDiff(quotebox)
        val relativeDiff = parseRelativeDiff(quotebox)
        val currency = parseCurrency(quotebox)

        return FinanzenNetTO(time,
                market,
                price.dropLast(currency.length),
                currency,
                absoluteDiff.dropLast(currency.length),
                relativeDiff.dropLast(1))
    }

    private fun parseTime(quotebox: Element) = parse(quotebox,"quotebox-time")

    private fun parseMarket(quotebox: Element) = getNode(quotebox, "col-sm-2")
            ?.select("div")?.last()?.ownText()?:""

    private fun parsePrice(quotebox: Element) = parse(quotebox,"col-xs-5")

    private fun parseAbsolutDiff(quotebox: Element) = parse(quotebox,"col-xs-4")

    private fun parseRelativeDiff(quotebox: Element) = parse(quotebox,"col-xs-3")

    private fun parseCurrency(quotebox: Element) = getNode(quotebox,"col-xs-5")
            ?.select("span")?.first()?.ownText()?:""

    private fun parse(quoteBox: Element, className: String) =
            getNode(quoteBox,className)?.text() ?: ""

    private fun getNode(quoteBox: Element, className: String) = quoteBox.getElementsByClass(className)?.first()
}

data class FinanzenNetTO(
        val time: String,
        val market:String ,
        val price:String ,
        val currency: String ,
        val absoluteDiff: String,
        val relativeDiff: String)
