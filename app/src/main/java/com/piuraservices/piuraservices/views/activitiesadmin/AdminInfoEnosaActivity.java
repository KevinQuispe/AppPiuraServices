package com.piuraservices.piuraservices.views.activitiesadmin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.piuraservices.piuraservices.R;

public class AdminInfoEnosaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_info_enosa);
        getSupportActionBar().setTitle("Admin Información Enosa");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
