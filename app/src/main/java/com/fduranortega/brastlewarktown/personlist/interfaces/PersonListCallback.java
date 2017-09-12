package com.fduranortega.brastlewarktown.personlist.interfaces;

import com.fduranortega.brastlewarktown.model.Person;

import java.util.List;

/**
 * Created by FranAlterados on 6/9/17.
 */
public interface PersonListCallback {
    public void dataResponse(List<Person> data);

    public void dataError(String message);

    public void showLoading();

    public void hideLoading();

    public void displayFilterActionBar();

    public void showFilterButton();
}
