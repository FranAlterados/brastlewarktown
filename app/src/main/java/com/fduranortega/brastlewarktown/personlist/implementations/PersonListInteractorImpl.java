package com.fduranortega.brastlewarktown.personlist.implementations;

import com.fduranortega.brastlewarktown.model.Person;
import com.fduranortega.brastlewarktown.personlist.interfaces.PersonListInteractor;
import com.fduranortega.brastlewarktown.personlist.interfaces.PersonListListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FranAlterados on 6/9/17.
 */
public class PersonListInteractorImpl implements PersonListInteractor {


    public PersonListInteractorImpl() {

    }


    @Override
    public void getData(PersonListListener listener) {
        //TODO
        List<Person> fakePersons = new ArrayList<>();
        listener.dataResponse(fakePersons);
    }
}
