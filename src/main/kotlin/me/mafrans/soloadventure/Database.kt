package me.mafrans.soloadventure

import com.mongodb.client.MongoClients
import dev.morphia.Datastore
import dev.morphia.Morphia
import dev.morphia.query.experimental.filters.Filters
import me.mafrans.soloadventure.models.DBEnemy
import me.mafrans.soloadventure.models.DBImage

class Database {
    private val datastore: Datastore = Morphia.createDatastore(MongoClients.create(), "soloadventure")
    init {
        datastore.mapper.mapPackage("me.mafrans.soloadventure.models")
        datastore.ensureIndexes()
    }

    companion object {
        val db = Database()
    }

    class Images {
        companion object {
            fun findImage(name: String): DBImage? {
                val images = db.datastore.find(DBImage::class.java).filter(Filters.gte("name", name))
                if (images.count() == 0L)
                    return null
                else
                    return images.first()
            }
        }
    }

    class Enemies {
        companion object {
            fun findEnemy(name: String): DBEnemy? {
                val images = db.datastore.find(DBEnemy::class.java).filter(Filters.gte("name", name))
                if (images.count() == 0L)
                    return null
                else
                    return images.first()
            }
        }
    }
}