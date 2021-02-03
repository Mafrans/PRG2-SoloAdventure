package me.mafrans.soloadventure.models;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import me.mafrans.soloadventure.Database;
import org.bson.types.ObjectId;

@Entity
public class DBImage {
    public static int DEFAULT_WIDTH = 24;
    public static int DEFAULT_HEIGHT = 16;

    @Id private ObjectId id;

    @Reference public DBImageCell[][] cells;

    public DBImage() {
        this.cells = new DBImageCell[DEFAULT_WIDTH][DEFAULT_HEIGHT];
    }

    public DBImage(DBImageCell[][] cells) {
        this.cells = cells;
    }

    public static DBImage find(String name) {
        return Database.Images.Companion.findImage(name);
    }

    public void save() {
        for (DBImageCell[] column : cells) {
            for (DBImageCell cell : column) {
                cell.save();
            }
        }
        Database.Companion.save(this);
    }
}
