package com.fduranortega.brastlewarktown.persondetail.interfaces;

import com.fduranortega.brastlewarktown.model.Person;

/**
 * Created by FranAlterados on 8/9/17.
 */
public interface PersonDetailCallback {
    public void dataResponse(Person data);

    public void dataError(String message);

    public void showLoading();

    public void hideLoading();
}
