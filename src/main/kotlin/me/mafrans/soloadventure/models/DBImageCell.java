package me.mafrans.soloadventure.models;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import me.mafrans.soloadventure.Database;
import org.bson.types.ObjectId;

/**
 * A database entity holding a single cell (pixel) of a {@link DBImage}
 */
@Entity
public class DBImageCell {
    @Id private ObjectId id;

    /**
     * The text in the cell
     */
    public String text;

    /**
     * The cell's style (color, background)
     */
    @Reference public DBColorStyle style;

    /**
     * Default constructor for DBImageCell
     */
    public DBImageCell() {
        this.text = "";
        this.style = new DBColorStyle();
    }

    /**
     * Constructor for DBImageCell
     * @param text The text in the cell
     * @param style The cell's style (color, background)
     */
    public DBImageCell(String text, DBColorStyle style) {
        this.text = text;
        this.style = style;
    }

    /**
     * Saves this DBImageCell entity, and all its references, to the database
     */
    public void save() {
        style.save();
        Database.Companion.save(this);
    }

    /**
     * Deletes this DBImageCell entity, and all its references, from the database
     */
    public void delete() {
        style.delete();
        Database.Companion.delete(this);
    }
}
