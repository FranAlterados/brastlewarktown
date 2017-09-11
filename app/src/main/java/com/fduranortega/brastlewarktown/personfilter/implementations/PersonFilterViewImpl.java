package com.fduranortega.brastlewarktown.personfilter.implementations;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.fduranortega.brastlewarktown.R;
import com.fduranortega.brastlewarktown.model.Filter;
import com.fduranortega.brastlewarktown.personfilter.interfaces.PersonFilterPresenter;
import com.fduranortega.brastlewarktown.personfilter.interfaces.PersonFilterView;
import com.fduranortega.brastlewarktown.personlist.implementations.PersonListViewImpl;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonFilterViewImpl extends AppCompatActivity implements PersonFilterView {

    @Bind(R.id.etName)
    EditText etName;
    //etName

    @Bind(R.id.etMinAge)
    EditText etMinAge;
    //etMinAge

    @Bind(R.id.etMaxAge)
    EditText etMaxAge;
    //etMaxAge

    @Bind(R.id.etMinWeight)
    EditText etMinWeight;
    //etMinWeight

    @Bind(R.id.etMaxWeight)
    EditText etMaxWeight;
    //etMaxWeight

    @Bind(R.id.etMinHeight)
    EditText etMinHeight;
    //etMinHeight

    @Bind(R.id.etMaxHeight)
    EditText etMaxHeight;
    //etMaxHeight

    @Bind(R.id.spColor)
    Spinner spColor;
    //spColor

    @Bind(R.id.spProfession)
    Spinner spProfession;
    //spProfession

    @Bind(R.id.btFilter)
    Button btFilter;
    //btFilter

    PersonFilterPresenter presenter;

    List<String> colorList;
    List<String> professionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_filter);

        ButterKnife.bind(this);

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
        Intent intent = new Intent(this, PersonListViewImpl.class);
        intent.putExtra(Filter.FILTER_KEY, filter);
        startActivity(intent);
    }

    @OnClick(R.id.btFilter)
    public void clickFilterButton() {
        Filter filter = new Filter();
        //TODO make filter

        clickFilter(filter);
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
