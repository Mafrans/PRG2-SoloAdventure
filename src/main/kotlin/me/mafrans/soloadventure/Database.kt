package me.mafrans.soloadventure

import com.mongodb.client.MongoClients
import dev.morphia.Datastore
import dev.morphia.Morphia
import dev.morphia.query.UpdateException
import dev.morphia.query.experimental.filters.Filters
import me.mafrans.soloadventure.models.DBEnemy
import me.mafrans.soloadventure.models.DBImage

/**
 * A helper class that manages and deals with the database
 */
class Database {
    /**
     * A {@link Morphia} datastore, with the name 'soloadventure'
     */
    private val datastore: Datastore = Morphia.createDatastore(MongoClients.create(), "soloadventure")
    init {
        datastore.mapper.mapPackage("me.mafrans.soloadventure.models")
        datastore.ensureIndexes()
    }

    companion object {
        val db = Database()

        /**
         * Save a generic entity to the database
         * @param entity The entity to save
         */
        fun <T> save(entity: T) {
            db.datastore.save(entity)
            try {
                db.datastore.merge(entity)
            }
            catch (e: UpdateException) {}
        }

        /**
         * Delete a generic entity from the database
         * @param entity The entity to delete
         */
        fun <T> delete(entity: T) {
            db.datastore.delete(entity);
        }

        /**
         * Find the first entity of the given class type from the database
         * @param clazz The class type
         */
        fun <T> first(clazz: Class<T>): T {
            return db.datastore.find(clazz).first();
        }
    }

    /**
     * Helper class managing the database specifically for images
     */
    class Images {
        companion object {
            /**
             * Retrieves an image from the database, given its name
             * @param name The image's name
             */
            fun findImage(name: String): DBImage? {
                val images = db.datastore.find(DBImage::class.java).filter(Filters.gte("name", name))
                if (images.count() == 0L)
                    return null
                else
                    return images.first()
            }
        }
    }

    /**
     * Helper class managing the database specifically for enemies
     */
    class Enemies {
        companion object {
            /**
             * Retrieves an enemy from the database, given its name
             * @param name The enemy's name
             */
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