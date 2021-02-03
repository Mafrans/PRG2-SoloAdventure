package me.mafrans.soloadventure.models;

import me.mafrans.soloadventure.Database;

public class DBImage {
    public DBImageCell[][] cells;

    public DBImage(DBImageCell[][] cells) {
        this.cells = cells;
    }

    public DBImage find(String name) {
        return Database.Images.Companion.findImage(name);
    }
}
