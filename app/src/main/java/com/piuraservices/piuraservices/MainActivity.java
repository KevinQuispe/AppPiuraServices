package com.piuraservices.piuraservices;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.piuraservices.piuraservices.views.activities.AcercadeActivity;
import com.piuraservices.piuraservices.views.activitiesubicanos.ListaDireccionesMapaActivity;
import com.piuraservices.piuraservices.views.fragments.EntidadContactoFragment;
import com.piuraservices.piuraservices.views.fragments.EntidadReclamoFragment;
import com.piuraservices.piuraservices.views.fragments.EntidadTramiteFragment;
import com.piuraservices.piuraservices.views.fragments.EntidadesFragment;
import com.piuraservices.piuraservices.views.fragments.HomeFragment;
import com.piuraservices.piuraservices.views.fragments.UbicanosFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //navigation buton
    private TextView mTextMessage;
    //get location en google maps
    private FusedLocationProviderClient mFusedLocationClient;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    HomeFragment form1 = new HomeFragment();
                    android.support.v4.app.FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                    transaction1.replace(R.id.contenedorFragement, form1);
                    transaction1.addToBackStack(null).commit();
                    setTitle("Piura Services");
                    return true;

                case R.id.navigation_entidades:
                    EntidadesFragment form = new EntidadesFragment();
                    android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.contenedorFragement, form);
                    transaction.addToBackStack(null).commit();
                    setTitle("Entidades");
                    return true;
                case R.id.navigation_ubicanos:
                    //UbicanosFragment ubicanos = new UbicanosFragment();
                    //android.support.v4.app.FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
                    //transaction2.replace(R.id.contenedorFragement, ubicanos);
                    //transaction2.addToBackStack(null).commit();
                    //setTitle("Ubicanos");
                    Intent intentmap= new Intent(MainActivity.this, ListaDireccionesMapaActivity.class);
                    startActivity(intentmap);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //clase  navigation
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //call latitud y longitud
        //obtenerLongitudLatitud();

    }

    private void obtenerLongitudLatitud() {
        //get location en google maps
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            Log.e("latitud" + location.getLatitude(), "longitud" + location.getLongitude());
                        }
                    }
                });
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_acercade ) {
            Intent intent=new Intent(getApplicationContext(), AcercadeActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_salir) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            HomeFragment form = new HomeFragment();
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.contenedorFragement, form);
            transaction.addToBackStack(null).commit();
            setTitle("Piura Services");

        } else if (id == R.id.nav_entidades) {
            EntidadesFragment form = new EntidadesFragment();
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.contenedorFragement, form);
            transaction.addToBackStack(null).commit();
            setTitle("Entidades");

        } else if (id == R.id.nav_tramites) {
            EntidadTramiteFragment tramites = new EntidadTramiteFragment();
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.contenedorFragement, tramites);
            transaction.addToBackStack(null).commit();
            setTitle("Elija Entidad o Empresa");

        } else if (id == R.id.nav_reclamos) {
            EntidadReclamoFragment reclamos = new EntidadReclamoFragment();
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.contenedorFragement, reclamos);
            transaction.addToBackStack(null).commit();
            setTitle("Elija Entidad o Empresa");

        } else if (id == R.id.nav_contactos) {
            //Intent intent=new Intent(MainActivity.this, ContactosActivity.class);
            //startActivity(intent);
            EntidadContactoFragment contactos = new EntidadContactoFragment();
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.contenedorFragement, contactos);
            transaction.addToBackStack(null).commit();
            setTitle("Elija Entidad o Empresa");

        } else if (id == R.id.nav_ubicanos) {
            Intent intent=new Intent(MainActivity.this, ListaDireccionesMapaActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_acercade) {
            Intent intent=new Intent(MainActivity.this, AcercadeActivity.class);
            startActivity(intent);
            return true;

        } else if (id == R.id.nav_compartir) {
            Intent intent= new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            String subject="Compartir";
            String body="Descarga la aplicación PIURA SERVICES para consultar información de los servicios básicos en la ciudad de Piura, descargala desde la google play: https://play.google.com/store/apps/detaills?id=com.piuraservices.piuraservices";
            //String body="Descarga la aplicación PIURA SERVICES para consultar información de los servicios básicos en la ciudad de Piura, descargala desde la google play: https://play.google.com/store/apps/";
            intent.putExtra(Intent.EXTRA_SUBJECT,subject);
            intent.putExtra(Intent.EXTRA_TEXT,body);
            startActivity(Intent.createChooser(intent,"Compartir en"));
        } else if (id == R.id.nav_salir) {
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
        }
        public  void playStore(){
            try {
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                        Uri.parse("https://play.google.com/store/apps/"));
                startActivity(viewIntent);

            }catch(Exception e) {
                Toast.makeText(getApplicationContext(),"Unable to Connect Try Again...",
                        Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }

}
