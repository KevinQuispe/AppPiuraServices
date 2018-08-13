package com.piuraservices.piuraservices.views.activitiesadmin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.piuraservices.piuraservices.R;

import java.util.zip.Inflater;

public class AdministrarInformacionActivity extends AppCompatActivity  implements AdapterView.OnItemClickListener{

    ListView listaelementos;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrar_informacion);
        getSupportActionBar().setTitle("Administrar Información");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String[] informacion = {"Información de consulta EPS GRAU SA", "Información de consulta ENOSA", "Información de consulta Movistar","Información de consulta Claro","Información de consulta Entel"};
        Inflater inflater;
        listaelementos=(ListView) findViewById(R.id.list_admininformacion);
        adapter = new ArrayAdapter<String>(AdministrarInformacionActivity.this, android.R.layout.simple_list_item_1, informacion);
        listaelementos.setAdapter(adapter);
        listaelementos.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "item" + i, Toast.LENGTH_SHORT).show();
    }
}
