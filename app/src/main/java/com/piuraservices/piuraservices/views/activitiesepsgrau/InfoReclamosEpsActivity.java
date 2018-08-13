package com.piuraservices.piuraservices.views.activitiesepsgrau;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.piuraservices.piuraservices.R;

import java.util.zip.Inflater;

public class InfoReclamosEpsActivity extends AppCompatActivity {

    ListView listaelementos;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_reclamos_eps);
        getSupportActionBar().setTitle("Información de Reclamos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String[] informacion = {"Información Referencial", "Información de Reclamos", "Información de Trámites"};
        Inflater inflater;
        listaelementos=(ListView) findViewById(R.id.list_reclamoseps);
        adapter = new ArrayAdapter<String>(InfoReclamosEpsActivity.this, android.R.layout.simple_list_item_1, informacion);
        listaelementos.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

}
