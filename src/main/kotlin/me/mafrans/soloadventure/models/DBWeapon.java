package me.mafrans.soloadventure.models;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import me.mafrans.soloadventure.Database;
import org.bson.types.ObjectId;

/**
 * A database entity holding information about an item's weapon data
 */
@Entity
public class DBWeapon {
    @Id private ObjectId id;

    /**
     * The weapon's base damage
     */
    public int damage;

    /**
     * The weapon's attack damage variance, damage is calculated as `damage + Math.round((Math.random()*2-1) * variance)`
     */
    public int variance;

    /**
     * How likely the weapon is to crit, dealing twice as much damage
     */
    public int critPercent;

    /**
     * An array of messages, of which one is chosen at random when the player attacks
     */
    public String[] attackMessages;

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
