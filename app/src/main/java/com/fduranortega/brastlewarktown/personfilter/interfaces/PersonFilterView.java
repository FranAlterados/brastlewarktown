package com.fduranortega.brastlewarktown.personfilter.interfaces;

import com.fduranortega.brastlewarktown.model.Filter;

import java.util.List;

/**
 * Created by FranAlterados on 11/9/17.
 */
public interface PersonFilterView {

    public void setColorList(List<String> colorList);

    public void setProfessionList(List<String> professionList);

    public void showError(String message);

    public void clickFilter(Filter filter);
}
