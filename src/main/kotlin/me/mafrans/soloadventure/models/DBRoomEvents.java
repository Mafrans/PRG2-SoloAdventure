package me.mafrans.soloadventure.models;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import me.mafrans.soloadventure.Database;
import org.bson.types.ObjectId;

import java.util.HashMap;
import java.util.List;

/**
 * Database entity holding information about room javascript events
 */
@Entity
public class DBRoomEvents {
    @Id private ObjectId id;

    /**
     * A javascript event run when the player enters the room
     */
    public String onEnter;

    /**
     * Default constructor for DBRoomEvents
     */
    public DBRoomEvents() {
    }

    /**
     * Saves this entity to the database
     */
    public void save() {
        Database.Companion.save(this);
    }

    /**
     * Deletes this entity from the database
     */
    public void delete() {
        Database.Companion.delete(this);
    }
}
