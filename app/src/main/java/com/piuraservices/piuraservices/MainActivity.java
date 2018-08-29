package com.piuraservices.piuraservices;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.piuraservices.piuraservices.views.activities.ConfigurarCuentaActivity;
import com.piuraservices.piuraservices.views.activities.ContactosActivity;
import com.piuraservices.piuraservices.views.activities.ListaDireccionesActivity;
import com.piuraservices.piuraservices.views.activitiesadmin.AdministrarInformacionActivity;
import com.piuraservices.piuraservices.views.fragments.EntidadesFragment;
import com.piuraservices.piuraservices.views.fragments.HomeFragment;
import com.piuraservices.piuraservices.views.fragments.UbicanosFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //navigation buton
    private TextView mTextMessage;
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
                    UbicanosFragment ubicanos = new UbicanosFragment();
                    android.support.v4.app.FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
                    transaction2.replace(R.id.contenedorFragement, ubicanos);
                    transaction2.addToBackStack(null).commit();
                    setTitle("Ubicanos");
                    //Intent intent= new Intent(MainActivity.this, ListaDireccionesActivity.class);
                    //startActivity(intent);
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

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

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
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent=new Intent(MainActivity.this, ConfigurarCuentaActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_admininfo) {
            Intent intent=new Intent(MainActivity.this, AdministrarInformacionActivity.class);
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

        } else if (id == R.id.nav_reclamos) {

        } else if (id == R.id.nav_contactos) {
            Intent intent=new Intent(MainActivity.this, ContactosActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_ubicanos) {
            UbicanosFragment ubicanos = new UbicanosFragment();
            android.support.v4.app.FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
            transaction2.replace(R.id.contenedorFragement, ubicanos);
            transaction2.addToBackStack(null).commit();
            setTitle("Ubicanos");

        } else if (id == R.id.nav_config) {
            Intent intent=new Intent(MainActivity.this, ConfigurarCuentaActivity.class);
            startActivity(intent);
            return true;

        } else if (id == R.id.nav_compartir) {
            Intent intent= new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            String subject="Compartir";
            String body="Descarga esta aplicación comunicate con: https://twitter.com/kquisperojas";
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
}