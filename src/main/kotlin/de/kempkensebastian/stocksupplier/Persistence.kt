package de.kempkensebastian.stocksupplier

import org.springframework.data.mongodb.repository.MongoRepository

interface StockRepository : MongoRepository<StockEntity,String>

class StockEntity(
        val source: String,
        val time: String,
        val market:String ,
        val price:String ,
        val currency: String ,
        val absoluteDiff: String,
        val relativeDiff: String)