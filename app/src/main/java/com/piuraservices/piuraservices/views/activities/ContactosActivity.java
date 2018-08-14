package com.piuraservices.piuraservices.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.piuraservices.piuraservices.R;

public class ContactosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);
        getSupportActionBar().setTitle("Contactos telefónicos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
