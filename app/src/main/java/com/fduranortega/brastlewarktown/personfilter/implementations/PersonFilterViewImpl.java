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

    @Bind(R.id.etMinAge)
    EditText etMinAge;


    @Bind(R.id.etMaxAge)
    EditText etMaxAge;


    @Bind(R.id.etMinWeight)
    EditText etMinWeight;


    @Bind(R.id.etMaxWeight)
    EditText etMaxWeight;


    @Bind(R.id.etMinHeight)
    EditText etMinHeight;


    @Bind(R.id.etMaxHeight)
    EditText etMaxHeight;


    @Bind(R.id.spColor)
    Spinner spColor;


    @Bind(R.id.spProfession)
    Spinner spProfession;


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

        String name = null;
        if (!etName.getText().toString().equals("")) {
            name = etName.getText().toString();
        }

        Integer minAge = null;
        if (!etMinAge.getText().toString().equals("")) {
            minAge = Integer.parseInt(etMinAge.getText().toString());
        }

        Integer maxAge = null;
        if (!etMaxAge.getText().toString().equals("")) {
            maxAge = Integer.parseInt(etMaxAge.getText().toString());
        }

        Double minWeight = null;
        if (!etMinWeight.getText().toString().equals("")) {
            minWeight = Double.parseDouble(etMinWeight.getText().toString());
        }

        Double maxWeight = null;
        if (!etMaxWeight.getText().toString().equals("")) {
            maxWeight = Double.parseDouble(etMaxWeight.getText().toString());
        }

        Double minHeight = null;
        if (!etMinHeight.getText().toString().equals("")) {
            minHeight = Double.parseDouble(etMinHeight.getText().toString());
        }

        Double maxHeight = null;
        if (!etMaxHeight.getText().toString().equals("")) {
            maxHeight = Double.parseDouble(etMaxHeight.getText().toString());
        }

        String hairColor = null;
        if (spColor.getSelectedItemPosition() > -1) {
            hairColor = colorList.get(spColor.getSelectedItemPosition());
        }

        String profession = null;
        if (spProfession.getSelectedItemPosition() > -1) {
            profession = professionList.get(spProfession.getSelectedItemPosition());
        }

        filter.setName(name);
        filter.setMinAge(minAge);
        filter.setMaxAge(maxAge);
        filter.setMinWeight(minWeight);
        filter.setMaxWeight(maxWeight);
        filter.setMinHeight(minHeight);
        filter.setMaxHeight(maxHeight);
        filter.setHairColor(hairColor);
        filter.setProfession(profession);

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
