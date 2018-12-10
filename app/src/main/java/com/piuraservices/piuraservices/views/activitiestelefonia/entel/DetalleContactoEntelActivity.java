
package com.piuraservices.piuraservices.views.activitiestelefonia.entel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.piuraservices.piuraservices.R;

public class DetalleContactoEntelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contacto_entel);
        getSupportActionBar().setTitle("Detalle de contacto");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
