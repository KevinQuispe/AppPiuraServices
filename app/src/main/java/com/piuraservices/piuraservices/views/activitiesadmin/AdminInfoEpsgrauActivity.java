package com.piuraservices.piuraservices.views.activitiesadmin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.piuraservices.piuraservices.R;

public class AdminInfoEpsgrauActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_info_epsgrau);
        getSupportActionBar().setTitle("Admin Información Epsgrau");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
