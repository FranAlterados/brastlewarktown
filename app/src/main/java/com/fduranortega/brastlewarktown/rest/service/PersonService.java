package com.fduranortega.brastlewarktown.rest.service;

import com.fduranortega.brastlewarktown.rest.dto.DTOTown;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by FranAlterados on 6/9/17.
 */
public interface PersonService {
    @GET("/data.json")
    public void getPersons(Callback<DTOTown> callback);
}
