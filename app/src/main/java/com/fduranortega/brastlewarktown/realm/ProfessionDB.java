package com.fduranortega.brastlewarktown.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by FranAlterados on 8/9/17.
 */
public class ProfessionDB extends RealmObject {
    @PrimaryKey
    String id;
    String profession;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
