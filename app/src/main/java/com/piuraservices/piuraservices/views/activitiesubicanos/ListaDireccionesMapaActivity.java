package com.piuraservices.piuraservices.views.activitiesubicanos;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.TextHttpResponseHandler;
import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.UbicanosActivity;
import com.piuraservices.piuraservices.adapters.epsgrau.ListaInfoContactosEpsgrauAdapter;
import com.piuraservices.piuraservices.models.epsgrau.InfoContactosEpsgraumodel;
import com.piuraservices.piuraservices.models.epsgrau.InfoReferencialEpsgraumodel;
import com.piuraservices.piuraservices.services.epsgrau.ListaReferencialEpsclient;
import com.piuraservices.piuraservices.services.http;
import com.piuraservices.piuraservices.utils.Config;
import com.piuraservices.piuraservices.views.activitiesenosa.EnosaActivity;
import com.piuraservices.piuraservices.views.activitiesepsgrau.DetalleContactoEpsActivity;
import com.piuraservices.piuraservices.views.activitiesepsgrau.EPS_grauActivity;
import com.piuraservices.piuraservices.views.fragments.UbicanosFragment;

import org.apache.http.Header;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListaDireccionesMapaActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    ListView listaelementos;
    ArrayAdapter<String> adapter;
    RadioGroup radioentidad;
    RadioButton rbepsgrau;
    //prograso loading
    ProgressDialog progreso;
    //list view de reclamos
    ListView listViewContactos;
    //lista data del modelo de reclamos
    List<InfoContactosEpsgraumodel> list_contactos;
    //Array list for to http and to converter to gson EPSGRAU
    ArrayList<InfoContactosEpsgraumodel> lista = new ArrayList();
    public static final String nombreempresa = "epsgrau";
    //variables para mostrar datos de la central
    String direccioneps = "";
    String direccionenosa = "";
    String direccionmovistar = "";
    String direccionclaro = "";
    String direccionentel = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_direcciones_mapa);
        getSupportActionBar().setTitle("Ubica tu servicio");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        radioentidad = (RadioGroup) findViewById(R.id.radio_group_mapa);
        rbepsgrau = (RadioButton) findViewById(R.id.rb_epsgrau_mapa);
        listViewContactos = (ListView) findViewById(R.id.lista_direciones_mapa);
        //llamar al metodo elija entidad
        elijaentidad();
        //medoto lista entidades local central
        listaEntidadCentral();
        //lista infor entidades
        //listainfoEntidad();
        
    }

    //mesaje de apertura en lista entidades
    public void messageListaCentrales() {
        final AlertDialog.Builder alertaDeError2 = new AlertDialog.Builder(ListaDireccionesMapaActivity.this);
        alertaDeError2.setTitle("Readme");
        alertaDeError2.setMessage("Elija una entidad o haga " +
                "Click en la lista y ubique la central de los servicios Básicos mediante Google Maps");
        alertaDeError2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertaDeError2.create();
        alertaDeError2.show();

    }
    //mesaje recargar inter
    public void mensajeSinInternet(){
        final AlertDialog.Builder alertaDeError2 = new AlertDialog.Builder(ListaDireccionesMapaActivity.this);
        alertaDeError2.setTitle("Sin Acceso a Internet");
        alertaDeError2.setMessage("Compruebe su conexion de internet o active sus datos");
        alertaDeError2.setPositiveButton("Recargar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listaEntidadCentral();
            }
        });
        alertaDeError2.create();
        alertaDeError2.show();
       // alertaDeError2.setCancelable(false);
    }

    //metodo que comrueba el acceso a internet
    public void compruebaConexionInternet() {
        verificaConexion(getApplication());
        if (!verificaConexion(this)) {
            //Toast.makeText(getBaseContext(), "Comprueba tu conexión a Internet.", Toast.LENGTH_SHORT).show();
            mensajeSinInternet();
            //dialog();
        }
        else{
            listaEntidadCentral();
        }

    }
    //verifica conexion a internet
    public static boolean verificaConexion(Context ctx) {
        boolean bConectado = false;
        ConnectivityManager connec = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        // No sólo wifi, también GPRS
        NetworkInfo[] redes = connec.getAllNetworkInfo();
        // este bucle debería no ser tan ñapa
        for (int i = 0; i < 2; i++) {
            // ¿Tenemos conexión? ponemos a true
            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                bConectado = true;
            }
        }
        return bConectado;
    }

    //metodo para listar contactos ok
    public void listarCotactosEps() {
        dialog();
        String url = "informacion/listacontactos/1";
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
                    lista = new Gson().fromJson(responseString, new TypeToken<ArrayList<InfoContactosEpsgraumodel>>() {
                    }.getType());
                    listViewContactos.setAdapter(new ListaInfoContactosEpsgrauAdapter(getApplicationContext(), lista));
                    listViewContactos.setOnItemClickListener(ListaDireccionesMapaActivity.this);
                    listViewContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            mostrarDetalle(lista.get(i)); //call data detalle
                        }
                    });
                    progreso.hide();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });

    }

    public void mostrarDetalle(final InfoContactosEpsgraumodel tramite) {
        //capturar datos
        Bundle bundle = new Bundle();
        bundle.putSerializable("Tramite", tramite);
        bundle.putString("nombreKey", tramite.getNombreempresa().toString());
        bundle.putString("direccionKey", tramite.getDireccion().toString());
        bundle.putString("telefonoKey", tramite.getDireccion().toString());
        bundle.putString("horarioKey", tramite.getDireccion().toString());
        bundle.putString("tipoatencionKey", tramite.getDireccion().toString());
        Intent intent = new Intent(ListaDireccionesMapaActivity.this, DetalleContactoEpsActivity.class);
        Bundle parametros = new Bundle();
        String nombre = tramite.getNombreempresa().toString();
        String direccion = tramite.getDireccion().toString();
        String telefono = tramite.getTelefono().toString();
        String horario = tramite.getHorario().toString();
        String tipoatencion = tramite.getTipoatencion().toString();
        parametros.putString("nombreKey", nombre);
        parametros.putString("direccionKey", direccion);
        parametros.putString("telefonoKey", telefono);
        parametros.putString("horarioKey", horario);
        parametros.putString("tipoatencionKey", tipoatencion);
        intent.putExtras(parametros);
        startActivity(intent);
    }

    //metodo que lista las entidades centrales de los servicios
    public void listaEntidadCentral() {
        final String url = Config.URL_SERVER;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        ListaReferencialEpsclient servicio = retrofit.create(ListaReferencialEpsclient.class);
        Call<List<InfoReferencialEpsgraumodel>> call = servicio.getInfoReferencialeps();
        call.enqueue(new Callback<List<InfoReferencialEpsgraumodel>>() {
            @Override
            public void onResponse(Call<List<InfoReferencialEpsgraumodel>> call, Response<List<InfoReferencialEpsgraumodel>> response) {
                try {
                    for (InfoReferencialEpsgraumodel info : response.body()) {
                        if (info.getId() == 1) {
                            Log.e("DIRECCION", info.getDireccion() + "\nNOMBRE:" + info.getNombre());
                            direccioneps = response.body().get(0).getDireccion();
                            direccionenosa = response.body().get(1).getDireccion();
                            direccionmovistar = response.body().get(2).getDireccion();
                            direccionclaro = response.body().get(3).getDireccion();
                            direccionentel = response.body().get(4).getDireccion();
                            //mostrar cuando hay internet
                            messageListaCentrales();
                            //direccion.setText(response.body().get(1).getDireccion());
                            //Arreglo  tipo string
                            String[] entidades =
                                    {"Central Eps Grau S.A," + " " + direccioneps,
                                            "Central Enosa," + " " + direccionenosa,
                                            "Central Movistar," + " " + direccionmovistar,
                                            "Central Claro," + " " + direccionclaro,
                                            "Central Entel," + " " + direccionentel};
                            listaelementos = (ListView) findViewById(R.id.lista_direciones_mapa);
                            adapter = new ArrayAdapter<String>(ListaDireccionesMapaActivity.this, android.R.layout.simple_list_item_1, entidades);
                            listaelementos.setAdapter(adapter);
                            listaelementos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    openGoogleMapsEntidadCentral(i);
                                }
                            });
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<InfoReferencialEpsgraumodel>> call, Throwable t) {
                compruebaConexionInternet();
                //Toast.makeText(ListaDireccionesMapaActivity.this, "No tiene acceso a internet :(", Toast.LENGTH_SHORT).show();
            }

        });

    }

    public void openGoogleMapsEntidadCentral(int i) {

        switch (i) {
            case 0:
                Uri urisps = Uri.parse("google.navigation:q=EPS Grau S.A,+Piura");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, urisps);
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
                Uri usrimovistar = Uri.parse("google.navigation:q=Movistar+Piura");
                Intent mapIntent3 = new Intent(Intent.ACTION_VIEW, usrimovistar);
                mapIntent3.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent3);
                break;
            case 3:
                Uri uriclaro = Uri.parse("google.navigation:q=Claro+Piura");
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
                Toast.makeText(getApplicationContext(), "Select Option", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    public void elijaentidad() {
        radioentidad.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_epsgrau_mapa:
                        Intent intent = new Intent(ListaDireccionesMapaActivity.this, UbicanosActivity.class);
                        Bundle parametros = new Bundle();
                        // String entidad = rbepsgrau.getText().toString();
                        String entidad = "epsgrau";
                        parametros.putString("entidadKey", entidad);
                        intent.putExtras(parametros);
                        startActivity(intent);
                        break;

                    case R.id.rb_enosa_mapa:
                        Intent intentenosa = new Intent(ListaDireccionesMapaActivity.this, UbicanosActivity.class);
                        Bundle parametrosenosa = new Bundle();
                        String entidadenosa = "enosa";
                        parametrosenosa.putString("entidadKey", entidadenosa);
                        intentenosa.putExtras(parametrosenosa);
                        startActivity(intentenosa);
                        break;

                    case R.id.rb_telefonia_mapa:
                        Intent intent1 = new Intent(ListaDireccionesMapaActivity.this, ListaEmpresasTelefoniaActivity.class);
                        startActivity(intent1);
                        break;
                    default:

                }

            }
        });
    }
    //mesaje de dialog
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
        SearchView searchView = (SearchView) item.getActionView();
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
