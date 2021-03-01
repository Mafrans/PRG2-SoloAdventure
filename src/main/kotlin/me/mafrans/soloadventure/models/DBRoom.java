package me.mafrans.soloadventure.models;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import me.mafrans.soloadventure.Database;
import org.bson.types.ObjectId;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Database entity holding information about a room in the game
 */
@Entity
public class DBRoom {
    @Id private ObjectId id;

    /**
     * The room's color
     */
    public int color;

    /**
     * The image that is displayed when the player enters the room
     */
    @Reference public DBImage image;

    /**
     * The text that is displayed when the player enters the room
     */
    public String description;

    /**
     * A set of items that can be found in the room
     */
    @Reference public Set<DBItem> items;

    /**
     * A set of enemies that can be fought in the room
     */
    @Reference public Set<DBEnemy> enemies;

    /**
     * A hashmap of inspection data, containing the keys and the text that is displayed when those keys are inspected by the player
     */
    public HashMap<String, String> inspections;

    /**
     * A {@link DBRoomEvents} holding information about the javascript events in the room
     */
    @Reference public DBRoomEvents events;

    /**
     * Default constructor for DBRoom
     */
    public DBRoom() {
        this(Color.WHITE.getRGB(), null, "", new HashSet<>(), new HashSet<>(), new HashMap<>(), new DBRoomEvents());
    }

    /**
     * Constructor for DBRoom
     * @param color The room's color
     * @param image The image that is displayed when the player enters the room
     * @param description The text that is displayed when the player enters the room
     * @param items A set of items that can be found in the room
     * @param enemies A set of enemies that can be fought in the room
     * @param inspections A HashMap of inspection data, containing the keys and the text that is displayed when those keys are inspected by the player
     * @param events A {@link DBRoomEvents} holding information about the javascript events in the room
     */
    public DBRoom(int color, DBImage image, String description, Set<DBItem> items, Set<DBEnemy> enemies, HashMap<String, String> inspections, DBRoomEvents events) {
        this.color = color;
        this.image = image;
        this.description = description;
        this.items = items;
        this.enemies = enemies;
        this.inspections = inspections;
        this.events = events;
    }

    /**
     * Saves this DBRoom entity, and all its references, to the database
     */
    public void save() {
        if (image != null) {
            image.save();
        }

        if (events != null) {
            events.save();
        }

        for (DBItem item : items) {
            item.save();
        }

        for (DBEnemy enemy : enemies) {
            enemy.save();
        }

        Database.Companion.save(this);
    }

    /**
     * Deletes this DBRoom entity from the database
     */
    public void delete() {
        Database.Companion.delete(this);
    }
}
