package me.mafrans.soloadventure.models;

import dev.morphia.annotations.Entity;

@Entity
public class DBColorStyle {
    public int foreground;
    public int background;

    public DBColorStyle(int foreground, int background) {
        this.foreground = foreground;
        this.background = background;
    }

    @Override
    public String toString() {
        return "DBColorStyle{" +
                "foreground=" + foreground +
                ", background=" + background +
                '}';
    }
}
