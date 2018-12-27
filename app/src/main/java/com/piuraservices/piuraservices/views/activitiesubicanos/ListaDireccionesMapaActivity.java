package com.piuraservices.piuraservices.views.activitiesubicanos;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.TextHttpResponseHandler;
import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.UbicanosActivity;
import com.piuraservices.piuraservices.adapters.epsgrau.ListaInfoContactosEpsgrauAdapter;
import com.piuraservices.piuraservices.models.enosa.InfoContactosEnosamodel;
import com.piuraservices.piuraservices.models.epsgrau.InfoContactosEpsgraumodel;
import com.piuraservices.piuraservices.models.telefonia.movistar.InfoContactosMovistarmodel;
import com.piuraservices.piuraservices.services.http;
import com.piuraservices.piuraservices.views.activities.ListaDireccionesActivity;
import com.piuraservices.piuraservices.views.activitiesepsgrau.DetalleContactoEpsActivity;
import com.piuraservices.piuraservices.views.activitiesepsgrau.InfoContactosEpsgrauActivity;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class ListaDireccionesMapaActivity extends AppCompatActivity implements  View.OnClickListener,AdapterView.OnItemClickListener {
    ListView listaelementos;
    ArrayAdapter<String> adapter;
    RadioGroup radioentidad;
    //prograso loading
    ProgressDialog progreso;
    //list view de reclamos
    ListView listViewContactos;
    //lista data del modelo de reclamos
    List<InfoContactosEpsgraumodel> list_contactos;
    //Array list for to http and to converter to gson EPSGRAU
    ArrayList<InfoContactosEpsgraumodel> lista = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_direcciones_mapa);
        getSupportActionBar().setTitle("Ubica tu servicio");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        radioentidad=(RadioGroup) findViewById(R.id.radio_group_maps);
        listViewContactos = (ListView) findViewById(R.id.lista_direciones_mapa);
        listViewContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int pos = i;
                Intent intent=new Intent(ListaDireccionesMapaActivity.this, DetalleContactoEpsActivity.class);
                startActivity(intent);
                mostrarDetalle(list_contactos.get(pos));
            }
        });
        listaEntidadCentral();
        //llamar al metodo elija entidad
        elijaentidad();
    }
    public void listarCotactosEps()
    {
        dialog();
        String url="informacion/listacontactos/1";
        http.get(getApplicationContext(), url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                System.out.println(responseString);
                Toast.makeText(getApplicationContext(), "Error de Conexion", Toast.LENGTH_SHORT).show();
                progreso.hide();
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                System.out.println(responseString);
                try {
                    //Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                    lista=new Gson().fromJson(responseString,new TypeToken<ArrayList<InfoContactosEpsgraumodel>>(){}.getType());
                    listViewContactos.setAdapter(new ListaInfoContactosEpsgrauAdapter(getApplicationContext(),lista));
                    listViewContactos.setOnItemClickListener(ListaDireccionesMapaActivity.this);
                    listViewContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            mostrarDetalle(lista.get(i)); //call data detalle
                        }
                    });
                    progreso.hide();
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        });

    }
    public void mostrarDetalle(final InfoContactosEpsgraumodel tramite){
        //capturar datos
        Bundle bundle=new Bundle();
        bundle.putSerializable("Tramite",tramite);
        bundle.putString("nombreKey",tramite.getNombreempresa().toString());
        bundle.putString("direccionKey",tramite.getDireccion().toString());
        bundle.putString("telefonoKey",tramite.getDireccion().toString());
        bundle.putString("horarioKey",tramite.getDireccion().toString());
        bundle.putString("tipoatencionKey",tramite.getDireccion().toString());
        Intent intent=new Intent(ListaDireccionesMapaActivity.this, DetalleContactoEpsActivity.class);
        Bundle parametros = new Bundle();
        String nombre = tramite.getNombreempresa().toString();
        String direccion = tramite.getDireccion().toString();
        String telefono = tramite.getTelefono().toString();
        String horario = tramite.getHorario().toString();
        String tipoatencion = tramite.getTipoatencion().toString();
        parametros.putString("nombreKey",nombre);
        parametros.putString("direccionKey",direccion);
        parametros.putString("telefonoKey",telefono);
        parametros.putString("horarioKey",horario);
        parametros.putString("tipoatencionKey",tipoatencion);
        intent.putExtras(parametros);
        startActivity(intent);
    }

    public void listaEntidadCentral(){
        String[] enosa = {"Central Eps Grau,Calle Libertad #698 Piura",
                        "Central Enosa,Esquina Av. Andrés Avelino Cáceres 147",
                        "Central Movistar,Calle Tacna #432 Piura",
                        "Central Claro,Calle Tacna #432 Piura",
                        "Central Entel,Calle Tacna #432 Piura"};
        listaelementos=(ListView) findViewById(R.id.lista_direciones_mapa);
        adapter = new ArrayAdapter<String>(ListaDireccionesMapaActivity.this, android.R.layout.simple_list_item_1, enosa);
        listaelementos.setAdapter(adapter);
        listaelementos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                openGogleMapsEntidadCentral(i);
            }
        });
    }
    public void openGogleMapsEntidadCentral(int i){

            switch (i) {
                    case 0:
                    Uri usisps = Uri.parse("google.navigation:q=EPS Grau S.A,+Piura");
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, usisps);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                    break;
                    case 1:
                    Uri urienosa = Uri.parse("google.navigation:q=Enosa+Piura");
                    Intent mapIntent2 = new Intent(Intent.ACTION_VIEW, urienosa);
                    mapIntent2.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent2);
                    break;
                    case 2:
                    Uri usrimovistar = Uri.parse("google.navigation:q=Claro+Piura");
                    Intent mapIntent3 = new Intent(Intent.ACTION_VIEW, usrimovistar);
                    mapIntent3.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent3);
                    break;
                    case 3:
                    Uri uriclaro= Uri.parse("google.navigation:q=Claro+Piura");
                    Intent mapIntent4 = new Intent(Intent.ACTION_VIEW, uriclaro);
                    mapIntent4.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent4);
                    break;
                    case 4:
                    Uri urentel = Uri.parse("google.navigation:q=Entel+Piura");
                    Intent mapIntent5 = new Intent(Intent.ACTION_VIEW, urentel);
                    mapIntent5.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent5);
                    break;
                    default:
                        Toast.makeText(getApplicationContext(),"Select Option",Toast.LENGTH_SHORT).show();
            }

        }
    public void elijaentidad(){
        radioentidad.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                switch (checkedId){
                    case R.id.rb_eps_grau:
                        listarCotactosEps();
                        break;
                    case R.id.rb_enosa_maps:
                        break;

                    case R.id.rb_telefonia_maps:
                        Intent intent=new Intent(ListaDireccionesMapaActivity.this, DetalleContactoEpsActivity.class);
                        startActivity(intent);
                        break;
                    default:

                }

            }
        });
    }
    public void dialog() {
        //progreso = new ProgressDialog(EpsInfoReclamosActivity.this, ProgressDialog.THEME_HOLO_LIGHT);
        progreso = new ProgressDialog(ListaDireccionesMapaActivity.this, ProgressDialog.BUTTON_POSITIVE);
        // set indeterminate style
        progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // set title and message
        progreso.setTitle("Procesando");
        progreso.setMessage("Loading...");
        // and show it
        progreso.show();
        progreso.setCancelable(false);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

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
