package me.mafrans.soloadventure.models;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import me.mafrans.soloadventure.Database;
import org.bson.types.ObjectId;

@Entity
public class DBEnemy {
    @Id private ObjectId id;

    public String name;
    public int hp;
    @Reference public DBImage sprite;

    public DBEnemy() {
        this.name = "undefined";
        this.hp = 0;
        this.sprite = new DBImage();
    }

    public DBEnemy(String name, int hp, DBImage sprite) {
        this.name = name;
        this.hp = hp;
        this.sprite = sprite;
    }

    public DBEnemy(String name, int hp) {
        this.name = name;
        this.hp = hp;
    }

    public static DBEnemy find(String name) {
        return Database.Enemies.Companion.findEnemy(name);
    }

    public void save() {
        sprite.save();
        Database.Companion.save(this);
    }
}
