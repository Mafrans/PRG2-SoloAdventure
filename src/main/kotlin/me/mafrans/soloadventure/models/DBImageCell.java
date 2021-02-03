package me.mafrans.soloadventure.models;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Reference;

@Entity
public class DBImageCell {
    public String text;
    @Reference public DBColorStyle style;

    public DBImageCell(String text, DBColorStyle style) {
        this.text = text;
        this.style = style;
    }

    @Override
    public String toString() {
        return "DBImageCell{" +
                "text='" + text + '\'' +
                ", style=" + style +
                '}';
    }
}
