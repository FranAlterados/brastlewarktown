package com.fduranortega.brastlewarktown.realm.mappers;

import com.fduranortega.brastlewarktown.model.Person;
import com.fduranortega.brastlewarktown.realm.PersonDB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FranAlterados on 10/9/17.
 */
public class PersonDBMapper {

    public static Person convert(PersonDB dtoPerson) {
        Person person = new Person();

        //TODO match fields

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
