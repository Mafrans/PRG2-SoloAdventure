package me.mafrans.soloadventure.models;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import me.mafrans.soloadventure.Database;
import org.bson.types.ObjectId;

/**
 * Database entity holding information about an enemy
 */
@Entity
public class DBEnemy {
    @Id public ObjectId id;

    /**
     * The enemy's name
     */
    public String name;

    /**
     * How many hit points the enemy has
     */
    public int hp;

    /**
     * An image sprite of the enemy
     */
    @Reference public DBImage sprite;

    /**
     * Default constructor for DBEnemy
     */
    public DBEnemy() {
        this.name = "undefined";
        this.hp = 0;
        this.sprite = new DBImage();
    }

    /**
     * Constructor for DBEnemy
     * @param name The enemy's name
     * @param hp How many hit points the enemy has
     * @param sprite An image sprite of the enemy
     */
    public DBEnemy(String name, int hp, DBImage sprite) {
        this.name = name;
        this.hp = hp;
        this.sprite = sprite;
    }

    /**
     * Constructor for DBEnemy
     * @param name The enemy's name
     * @param hp How many hit points the enemy has
     */
    public DBEnemy(String name, int hp) {
        this.name = name;
        this.hp = hp;
    }

    /**
     * Finds an enemy from the database, given its name
     * @param name The enemy's name
     * @return A DBEnemy instance of the enemy
     */
    public static DBEnemy find(String name) {
        return Database.Enemies.Companion.findEnemy(name);
    }

    /**
     * Saves this enemy, and all its references, to the database
     */
    public void save() {
        if (sprite != null) {
            sprite.save();
        }
        Database.Companion.save(this);
    }

    /**
     * Deletes this enemy, and all its references, from the database
     */
    public void delete() {
        if (sprite != null) {
            sprite.delete();
        }
        Database.Companion.delete(this);
    }
}
