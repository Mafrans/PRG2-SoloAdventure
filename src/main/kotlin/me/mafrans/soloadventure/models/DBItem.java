package me.mafrans.soloadventure.models;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import me.mafrans.soloadventure.Database;
import me.mafrans.soloadventure.editor.AsciiColor;
import org.bson.types.ObjectId;

import java.util.Arrays;

@Entity
public class DBItem {
    @Id
    public ObjectId id;

    public String name;
    public String description;
    public int color;
    public String[] tags;
    public boolean isWeapon;

    @Reference public DBWeapon weapon;

    public DBItem() {
        this("undefined", "");
    }

    public DBItem(String name, String description) {
        this(name, description, AsciiColor.WHITE.fg(), new String[0], false, new DBWeapon());
    }

    public DBItem(String name, String description, int color, String[] tags, boolean isWeapon, DBWeapon weapon) {
        this.name = name;
        this.description = description;
        this.color = color;
        this.tags = tags;
        this.isWeapon = isWeapon;
        this.weapon = weapon;
    }

    public void save() {
        weapon.save();
        Database.Companion.save(this);
    }

    @Override
    public String toString() {
        return "DBItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", color=" + color +
                ", tags=" + Arrays.toString(tags) +
                ", isWeapon=" + isWeapon +
                ", weapon=" + weapon +
                '}';
    }
}
