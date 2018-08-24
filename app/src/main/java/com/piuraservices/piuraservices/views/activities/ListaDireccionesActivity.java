package com.piuraservices.piuraservices.views.activities;

import android.content.Intent;
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

import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.UbicanosActivity;
import com.piuraservices.piuraservices.views.activitiesenosa.InfoTramitesEnosaActivity;

import java.util.zip.Inflater;

public class ListaDireccionesActivity extends AppCompatActivity {
    ListView listaelementos;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_direcciones);
        getSupportActionBar().setTitle("Lista de Direcciones");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String[] informacion =
                {"Movistar,Calle Libertad #698 Piura",
                "Movistar,Esquina Av. Andrés Avelino Cáceres 147  Local Lc:117 – C.C. Open Plaza Piura",
                "Movistar,Calle Tacna #432 Piura",
                "Movistar,Av. Sanchez Cerro (Antesjr.H) #234-239 Piso 1 - Tda Lc-T2/Ls-05 Cc Real Plaza Piura",
                "Movistar,Av. Loreto N° 259 Piura",
                        "Movistar,Edificio Lucy #101-102 Centro Civico Talara"};
        Inflater inflater;
        listaelementos=(ListView) findViewById(R.id.lista_direciones);
        adapter = new ArrayAdapter<String>(ListaDireccionesActivity.this, android.R.layout.simple_list_item_1, informacion);
        listaelementos.setAdapter(adapter);
        listaelementos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(ListaDireccionesActivity.this, UbicanosActivity.class);
                startActivity(intent);
            }
        });
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
