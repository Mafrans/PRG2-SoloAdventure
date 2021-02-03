package me.mafrans.soloadventure.models;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import me.mafrans.soloadventure.Database;
import org.bson.types.ObjectId;

@Entity
public class DBImageCell {
    @Id private ObjectId id;

    public String text;
    @Reference public DBColorStyle style;

    public DBImageCell() {
        this.text = "";
        this.style = new DBColorStyle();
    }

    public DBImageCell(String text, DBColorStyle style) {
        this.text = text;
        this.style = style;
    }

    public void save() {
        style.save();
        Database.Companion.save(this);
    }
}
