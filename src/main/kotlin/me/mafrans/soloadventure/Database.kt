package me.mafrans.soloadventure

import com.mongodb.client.MongoClients
import dev.morphia.Datastore
import dev.morphia.Morphia
import dev.morphia.query.experimental.filters.Filters
import me.mafrans.soloadventure.models.DBImage

class Database {
    class Images {
        companion object {
            private val datastore: Datastore = Morphia.createDatastore(MongoClients.create(), "images")

            init {
                datastore.mapper.mapPackage("me.mafrans.soloadventure.models")
                datastore.ensureIndexes()
            }

            fun findImage(name: String): DBImage? {
                val images = this.datastore.find(DBImage::class.java).filter(Filters.gte("name", name))
                if (images.count() == 0L)
                    return null
                else
                    return images.first()
            }
        }
    }
}