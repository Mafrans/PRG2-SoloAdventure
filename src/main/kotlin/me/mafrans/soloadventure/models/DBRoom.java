package me.mafrans.soloadventure.models;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import me.mafrans.soloadventure.Database;
import me.mafrans.soloadventure.editor.AsciiColor;
import org.bson.types.ObjectId;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class DBRoom {
    @Id private ObjectId id;

    public int color;
    @Reference public DBImage image;
    public String description;
    @Reference public Set<DBItem> items;
    @Reference public Set<DBEnemy> enemies;
    public HashMap<String, String> inspections;
    @Reference public DBRoomEvents events;

    public DBRoom() {
        this(Color.WHITE.getRGB(), null, "", new HashSet<>(), new HashSet<>(), new HashMap<>(), new DBRoomEvents());
    }

    public DBRoom(int color, DBImage image, String description, Set<DBItem> items, Set<DBEnemy> enemies, HashMap<String, String> inspections, DBRoomEvents events) {
        this.color = color;
        this.image = image;
        this.description = description;
        this.items = items;
        this.enemies = enemies;
        this.inspections = inspections;
        this.events = events;
    }

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
