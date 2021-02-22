package me.mafrans.soloadventure.models;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import me.mafrans.soloadventure.Database;
import org.bson.types.ObjectId;

@Entity
public class DBGame {
    @Id public ObjectId id;
    @Reference public DBRoom[][] rooms;

    public static final int MAP_WIDTH = 9;
    public static final int MAP_HEIGHT = 9;

    public DBGame() {
        this(new DBRoom[MAP_WIDTH][MAP_HEIGHT]);
    }

    public DBGame(DBRoom[][] rooms) {
        this.rooms = rooms;
    }

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

    public static DBGame first() {
        return Database.Companion.first(DBGame.class);
    }
}
