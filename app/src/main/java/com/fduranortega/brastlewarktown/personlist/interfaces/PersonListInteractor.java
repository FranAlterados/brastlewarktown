package com.fduranortega.brastlewarktown.personlist.interfaces;

import com.fduranortega.brastlewarktown.model.Filter;

/**
 * Created by FranAlterados on 6/9/17.
 */
public interface PersonListInteractor {

    public void getData(Filter filter, PersonListCallback callback);
}
