package com.fduranortega.brastlewarktown.persondetail.implementations;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fduranortega.brastlewarktown.R;
import com.fduranortega.brastlewarktown.model.Person;
import com.fduranortega.brastlewarktown.persondetail.interfaces.PersonDetailPresenter;
import com.fduranortega.brastlewarktown.persondetail.interfaces.PersonDetailView;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PersonDetailViewImpl extends AppCompatActivity implements PersonDetailView {

    @Bind(R.id.ivPhoto)
    ImageView ivPhoto;

    @Bind(R.id.tvName)
    TextView tvName;

    String id;
    Person person;
    PersonDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_detail);

        ButterKnife.bind(this);

        presenter = new PersonDetailPresenterImpl(this);

        id = getIntent().getExtras().getString(Person.ID_KEY);
        presenter.getData(id);
    }

    @Override
    public void displayData(Person person) {
        this.person = person;

        Picasso.with(this).load(person.getPhoto()).into(ivPhoto);
        tvName.setText(person.getName());
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
