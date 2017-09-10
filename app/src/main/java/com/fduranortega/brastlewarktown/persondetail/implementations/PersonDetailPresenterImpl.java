package com.fduranortega.brastlewarktown.persondetail.implementations;

import com.fduranortega.brastlewarktown.model.Person;
import com.fduranortega.brastlewarktown.persondetail.interfaces.PersonDetailCallback;
import com.fduranortega.brastlewarktown.persondetail.interfaces.PersonDetailInteractor;
import com.fduranortega.brastlewarktown.persondetail.interfaces.PersonDetailPresenter;
import com.fduranortega.brastlewarktown.persondetail.interfaces.PersonDetailView;

/**
 * Created by FranAlterados on 8/9/17.
 */
public class PersonDetailPresenterImpl implements PersonDetailPresenter, PersonDetailCallback {

    PersonDetailView view;
    PersonDetailInteractor interactor;

    public PersonDetailPresenterImpl(PersonDetailView view) {
        this.view = view;

        interactor = new PersonDetailInteractorImpl();
    }

    @Override
    public void dataResponse(Person data) {
        view.displayData(data);
    }

    @Override
    public void dataError(String message) {
        view.showError(message);
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
    public void getData(String id) {
        interactor.getData(id);
    }
}
