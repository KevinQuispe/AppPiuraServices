package com.piuraservices.piuraservices.views.activitiesadmin.adminentel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.piuraservices.piuraservices.R;

public class AdminInfoEntelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_info_entel);
        getSupportActionBar().setTitle("Admin Información Entel");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
