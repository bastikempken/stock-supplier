package de.kempkensebastian.stocksupplier

fun mapFinanzenNet(input: FinanzenNetTO): StockEntity = StockEntity(
        "FINANZEN_NET",
        input.time,
        input.market,
        input.price,
        input.currency,
        input.absoluteDiff,
        input.relativeDiff
)