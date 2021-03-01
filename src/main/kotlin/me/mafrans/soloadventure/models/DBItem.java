package me.mafrans.soloadventure.models;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import me.mafrans.soloadventure.Database;
import me.mafrans.soloadventure.AsciiColor;
import org.bson.types.ObjectId;

/**
 * Database entry holding information about an item in the game
 */
@Entity
public class DBItem {
    @Id
    public ObjectId id;

    /**
     * The item's name
     */
    public String name;

    /**
     * The item's description
     */
    public String description;

    /**
     * The item's color
     */
    public int color;

    /**
     * An array of tags, used to identify the item in code
     */
    public String[] tags;

    /**
     * Whether this item is a weapon or not
     */
    public boolean isWeapon;

    /**
     * If this item is a weapon, this entity contains weapon data
     */
    @Reference public DBWeapon weapon;

    /**
     * Default constructor for DBItem
     */
    public DBItem() {
        this("undefined", "");
    }

    /**
     * Constructor for DBItem
     * @param name The item's name
     * @param description The item's description
     */
    public DBItem(String name, String description) {
        this(name, description, AsciiColor.WHITE.fg(), new String[0], false, new DBWeapon());
    }

    /**
     * Constructor for DBItem
     * @param name The item's name
     * @param description The item's description
     * @param color The item's color
     * @param tags An array of tags, used to identify the item in code
     * @param isWeapon Whether the item is a weapon or not
     * @param weapon The item's weapon data
     */
    public DBItem(String name, String description, int color, String[] tags, boolean isWeapon, DBWeapon weapon) {
        this.name = name;
        this.description = description;
        this.color = color;
        this.tags = tags;
        this.isWeapon = isWeapon;
        this.weapon = weapon;
    }

    /**
     * Saves this item, and all its references, to the database
     */
    public void save() {
        weapon.save();
        Database.Companion.save(this);
    }

    /**
     * Deletes this item, and all its references, from the database
     */
    public void delete() {
        weapon.delete();
        Database.Companion.delete(this);
    }
}
