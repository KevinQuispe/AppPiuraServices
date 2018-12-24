package com.piuraservices.piuraservices.views.activitiesubicanos;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.UbicanosActivity;
import com.piuraservices.piuraservices.views.activities.ListaDireccionesActivity;

import java.util.zip.Inflater;

public class ListaDireccionesMapaActivity extends AppCompatActivity {

    ListView listaelementos;
    ArrayAdapter<String> adapter;
    RadioGroup radioentidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_direcciones_mapa);
        getSupportActionBar().setTitle("Ubica tu servicio");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        radioentidad=(RadioGroup) findViewById(R.id.radio_group_maps);
        Toast.makeText(getApplicationContext(),"Elija entidad",Toast.LENGTH_LONG).show();
        //call entidad
        elijaentidad();

    }
    public void showalldirections(){

    }
    public void elijaentidad(){
        radioentidad.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                if (checkedId==R.id.rb_eps_grau_maps){
                    String[] epsgrau={ "Eps Grau,Av. Sanchez Cerro (Antesjr.H) #234-239 Piso 1 - Tda Lc-T2/Ls-05 Cc Real Plaza Piura",
                            "Eps Grau,Av. Loreto N° 259 Piura",
                            "Eps Grau,Edificio Lucy #101-102 Centro Civico Talara"};
                    listaelementos=(ListView) findViewById(R.id.lista_direciones_mapa);
                    adapter = new ArrayAdapter<String>(ListaDireccionesMapaActivity.this, android.R.layout.simple_list_item_1, epsgrau);
                    listaelementos.setAdapter(adapter);
                    listaelementos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent=new Intent(ListaDireccionesMapaActivity.this, UbicanosActivity.class);
                            startActivity(intent);
                        }
                    });

                }
                if (checkedId==R.id.rb_enosa_maps){
                    String[] enosa =
                            {"Enosa,Calle Libertad #698 Piura",
                                    "Enosa,Esquina Av. Andrés Avelino Cáceres 147  Local Lc:117 – C.C. Open Plaza Piura",
                                    "Movistar,Calle Tacna #432 Piura"};

                    listaelementos=(ListView) findViewById(R.id.lista_direciones_mapa);
                    adapter = new ArrayAdapter<String>(ListaDireccionesMapaActivity.this, android.R.layout.simple_list_item_1, enosa);
                    listaelementos.setAdapter(adapter);
                    listaelementos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent=new Intent(ListaDireccionesMapaActivity.this, UbicanosActivity.class);
                            startActivity(intent);
                        }
                    });
                }
                else if (checkedId==R.id.rb_telefonia_maps){
                    String[] telefonia =
                            {"Movistar,Calle Libertad #698 Piura",
                                    "Movistar,Esquina Av. Andrés Avelino Cáceres 147  Local Lc:117 – C.C. Open Plaza Piura",
                                    "Movistar,Calle Tacna #432 Piura"};

                    listaelementos=(ListView) findViewById(R.id.lista_direciones_mapa);
                    adapter = new ArrayAdapter<String>(ListaDireccionesMapaActivity.this, android.R.layout.simple_list_item_1, telefonia);
                    listaelementos.setAdapter(adapter);
                    listaelementos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent=new Intent(ListaDireccionesMapaActivity.this, UbicanosActivity.class);
                            startActivity(intent);
                        }
                    });
                }
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
