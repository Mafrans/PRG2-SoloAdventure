package me.mafrans.soloadventure.models;

import me.mafrans.soloadventure.Database;

public class DBEnemy {
    public String name;
    public int hp;
    public DBImage sprite;

    public DBEnemy(String name, int hp, DBImage sprite) {
        this.name = name;
        this.hp = hp;
        this.sprite = sprite;
    }

    public DBEnemy(String name, int hp) {
        this.name = name;
        this.hp = hp;
    }

    public static DBEnemy find(String name) {
        return Database.Enemies.Companion.findEnemy(name);
    }

    public void save() {
        Database.Companion.save(this);
    }
}
