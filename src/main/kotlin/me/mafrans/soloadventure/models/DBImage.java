package me.mafrans.soloadventure.models;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import me.mafrans.soloadventure.Database;
import org.bson.types.ObjectId;

/**
 * A database entity holding an ascii image
 */
@Entity
public class DBImage {
    /**
     * The default image width
     */
    public static int DEFAULT_WIDTH = 24;

    /**
     * The default image height
     */
    public static int DEFAULT_HEIGHT = 16;

    @Id private ObjectId id;

    /**
     * A grid of DBCell entities
     */
    @Reference public DBImageCell[][] cells;

    /**
     * Default constructor for DBImage
     */
    public DBImage() {
        this.cells = new DBImageCell[DEFAULT_WIDTH][DEFAULT_HEIGHT];
    }

    /**
     * Constructor for DBImage
     * @param cells A grid of DBCell entities
     */
    public DBImage(DBImageCell[][] cells) {
        this.cells = cells;
    }

    /**
     * Finds an entity of DBImage in the database, given its name
     * @param name The DBImage's name
     * @return null
     * @deprecated unused and irrelevant
     */
    public static DBImage find(String name) {
        return Database.Images.Companion.findImage(name);
    }

    /**
     * Saves this DBImage entity, and all its references, to the database
     */
    public void save() {
        for (DBImageCell[] column : cells) {
            for (DBImageCell cell : column) {
                cell.save();
            }
        }
        Database.Companion.save(this);
    }

    /**
     * Deletes this DBImage entity, and all its references, from the database
     */
    public void delete() {
        for (DBImageCell[] column : cells) {
            for (DBImageCell cell : column) {
                cell.delete();
            }
        }
        Database.Companion.delete(this);
    }
}
