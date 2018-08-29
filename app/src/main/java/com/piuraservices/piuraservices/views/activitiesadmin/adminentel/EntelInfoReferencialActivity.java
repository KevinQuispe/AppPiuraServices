package com.piuraservices.piuraservices.views.activitiesadmin.adminentel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.piuraservices.piuraservices.R;

public class EntelInfoReferencialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entel_info_referencial);
        getSupportActionBar().setTitle("Información Referencial");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
