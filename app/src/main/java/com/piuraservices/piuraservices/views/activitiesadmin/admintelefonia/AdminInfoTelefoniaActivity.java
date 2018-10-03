package com.piuraservices.piuraservices.views.activitiesadmin.admintelefonia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.piuraservices.piuraservices.R;
import java.util.zip.Inflater;

public class AdminInfoTelefoniaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView listaelementos;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_info_telefonia);
        getSupportActionBar().setTitle("Admin Información Telefonia");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String[] informacion = {"Información Referencial", "Información de Trámites", "Información de Reclamos"};
        Inflater inflater;
        listaelementos = (ListView) findViewById(R.id.id_listadmininfotelefonia);
        adapter = new ArrayAdapter<String>(AdminInfoTelefoniaActivity.this, android.R.layout.simple_list_item_1, informacion);
        listaelementos.setAdapter(adapter);
        listaelementos.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        if (i == 0) {
            Intent epsgrau = new Intent(this, TelefoniaInfoReferencialActivity.class);
            startActivity(epsgrau);
            //Toast.makeText(this, "item" + i, Toast.LENGTH_SHORT).show();
        }
        if (i == 1) {
            Intent enosa = new Intent(this, TelefoniaInfoTramitesActivity.class);
            startActivity(enosa);
        }
        if (i == 2) {
            Intent telefonia = new Intent(this, TelefoniaInfoReclamosActivity.class);
            startActivity(telefonia);
        }
    }
}