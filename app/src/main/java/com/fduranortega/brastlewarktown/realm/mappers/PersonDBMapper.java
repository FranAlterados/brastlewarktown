package com.fduranortega.brastlewarktown.realm.mappers;

import com.fduranortega.brastlewarktown.model.Person;
import com.fduranortega.brastlewarktown.realm.PersonDB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FranAlterados on 10/9/17.
 */
public class PersonDBMapper {

    public static Person convert(PersonDB personDB) {
        Person person = new Person();

        person.setId(personDB.getId());
        person.setName(personDB.getName());
        person.setPhoto(personDB.getPhoto());
        person.setAge(personDB.getAge());
        person.setWeight(personDB.getWeight());
        person.setHeight(personDB.getHeight());
        person.setHairColor(personDB.getHairColor().getColor());
        //TODO
//        person.setProfessions(personDB.get());
        //TODO
//        person.setFriends(personDB.get());

        return person;
    }

    public static List<Person> convertList(List<PersonDB> lstPersonDB) {
        List<Person> lstPerson = new ArrayList<>();

        if (lstPersonDB != null) {
            for (PersonDB personDB : lstPersonDB) {
                lstPerson.add(convert(personDB));
            }
        }

        return lstPerson;
    }
}
