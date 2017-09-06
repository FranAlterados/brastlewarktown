package com.fduranortega.brastlewarktown.personlist.implementations;

import com.fduranortega.brastlewarktown.app.App;
import com.fduranortega.brastlewarktown.model.Person;
import com.fduranortega.brastlewarktown.personlist.interfaces.PersonListInteractor;
import com.fduranortega.brastlewarktown.personlist.interfaces.PersonListListener;
import com.fduranortega.brastlewarktown.rest.dto.DTOTown;
import com.fduranortega.brastlewarktown.rest.dto.mappers.PersonMapper;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by FranAlterados on 6/9/17.
 */
public class PersonListInteractorImpl implements PersonListInteractor {


    public PersonListInteractorImpl() {

    }


    @Override
    public void getData(final PersonListListener listener) {
        App.getRestClient().getPersonService().getPersons(new Callback<DTOTown>() {
            @Override
            public void success(DTOTown dtoTown, Response response) {
                List<Person> lstPerson = PersonMapper.convertList(dtoTown.getBrastlewark());
                listener.dataResponse(lstPerson);
            }

            @Override
            public void failure(RetrofitError error) {
                listener.dataError(error.getMessage());
            }
        });
    }
}
