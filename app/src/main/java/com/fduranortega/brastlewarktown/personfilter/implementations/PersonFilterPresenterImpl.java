package com.fduranortega.brastlewarktown.personfilter.implementations;

import com.fduranortega.brastlewarktown.personfilter.interfaces.PersonFilterCallback;
import com.fduranortega.brastlewarktown.personfilter.interfaces.PersonFilterInteractor;
import com.fduranortega.brastlewarktown.personfilter.interfaces.PersonFilterPresenter;
import com.fduranortega.brastlewarktown.personfilter.interfaces.PersonFilterView;

import java.util.List;

/**
 * Created by FranAlterados on 11/9/17.
 */
public class PersonFilterPresenterImpl implements PersonFilterPresenter, PersonFilterCallback {

    PersonFilterView view;
    PersonFilterInteractor interactor;

    public PersonFilterPresenterImpl(PersonFilterView view) {
        this.view = view;

        interactor = new PersonFilterInteractorImpl();
    }

    @Override
    public void getColorList(List<String> colorList) {
        interactor.getColorList(this);
    }

    @Override
    public void getProfessionList(List<String> professionList) {
        interactor.getProfessionList(this);
    }

    @Override
    public void colorResponse(List<String> lstColor) {
        view.setColorList(lstColor);
    }

    @Override
    public void professionResponse(List<String> lstProfession) {
        view.setProfessionList(lstProfession);
    }

    @Override
    public void dataError(String message) {
        view.showError(message);
    }
}
