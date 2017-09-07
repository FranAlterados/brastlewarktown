package com.fduranortega.brastlewarktown.rest;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fduranortega.brastlewarktown.R;
import com.fduranortega.brastlewarktown.app.App;
import com.fduranortega.brastlewarktown.rest.service.PersonService;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.JacksonConverter;

/**
 * Created by FranAlterados on 6/9/17.
 */
public class RestClient {
    private static final String BASE_URL = App.INSTANCE.getApplicationContext().getString(R.string.urlApi);

    private PersonService personService;

    public RestClient() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OkHttpClient client = new OkHttpClient();
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestInterceptor.RequestFacade request) {
                request.addHeader("Accept", "application/json");
//                request.addHeader("Content-type", "text/plain; charset=UTF-8");

            }
        };
        Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BASE_URL)
                .setClient(new OkClient(client))
                .setRequestInterceptor(requestInterceptor)
                .setConverter(new JacksonConverter(mapper))
                .build();
        initServices(restAdapter);
    }

    private void initServices(RestAdapter restAdapter) {
        personService = restAdapter.create(PersonService.class);
    }

    public PersonService getPersonService() {
        return personService;
    }
}
