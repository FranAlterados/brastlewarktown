package com.fduranortega.brastlewarktown.realm;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by FranAlterados on 8/9/17.
 */
public class PersonDB extends RealmObject {
    @PrimaryKey
    String id;
    String name;
    String photo;
    Integer age;
    Double weight;
    Double height;
    String friendNames;
    ColorDB hairColor;
    RealmList<ProfessionDB> professions;
    RealmList<PersonDB> friends;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public ColorDB getHairColor() {
        return hairColor;
    }

    public void setHairColor(ColorDB hairColor) {
        this.hairColor = hairColor;
    }

    public RealmList<ProfessionDB> getProfessions() {
        return professions;
    }

    public void setProfessions(RealmList<ProfessionDB> professions) {
        this.professions = professions;
    }

    public RealmList<PersonDB> getFriends() {
        return friends;
    }

    public void setFriends(RealmList<PersonDB> friends) {
        this.friends = friends;
    }

    public String getFriendNames() {
        return friendNames;
    }

    public void setFriendNames(String friendNames) {
        this.friendNames = friendNames;
    }
}
