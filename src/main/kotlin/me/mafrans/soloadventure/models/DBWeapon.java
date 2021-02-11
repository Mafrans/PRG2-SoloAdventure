package me.mafrans.soloadventure.models;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import me.mafrans.soloadventure.Database;
import org.bson.types.ObjectId;

@Entity
public class DBWeapon {
    @Id private ObjectId id;

    public int damage;
    public int variance;
    public int critPercent;
    public String[] attackMessages;

    public void save() {
        Database.Companion.save(this);
    }

    public void delete() {
        Database.Companion.delete(this);
    }
}
