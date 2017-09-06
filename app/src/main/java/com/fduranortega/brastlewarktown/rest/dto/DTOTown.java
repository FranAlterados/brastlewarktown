package com.fduranortega.brastlewarktown.rest.dto;

import java.util.List;

/**
 * Created by FranAlterados on 6/9/17.
 */
public class DTOTown {
    List<DTOPerson> Brastlewark;

    public List<DTOPerson> getBrastlewark() {
        return Brastlewark;
    }

    public void setBrastlewark(List<DTOPerson> brastlewark) {
        Brastlewark = brastlewark;
    }
}
