package com.piuraservices.piuraservices.views.activitiesadmin.adminepsgrau;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.piuraservices.piuraservices.R;

import java.util.zip.Inflater;

public class AdminInfoEpsgrauActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listaelementos;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_info_epsgrau);
        getSupportActionBar().setTitle("Admin Información EPS Grau");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String[] informacion = {"Información Referencial", "Información de Trámites", "Información de Reclamos"};
        Inflater inflater;
        listaelementos = (ListView) findViewById(R.id.id_listadmininfoepsgrau);
        adapter = new ArrayAdapter<String>(AdminInfoEpsgrauActivity.this, android.R.layout.simple_list_item_1, informacion);
        listaelementos.setAdapter(adapter);
        listaelementos.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        if (i == 0) {
            Intent epsgrau = new Intent(this, EpsInfoReferencialActivity.class);
            startActivity(epsgrau);
            //Toast.makeText(this, "item" + i, Toast.LENGTH_SHORT).show();
        }
        if (i == 1) {
            Intent enosa = new Intent(this, EpsInfoTramitesActivity.class);
            startActivity(enosa);
        }
        if (i == 2) {
            Intent telefonia = new Intent(this, EpsInfoReclamosActivity.class);
            startActivity(telefonia);
        }
    }
}
