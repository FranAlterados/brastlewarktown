package com.fduranortega.brastlewarktown.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by FranAlterados on 8/9/17.
 */
public class ColorDB extends RealmObject {
    @PrimaryKey
    String id;
    String color;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
