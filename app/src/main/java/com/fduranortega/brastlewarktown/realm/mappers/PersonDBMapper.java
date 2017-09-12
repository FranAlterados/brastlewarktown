package com.fduranortega.brastlewarktown.realm.mappers;

import com.fduranortega.brastlewarktown.app.App;
import com.fduranortega.brastlewarktown.model.Person;
import com.fduranortega.brastlewarktown.realm.PersonDB;
import com.fduranortega.brastlewarktown.realm.ProfessionDB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by FranAlterados on 10/9/17.
 */
public class PersonDBMapper {

    public static Person convert(PersonDB personDB, Boolean getFriends) {
        Person person = new Person();

        person.setId(personDB.getId());
        person.setName(personDB.getName());
        person.setPhoto(personDB.getPhoto());
        person.setAge(personDB.getAge().toString());
        person.setWeight(personDB.getWeight().toString());
        person.setHeight(personDB.getHeight().toString());
        person.setHairColor(personDB.getHairColor().getColor());

        List<String> professions = new ArrayList<>();
        for (ProfessionDB professionDB : personDB.getProfessions()) {
            professions.add(professionDB.getProfession());
        }
        person.setProfessions(professions);

        if (getFriends) {
            List<Person> friends = new ArrayList<>();
            List<String> lstNames = Arrays.asList(personDB.getFriendNames().split(","));
            for (String friendName : lstNames) {
                PersonDB friend = App.getRealm().where(PersonDB.class).equalTo("name", friendName).findFirst();
                if (friend != null) {
                    //set false to stop recursion
                    friends.add(convert(friend, false));
                }
            }
            person.setFriends(friends);
        }

        return person;
    }

    public static List<Person> convertList(List<PersonDB> lstPersonDB) {
        List<Person> lstPerson = new ArrayList<>();

        if (lstPersonDB != null) {
            for (PersonDB personDB : lstPersonDB) {
                lstPerson.add(convert(personDB, false));
            }
        }

        return lstPerson;
    }
}
