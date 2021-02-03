package me.mafrans.soloadventure.models;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import me.mafrans.soloadventure.Database;
import me.mafrans.soloadventure.editor.AsciiColor;
import org.bson.types.ObjectId;

@Entity
public class DBColorStyle {
    @Id private ObjectId id;

    public int foreground;
    public int background;

    public DBColorStyle() {
        this.foreground = AsciiColor.WHITE.fg();
        this.background = AsciiColor.BLACK.bg();
    }

    public DBColorStyle(int foreground, int background) {
        this.foreground = foreground;
        this.background = background;
    }

    public void save() {
        Database.Companion.save(this);
    }
}
