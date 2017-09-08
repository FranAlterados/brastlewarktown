package com.fduranortega.brastlewarktown.persondetail.implementations;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fduranortega.brastlewarktown.R;
import com.fduranortega.brastlewarktown.persondetail.interfaces.PersonDetailView;

public class PersonDetailViewImpl extends AppCompatActivity implements PersonDetailView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_detail);
    }
}
