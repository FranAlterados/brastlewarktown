package com.fduranortega.brastlewarktown.personlist.implementations;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fduranortega.brastlewarktown.R;
import com.fduranortega.brastlewarktown.model.Person;
import com.fduranortega.brastlewarktown.personlist.interfaces.PersonListPresenter;
import com.fduranortega.brastlewarktown.personlist.interfaces.PersonListView;
import com.fduranortega.brastlewarktown.personlist.ui.RVPersonAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PersonListViewImpl extends AppCompatActivity implements PersonListView {

    PersonListPresenter presenter;

    List<Person> lstPerson = new ArrayList<>();
    RVPersonAdapter adapter;

    @Bind(R.id.rvPersons)
    RecyclerView rvPersons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_list);

        ButterKnife.bind(this);

        //TODO dagger
        presenter = new PersonListPresenterImpl(this);

        initRecyclerView();


        presenter.displayData();
    }

    @Override
    public void displayData(List<Person> data) {
        lstPerson = data;
        reloadRecyclerView();
    }

    public void initRecyclerView() {
        rvPersons.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvPersons.setLayoutManager(llm);

        adapter = new RVPersonAdapter(lstPerson);
        rvPersons.setAdapter(adapter);
    }

    public void reloadRecyclerView() {
        adapter.swap(lstPerson);
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
