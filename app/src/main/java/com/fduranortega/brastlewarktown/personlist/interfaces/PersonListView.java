package com.fduranortega.brastlewarktown.personlist.interfaces;

import com.fduranortega.brastlewarktown.model.Person;

import java.util.List;

/**
 * Created by FranAlterados on 6/9/17.
 */
public interface PersonListView {

    public void displayData(List<Person> data);

    public void showLoading();

    public void hideLoading();

    public void showError(String message);

    public void clickPerson(Person person);

    public void clickFilter();
}
