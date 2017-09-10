package com.fduranortega.brastlewarktown.persondetail.implementations;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.fduranortega.brastlewarktown.R;
import com.fduranortega.brastlewarktown.model.Person;
import com.fduranortega.brastlewarktown.persondetail.interfaces.PersonDetailPresenter;
import com.fduranortega.brastlewarktown.persondetail.interfaces.PersonDetailView;

public class PersonDetailViewImpl extends AppCompatActivity implements PersonDetailView {

    String id;
    PersonDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_detail);

        presenter = new PersonDetailPresenterImpl(this);

        id = getIntent().getExtras().getString(Person.ID_KEY);
        presenter.getData(id);
    }

    @Override
    public void displayData(Person person) {
        //TODO
        Toast.makeText(PersonDetailViewImpl.this, person.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        //TODO
    }

    @Override
    public void hideLoading() {
        //TODO
    }

    @Override
    public void showError(String message) {
        Toast.makeText(PersonDetailViewImpl.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clickFriend(Person person) {
        //TODO
    }
}
