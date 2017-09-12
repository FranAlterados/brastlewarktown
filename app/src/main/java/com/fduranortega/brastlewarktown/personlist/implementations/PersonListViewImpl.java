package com.fduranortega.brastlewarktown.personlist.implementations;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.fduranortega.brastlewarktown.R;
import com.fduranortega.brastlewarktown.model.Filter;
import com.fduranortega.brastlewarktown.model.Person;
import com.fduranortega.brastlewarktown.persondetail.implementations.PersonDetailViewImpl;
import com.fduranortega.brastlewarktown.personfilter.implementations.PersonFilterViewImpl;
import com.fduranortega.brastlewarktown.personlist.interfaces.PersonListPresenter;
import com.fduranortega.brastlewarktown.personlist.interfaces.PersonListView;
import com.fduranortega.brastlewarktown.personlist.ui.RVPersonAdapter;
import com.fduranortega.brastlewarktown.personlist.ui.RecyclerViewClickListener;

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

    @Bind(R.id.srlPersonList)
    SwipeRefreshLayout srlPersonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_list);

        ButterKnife.bind(this);

        //TODO dagger
        presenter = new PersonListPresenterImpl(this);

        initRecyclerView();

        Filter filter = null;
        if (getIntent() != null) {
            filter = (Filter) getIntent().getSerializableExtra(Filter.FILTER_KEY);
        }

        presenter.getData(filter);

    }

    @Override
    public void showFilterButton() {
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayOptions(actionBar.getDisplayOptions()
                | ActionBar.DISPLAY_SHOW_CUSTOM);
        ImageView imageView = new ImageView(actionBar.getThemedContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        imageView.setImageResource(android.R.drawable.ic_menu_search);
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT, Gravity.RIGHT
                | Gravity.CENTER_VERTICAL);
        layoutParams.rightMargin = 40;
        imageView.setLayoutParams(layoutParams);
        actionBar.setCustomView(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickFilter();
            }
        });
    }

    @Override
    public void displayFilterActionBar() {
        getSupportActionBar().setTitle(R.string.filtered_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        adapter.setItemListener(new RecyclerViewClickListener() {
            @Override
            public void recyclerViewListClicked(View v, int position) {
                Person personClicked = lstPerson.get(position);
                clickPerson(personClicked);
            }
        });

        rvPersons.setAdapter(adapter);
    }

    public void reloadRecyclerView() {
        adapter.swap(lstPerson);
    }

    @Override
    public void clickPerson(Person person) {
        Intent intent = new Intent(this, PersonDetailViewImpl.class);
        intent.putExtra(Person.ID_KEY, person.getId());
        startActivity(intent);
    }

    @Override
    public void clickFilter() {
        Intent intent = new Intent(this, PersonFilterViewImpl.class);
        startActivity(intent);
    }

    @Override
    public void showLoading() {
        srlPersonList.setEnabled(true);
        srlPersonList.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        srlPersonList.setEnabled(false);
        srlPersonList.setRefreshing(false);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(PersonListViewImpl.this, message, Toast.LENGTH_SHORT).show();
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
