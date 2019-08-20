package de.kempkensebastian.stocksupplier

import com.mongodb.MongoClient
import com.mongodb.ServerAddress
import de.bwaldvogel.mongo.MongoServer
import de.bwaldvogel.mongo.backend.memory.MemoryBackend
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.mongodb.MongoDbFactory
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoDbFactory


@Profile("test")
open class MongoTestConfiguration {
    @Bean
    open fun mongoTemplate(mongoClient: MongoClient): MongoTemplate =
         MongoTemplate(mongoDbFactory(mongoClient))

    @Bean
    open fun mongoDbFactory(mongoClient: MongoClient): MongoDbFactory =
         SimpleMongoDbFactory(mongoClient, "test")

    @Bean(destroyMethod = "shutdown")
    open fun mongoServer(): MongoServer {
        val mongoServer = MongoServer(MemoryBackend())
        mongoServer.bind()
        return mongoServer
    }

    @Bean(destroyMethod = "close")
    open fun mongoClient(mongoServer: MongoServer): MongoClient {
        return MongoClient(ServerAddress(mongoServer.localAddress))
    }
}
