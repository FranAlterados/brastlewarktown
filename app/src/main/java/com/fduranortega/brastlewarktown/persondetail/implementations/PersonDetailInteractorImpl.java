package com.fduranortega.brastlewarktown.persondetail.implementations;

import com.fduranortega.brastlewarktown.app.App;
import com.fduranortega.brastlewarktown.model.Person;
import com.fduranortega.brastlewarktown.persondetail.interfaces.PersonDetailCallback;
import com.fduranortega.brastlewarktown.persondetail.interfaces.PersonDetailInteractor;
import com.fduranortega.brastlewarktown.realm.PersonDB;
import com.fduranortega.brastlewarktown.realm.mappers.PersonDBMapper;

/**
 * Created by FranAlterados on 8/9/17.
 */
public class PersonDetailInteractorImpl implements PersonDetailInteractor {

    @Override
    public void getData(String id, PersonDetailCallback callback) {
        callback.showLoading();
        PersonDB personDB = App.getRealm().where(PersonDB.class).equalTo("id", id).findFirst();
        Person person = PersonDBMapper.convert(personDB, true);
        callback.dataResponse(person);
        callback.hideLoading();
    }
}
