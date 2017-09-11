package com.fduranortega.brastlewarktown.persondetail.implementations;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

    @Bind(R.id.tvAge)
    TextView tvAge;

    @Bind(R.id.tvWeight)
    TextView tvWeight;

    @Bind(R.id.tvHeight)
    TextView tvHeight;

    @Bind(R.id.tvHairColor)
    TextView tvHairColor;

    @Bind(R.id.tvProfessions)
    TextView tvProfessions;

    @Bind(R.id.llFriends)
    LinearLayout llFriends;

    @Bind(R.id.srlPersonDetail)
    SwipeRefreshLayout srlPersonDetail;

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
    public void displayData(final Person person) {
        this.person = person;

        getSupportActionBar().setTitle(person.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Picasso.with(this).load(person.getPhoto()).into(ivPhoto);
        tvName.setText(person.getName());
        tvAge.setText(person.getAge());
        tvWeight.setText(person.getWeight());
        tvHeight.setText(person.getHeight());
        tvHairColor.setText(person.getHairColor());
        String professions = "";
        if (person.getProfessions() != null) {
            for (String profession : person.getProfessions()) {
                if (professions != "") {
                    professions += "\n";
                }
                professions += profession;
            }
        }
        tvProfessions.setText(professions);

        for (final Person friend : person.getFriends()) {
            ImageView ivFriend = new ImageView(this);
            ivFriend.setScaleType(ImageView.ScaleType.CENTER_CROP);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150, 150);
            int margin = 30;
            layoutParams.bottomMargin = margin;
            layoutParams.leftMargin = margin;
            layoutParams.rightMargin = margin;
            layoutParams.topMargin = margin;
            ivFriend.setLayoutParams(layoutParams);

            Picasso.with(this).load(friend.getPhoto()).into(ivFriend);
            ivFriend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickFriend(friend);
                }
            });
            llFriends.addView(ivFriend);
        }
    }

    @Override
    public void showLoading() {
        srlPersonDetail.setEnabled(true);
        srlPersonDetail.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        srlPersonDetail.setEnabled(false);
        srlPersonDetail.setRefreshing(false);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(PersonDetailViewImpl.this, message, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void clickFriend(Person person) {
        Intent intent = new Intent(this, PersonDetailViewImpl.class);
        intent.putExtra(Person.ID_KEY, person.getId());
        startActivity(intent);
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
