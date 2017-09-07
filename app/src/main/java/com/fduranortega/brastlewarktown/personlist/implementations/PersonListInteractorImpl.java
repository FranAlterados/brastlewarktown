package com.fduranortega.brastlewarktown.personlist.implementations;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fduranortega.brastlewarktown.R;
import com.fduranortega.brastlewarktown.app.App;
import com.fduranortega.brastlewarktown.model.Person;
import com.fduranortega.brastlewarktown.personlist.interfaces.PersonListInteractor;
import com.fduranortega.brastlewarktown.personlist.interfaces.PersonListListener;
import com.fduranortega.brastlewarktown.rest.dto.DTOTown;
import com.fduranortega.brastlewarktown.rest.dto.mappers.PersonMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
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
        getFromService(listener);
//        getFromFile(listener);
    }

    private void getFromService(final PersonListListener listener) {
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

    private void getFromFile(final PersonListListener listener) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        objectMapper.setVisibilityChecker(objectMapper.getSerializationConfig().getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));

        try {
            InputStream is = App.INSTANCE.getResources().openRawResource(R.raw.data);
            Writer writer = new StringWriter();
            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                is.close();
            }
            String jsonString = writer.toString();
            DTOTown dto = objectMapper.readValue(jsonString, DTOTown.class);
            List<Person> lstPerson = PersonMapper.convertList(dto.getBrastlewark());
            listener.dataResponse(lstPerson);
        } catch (IOException e) {
            listener.dataError(e.getMessage());
            e.printStackTrace();
        }
    }


}
