package me.mafrans.soloadventure.models;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import me.mafrans.soloadventure.Database;
import me.mafrans.soloadventure.AsciiColor;
import org.bson.types.ObjectId;

/**
 * Database Entity holding color styles for a sprite cell.
 */
@Entity
public class DBColorStyle {
    @Id private ObjectId id;

    /**
     * The foreground color, represented as an ANSI integer
     */
    public int foreground;
    /**
     * The background color, represented as an ANSI integer
     */
    public int background;

    /**
     * Default constructor for DBColorStyle
     */
    public DBColorStyle() {
        this.foreground = AsciiColor.WHITE.fg();
        this.background = AsciiColor.BLACK.bg();
    }

    /**
     * Constructor for DBColorStyle
     * @param foreground The foreground color, represented as an ANSI integer
     * @param background The background color, represented as an ANSI integer
     */
    public DBColorStyle(int foreground, int background) {
        this.foreground = foreground;
        this.background = background;
    }

    /**
     * Save this entry to the Database
     */
    public void save() {
        Database.Companion.save(this);
    }

    /**
     * Delete this entry from the Database
     */
    public void delete() {
        Database.Companion.delete(this);
    }
}
