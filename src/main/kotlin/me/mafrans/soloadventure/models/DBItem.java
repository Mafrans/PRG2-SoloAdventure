package me.mafrans.soloadventure.models;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import me.mafrans.soloadventure.Database;
import me.mafrans.soloadventure.editor.AsciiColor;
import org.bson.types.ObjectId;

@Entity
public class DBItem {
    @Id private ObjectId id;

    public String name;
    public String description;
    public int color;
    public String[] tags;
    public boolean isWeapon;

    @Reference public DBWeapon weapon;

    public DBItem() { }

    public DBItem(String name, String description, int color, String[] tags, boolean isWeapon, DBWeapon weapon) {
        this.name = name;
        this.description = description;
        this.color = color;
        this.tags = tags;
        this.isWeapon = isWeapon;
        this.weapon = weapon;
    }

    public DBItem(String name, String description) {
        this.name = name;
        this.description = description;
        this.color = AsciiColor.WHITE.fg();
    }

    public void save() {
        weapon.save();
        Database.Companion.save(this);
    }
}
