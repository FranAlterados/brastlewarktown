package com.fduranortega.brastlewarktown.persondetail.interfaces;

import com.fduranortega.brastlewarktown.model.Person;

/**
 * Created by FranAlterados on 8/9/17.
 */
public interface PersonDetailView {

    public void displayData(Person person);

    public void showLoading();

    public void hideLoading();

    public void showError(String message);

    public void clickFriend(Person person);

}
