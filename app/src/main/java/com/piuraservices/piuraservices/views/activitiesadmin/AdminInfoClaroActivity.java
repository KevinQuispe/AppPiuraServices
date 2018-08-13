package com.piuraservices.piuraservices.views.activitiesadmin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.piuraservices.piuraservices.R;

public class AdminInfoClaroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_info_claro);

        getSupportActionBar().setTitle("Admin Información Claro");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
