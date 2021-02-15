package me.mafrans.soloadventure.models;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import me.mafrans.soloadventure.Database;
import org.bson.types.ObjectId;

import java.util.HashMap;
import java.util.List;

@Entity
public class DBRoomEvents {
    @Id private ObjectId id;

    public String onEnter;

    public DBRoomEvents() {
    }

    public void save() {
        Database.Companion.save(this);
    }
    public void delete() {
        Database.Companion.delete(this);
    }
}
