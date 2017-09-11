package com.fduranortega.brastlewarktown.personfilter.interfaces;

import java.util.List;

/**
 * Created by FranAlterados on 11/9/17.
 */
public interface PersonFilterCallback {

    public void colorResponse(List<String> lstColor);

    public void professionResponse(List<String> lstProfession);

    public void dataError(String message);

}
