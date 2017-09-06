package com.fduranortega.brastlewarktown.personlist.implementations;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.fduranortega.brastlewarktown.R;
import com.fduranortega.brastlewarktown.model.Person;
import com.fduranortega.brastlewarktown.personlist.interfaces.PersonListPresenter;
import com.fduranortega.brastlewarktown.personlist.interfaces.PersonListView;

import java.util.List;

public class PersonListViewImpl extends AppCompatActivity implements PersonListView {

    PersonListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_list);

        //TODO inyectar con dagger
        presenter = new PersonListPresenterImpl(this);

        presenter.displayData();
    }

    @Override
    public void displayData(List<Person> data) {
        Toast.makeText(PersonListViewImpl.this, "Hola", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clickPerson() {

    }

    @Override
    public void clickFilter() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String message) {

    }
}
