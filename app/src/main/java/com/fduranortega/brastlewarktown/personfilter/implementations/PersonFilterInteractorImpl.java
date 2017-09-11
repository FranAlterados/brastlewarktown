package com.fduranortega.brastlewarktown.personfilter.implementations;

import com.fduranortega.brastlewarktown.R;
import com.fduranortega.brastlewarktown.app.App;
import com.fduranortega.brastlewarktown.personfilter.interfaces.PersonFilterCallback;
import com.fduranortega.brastlewarktown.personfilter.interfaces.PersonFilterInteractor;
import com.fduranortega.brastlewarktown.realm.ColorDB;
import com.fduranortega.brastlewarktown.realm.ProfessionDB;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;

/**
 * Created by FranAlterados on 11/9/17.
 */
public class PersonFilterInteractorImpl implements PersonFilterInteractor {

    @Override
    public void getColorList(PersonFilterCallback callback) {
        if (!App.getRealm().isInTransaction()) {
            RealmResults<ColorDB> colorDBList = App.getRealm().where(ColorDB.class).findAll();
            List<String> lstColor = new ArrayList<>();
            if (colorDBList != null) {
                for (ColorDB colorDB : colorDBList) {
                    lstColor.add(colorDB.getColor());
                }
            }
            callback.colorResponse(lstColor);
        } else {
            callback.dataError(App.INSTANCE.getApplicationContext().getString(R.string.data_not_ready));
        }
    }

    @Override
    public void getProfessionList(PersonFilterCallback callback) {
        if (!App.getRealm().isInTransaction()) {
            RealmResults<ProfessionDB> professionDBList = App.getRealm().where(ProfessionDB.class).findAll();
            List<String> lstProfession = new ArrayList<>();
            if (professionDBList != null) {
                for (ProfessionDB professionDB : professionDBList) {
                    lstProfession.add(professionDB.getProfession());
                }
            }
            callback.professionResponse(lstProfession);
        } else {
            callback.dataError(App.INSTANCE.getApplicationContext().getString(R.string.data_not_ready));
        }
    }
}
