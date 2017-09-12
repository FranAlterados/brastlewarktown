package com.fduranortega.brastlewarktown.personlist.implementations;

import com.fduranortega.brastlewarktown.model.Filter;
import com.fduranortega.brastlewarktown.model.Person;
import com.fduranortega.brastlewarktown.personlist.interfaces.PersonListCallback;
import com.fduranortega.brastlewarktown.personlist.interfaces.PersonListInteractor;
import com.fduranortega.brastlewarktown.personlist.interfaces.PersonListPresenter;
import com.fduranortega.brastlewarktown.personlist.interfaces.PersonListView;

import java.util.List;

/**
 * Created by FranAlterados on 6/9/17.
 */
public class PersonListPresenterImpl implements PersonListPresenter, PersonListCallback {

    private PersonListView view;
    private PersonListInteractor interactor;

    public PersonListPresenterImpl(PersonListView view) {
        this.view = view;

        //TODO dagger
        interactor = new PersonListInteractorImpl();
    }

    @Override
    public void getData(Filter filter) {
        interactor.getData(filter, this);
    }

    @Override
    public void showLoading() {
        view.showLoading();
    }

    @Override
    public void hideLoading() {
        view.hideLoading();
    }

    @Override
    public void displayFilterActionBar() {
        view.displayFilterActionBar();
    }

    @Override
    public void showFilterButton() {
        view.showFilterButton();
    }

    @Override
    public void dataResponse(List<Person> data) {
        view.displayData(data);
    }

    @Override
    public void dataError(String message) {
        view.showError(message);
    }
}
