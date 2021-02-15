package me.mafrans.soloadventure.models;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import me.mafrans.soloadventure.Database;
import me.mafrans.soloadventure.editor.AsciiColor;
import org.bson.types.ObjectId;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Entity
public class DBRoom {
    @Id private ObjectId id;

    @Reference public DBImage image;
    public String description;
    @Reference public Set<DBItem> items;
    @Reference public Set<DBEnemy> enemies;
    public HashMap<String, String> inspections;
    @Reference DBRoomEvents events;

    public DBRoom() { }

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
    public void delete() {
        Database.Companion.delete(this);
    }
}
