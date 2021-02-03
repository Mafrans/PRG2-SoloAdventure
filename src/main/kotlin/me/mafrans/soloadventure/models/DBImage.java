package me.mafrans.soloadventure.models;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Reference;
import me.mafrans.soloadventure.Database;

@Entity
public class DBImage {
    public String name;
    @Reference public DBImageCell[][] cells;

    public DBImage(DBImageCell[][] cells) {
        this.cells = cells;
    }

    public static DBImage find(String name) {
        return Database.Images.Companion.findImage(name);
    }

    public void save() {
        Database.Companion.save(this);
    }
}
