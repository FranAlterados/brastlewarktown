package com.fduranortega.brastlewarktown.personlist.interfaces;

import com.fduranortega.brastlewarktown.model.Person;

import java.util.List;

/**
 * Created by FranAlterados on 6/9/17.
 */
public interface PersonListListener {
    public void dataResponse(List<Person> data);
}
