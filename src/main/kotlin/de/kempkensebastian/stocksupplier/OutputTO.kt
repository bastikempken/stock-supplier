package de.kempkensebastian.stocksupplier



data class OutputTO(
        val time: String,
        val market:String ,
        val price:String ,
        val currency: String ,
        val absoluteDiff: String,
        val relativeDiff: String)