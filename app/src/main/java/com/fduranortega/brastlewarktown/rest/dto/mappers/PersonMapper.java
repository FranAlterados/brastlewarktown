package com.fduranortega.brastlewarktown.rest.dto.mappers;

import com.fduranortega.brastlewarktown.model.Person;
import com.fduranortega.brastlewarktown.rest.dto.DTOPerson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FranAlterados on 6/9/17.
 */
public class PersonMapper {

    public static Person convert(DTOPerson dtoPerson) {
        Person person = new Person();

        person.setId(dtoPerson.getId());
        person.setName(dtoPerson.getName());
        person.setPhoto(dtoPerson.getThumbnail());
        person.setAge(dtoPerson.getAge());
        person.setWeight(dtoPerson.getWeight());
        person.setHeight(dtoPerson.getHeight());
        person.setHairColor(dtoPerson.getHairColor());
        person.setProfessions(dtoPerson.getProfessions());
        person.setFriends(dtoPerson.getFriends());

        return person;
    }

    public static List<Person> convertList(List<DTOPerson> lstDtoPerson) {
        List<Person> lstPerson = new ArrayList<>();

        if (lstDtoPerson != null) {
            for (DTOPerson dtoPerson : lstDtoPerson) {
                lstPerson.add(convert(dtoPerson));
            }
        }

        return lstPerson;
    }
}
