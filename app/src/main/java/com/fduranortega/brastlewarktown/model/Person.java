package com.fduranortega.brastlewarktown.model;

import java.util.List;

/**
 * Created by FranAlterados on 6/9/17.
 */
public class Person {
    public static String ID_KEY = "id";

    String id;
    String name;
    String photo;
    String age;
    String weight;
    String height;
    String hairColor;
    List<String> professions;
    List<String> friendsNames;
    List<Person> friends;

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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public List<String> getProfessions() {
        return professions;
    }

    public void setProfessions(List<String> professions) {
        this.professions = professions;
    }

    public List<String> getFriendsNames() {
        return friendsNames;
    }

    public void setFriendsNames(List<String> friendsNames) {
        this.friendsNames = friendsNames;
    }

    public List<Person> getFriends() {
        return friends;
    }

    public void setFriends(List<Person> friends) {
        this.friends = friends;
    }
}
