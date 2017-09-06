package com.fduranortega.brastlewarktown.personlist.interfaces;

/**
 * Created by FranAlterados on 6/9/17.
 */
public interface PersonListPresenter {

    public void displayData();

    public void showLoading();

    public void hideLoading();

    public void showError(String message);
}
