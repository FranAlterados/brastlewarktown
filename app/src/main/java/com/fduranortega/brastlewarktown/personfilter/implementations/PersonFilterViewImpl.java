package com.fduranortega.brastlewarktown.personfilter.implementations;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.fduranortega.brastlewarktown.R;
import com.fduranortega.brastlewarktown.model.Filter;
import com.fduranortega.brastlewarktown.personfilter.interfaces.PersonFilterPresenter;
import com.fduranortega.brastlewarktown.personfilter.interfaces.PersonFilterView;

import java.util.List;

public class PersonFilterViewImpl extends AppCompatActivity implements PersonFilterView {

    PersonFilterPresenter presenter;

    List<String> colorList;
    List<String> professionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_filter);

        presenter = new PersonFilterPresenterImpl(this);

        getSupportActionBar().setTitle(R.string.filter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void setColorList(List<String> colorList) {
        this.colorList = colorList;
    }

    @Override
    public void setProfessionList(List<String> professionList) {
        this.professionList = professionList;
    }

    @Override
    public void showError(String message) {
        Toast.makeText(PersonFilterViewImpl.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clickFilter(Filter filter) {
        //TODO
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
