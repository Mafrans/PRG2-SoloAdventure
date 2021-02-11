package me.mafrans.soloadventure.models;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import org.bson.types.ObjectId;

@Entity
public class DBWeapon {
    @Id private ObjectId id;

    public int damage;
    public int variance;
    public int critPercent;
    public String[] attackMessages;
}
