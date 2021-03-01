package me.mafrans.soloadventure.models;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import me.mafrans.soloadventure.Database;
import org.bson.types.ObjectId;

/**
 * Database entry holding all game data
 */
@Entity
public class DBGame {
    @Id public ObjectId id;
    /**
     * A grid of DBRoom entities, the player spawns in the centermost of these rooms
     */
    @Reference public DBRoom[][] rooms;

    /**
     * The maximum width of the game map
     */
    public static final int MAP_WIDTH = 9;

    /**
     * The maximum height of the game map
     */
    public static final int MAP_HEIGHT = 9;

    /**
     * Default constructor for DBRoom
     */
    public DBGame() {
        this(new DBRoom[MAP_WIDTH][MAP_HEIGHT]);
    }

    /**
     * Constructor for DBRoom
     * @param rooms A grid of DBRoom entities
     */
    public DBGame(DBRoom[][] rooms) {
        this.rooms = rooms;
    }

    /**
     * Saves this DBGame entity, and all its references, to the database
     */
    public void save() {
        for (DBRoom[] column : rooms) {
            for (DBRoom room : column) {
                if(room != null) {
                    room.save();
                }
            }
        }
        Database.Companion.save(this);
    }

    /**
     * Finds the first DBGame entity in the database
     * @return The first DBGame entity in the database
     */
    public static DBGame first() {
        return Database.Companion.first(DBGame.class);
    }
}
