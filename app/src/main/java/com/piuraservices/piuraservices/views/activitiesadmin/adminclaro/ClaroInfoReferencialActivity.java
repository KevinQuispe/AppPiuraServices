package com.piuraservices.piuraservices.views.activitiesadmin.adminclaro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.piuraservices.piuraservices.R;

public class ClaroInfoReferencialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claro_info_referencial);
        getSupportActionBar().setTitle("Información Referencial");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
