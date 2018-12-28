package com.piuraservices.piuraservices;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.piuraservices.piuraservices.models.enosa.EntidadEnosa;
import com.piuraservices.piuraservices.services.geolocation.Claro;
import com.piuraservices.piuraservices.services.geolocation.Entel;
import com.piuraservices.piuraservices.services.geolocation.MapsPojo;
import com.piuraservices.piuraservices.services.geolocation.Movistar;
import com.piuraservices.piuraservices.utils.FirebaseReferences;
import com.piuraservices.piuraservices.views.activitiesubicanos.ListaDireccionesMapaActivity;
import com.piuraservices.piuraservices.views.fragments.EntidadesFragment;
import com.piuraservices.piuraservices.views.fragments.HomeFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class UbicanosActivity extends FragmentActivity implements View.OnClickListener, OnMapReadyCallback,NavigationView.OnNavigationItemSelectedListener {

    private GoogleMap mMap;
    private Marker marcador;
    double lat = 0.0;
    double lng = 0.0;
    String mensaje1;
    String direccion = " ";
    String pais = " ";
    //get location en google maps
    private FusedLocationProviderClient mFusedLocationClient;
    //variables para subir latitud y longitud a firebase
    private DatabaseReference mdatabase;
    private ArrayList<Marker> tmprealtimemarkers = new ArrayList<>();
    private ArrayList<Marker> realtimemarkers = new ArrayList<>();
    //navigation buton
    private TextView mTextMessage;
    ImageView imgubicame;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent home = new Intent(UbicanosActivity.this, MainActivity.class);
                    startActivity(home);
                    return true;
                case R.id.navigation_entidades:
                    Intent listadirecciones = new Intent(UbicanosActivity.this, ListaDireccionesMapaActivity.class);
                    startActivity(listadirecciones);
                    return true;
                case R.id.navigation_ubicanos:
                    miUbicacion();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicanos);
        //inicializar la variable de base de datos firebase
        mdatabase = FirebaseDatabase.getInstance().getReference();
        //vefificar si los servicios de gogle maps estan activos
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
        if (status == ConnectionResult.SUCCESS) {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);

        } else {
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, (Activity) getApplicationContext(), 10);
            dialog.show();
        }
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //llamar al metodo obtener lat y longitud
        //obtenerLatitudLongitud();
        //obtner puntos para movistar puede ser aqui o en onmapsready
        //subirpuntosClaro();

        //clase  navigation
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigationmapa);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        imgubicame = (ImageView) findViewById(R.id.img_ubicame);
        imgubicame.setOnClickListener(this);
        //mi ubicacion
        miUbicacion();
        //llamar a metodo elije entiddad
        recivedatosEntidad();

    }

    //revive datos de entidad y pasarle el nombre al metodo elije entidad
    public void recivedatosEntidad() {
        Intent thisForm = getIntent();
        Bundle parametros = thisForm.getExtras();
        String entidad = parametros.getString("entidadKey");

        if (entidad.equals("movistar")) {
            Toast.makeText(getApplicationContext(), entidad, Toast.LENGTH_SHORT).show();
            elijebaseentidad(entidad);

            if (entidad.equals("epsgrau")) {

            }
            if (entidad.equals("movistar")) {

            }
        }
    }

    public void datosfirebase() {
        //variables para base de datos
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myref = database.getReference(FirebaseReferences.ENTIDADES_REFERENCES); //coje la referencia
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String nombre = dataSnapshot.getValue().toString();
                Log.e("Mi dato firebase es:", nombre);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Error", databaseError.getMessage().toString());
            }
        });
    }

    //activar los servicios del gps cuando esten apagados
    public void locationStart() {
        LocationManager mlocManager = (LocationManager) getApplication().getSystemService(Context.LOCATION_SERVICE);
        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
        }
    }

    //metodo con firebase logitud y latitud
    private void obtenerLatitudLongitud() {
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
                            Log.e("LATITUD:" + location.getLatitude(), "LONGITUD   " + location.getLongitude());
                            Map<String, Object> latlong = new HashMap<>();
                            MapsPojo m = new MapsPojo();
                            latlong.put("latitud", location.getLatitude());
                            latlong.put("longitud", location.getLongitude());
                            latlong.put("nombre", m.getNombre());
                            //mdatabase.child("places").push().setValue(latlong);
                        }
                    }
                });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //subir data a base de datos entel
        //subirpuntosEntel();
        recivedatosEntidad();
    }

    public void elijebaseentidad(final String nombre) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance(); //mi base de datos
        final DatabaseReference myref = database.getReference(FirebaseReferences.ENTIDADES_REFERENCES); //coje la referencia
        final DatabaseReference myrefmovistar = database.getReference(FirebaseReferences.MOVISTAR_REFERENCES); //coje la referencia
        final DatabaseReference myrefclaro = database.getReference(FirebaseReferences.CLARO_REFERENCES); //coje la referencia
        final DatabaseReference myrefentel = database.getReference(FirebaseReferences.ENTEL_REFERENCES); //coje la referencia
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //capturar las referencias de las base de datos
                String entidades = myref.getKey().toString();
                String movistar = myrefmovistar.getKey().toString();
                String claro = myrefclaro.getKey().toString();
                String entel = myrefentel.getKey().toString();

                if (myrefmovistar.getKey().toString().equals(nombre)) {
                    marcadorPuntosMovistar();
                }
                if (myrefclaro.getKey().toString().equals(nombre)) {
                    marcadorPuntosClaro();
                }
                if (myrefentel.getKey().toString().equals(nombre)) {
                    marcadorPuntosEntel();
                } else {
                    Toast.makeText(getApplicationContext(), "GoogleMaps", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //obtener datos
    public void obtenerdatosdirebase() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myref = database.getReference(FirebaseReferences.ENTIDADES_REFERENCES);
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Movistar mp = snapshot.getValue(Movistar.class);
                    String data = snapshot.getValue().toString();
                    Log.e("MI DATO CHILDREN MOVI", data);
                    //DatabaseReference objRef = myref.child( dataSnapshot.getChildren().toString());
                    //Map<String, Object> taskMap = new HashMap<String, Object>();
                    //taskMap.put("is_read", "1");
                    //objRef.updateChildren(taskMap); //should I use setValue()...?
                    //Log.v("Testing", "" + snapshot.getKey()); //displays
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Error", databaseError.getMessage());
            }
        });
    }

    //marcador de puntos para la empresa movistar
    public void subirpuntosMovistar() {
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
                            Log.e("LATITUD:" + location.getLatitude(), "LONGITUD   " + location.getLongitude());
                            Map<String, Object> latlong = new HashMap<>();
                            Movistar m = new Movistar();
                            latlong.put("latitud", location.getLatitude());
                            latlong.put("longitud", location.getLongitude());
                            latlong.put("nombre", m.getNombre());
                            latlong.put("direccion", m.getDireccion());
                            mdatabase.child("movistar").push().setValue(latlong);
                        } else {
                            Toast.makeText(getApplicationContext(), "onMarkerss", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

    //marcador de puntos para la empresa movistar
    public void subirpuntosClaro() {
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
                            Log.e("LATITUD:" + location.getLatitude(), "LONGITUD   " + location.getLongitude());
                            Map<String, Object> latlong = new HashMap<>();
                            Claro m = new Claro();
                            latlong.put("latitud", location.getLatitude());
                            latlong.put("longitud", location.getLongitude());
                            latlong.put("nombre", m.getNombre());
                            latlong.put("direccion", m.getDireccion());
                            mdatabase.child("claro").push().setValue(latlong);
                        } else {
                            Toast.makeText(getApplicationContext(), "onMarkers", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

    //marcador de puntos para la empresa entel
    public void subirpuntosEntel() {
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
                            Log.e("LATITUD:" + location.getLatitude(), "LONGITUD   " + location.getLongitude());
                            Map<String, Object> latlong = new HashMap<>();
                            Claro m = new Claro();
                            latlong.put("latitud", location.getLatitude());
                            latlong.put("longitud", location.getLongitude());
                            latlong.put("nombre", m.getNombre());
                            latlong.put("direccion", m.getDireccion());
                            mdatabase.child("entel").push().setValue(latlong);
                        } else {
                            Toast.makeText(getApplicationContext(), "onMarkers", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

    //marcador de puntos para la empresa movistar
    public void marcadorPuntosMovistar() {
        mdatabase.child("movistar").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (Marker marker : realtimemarkers) {
                    marker.remove();
                }
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Movistar mp = snapshot.getValue(Movistar.class);
                    Double latitud = mp.getLatitud();
                    Double longitud = mp.getLongitud();
                    MarkerOptions meMarkerOptions = new MarkerOptions();
                    meMarkerOptions.position(new LatLng(latitud, longitud)).title(mp.getNombre().toString());
                    tmprealtimemarkers.add(mMap.addMarker(meMarkerOptions)); //agrego los marcadores de la base firebase
                    AgregarMarcador(latitud, longitud);
                }
                realtimemarkers.clear();
                realtimemarkers.addAll(tmprealtimemarkers);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Error", databaseError.getMessage());
            }
        });

    }

    //marcador de puntos para la empresa claro
    public void marcadorPuntosClaro() {
        mdatabase.child("claro").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (Marker marker : realtimemarkers) {
                    marker.remove();
                }
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Claro mp = snapshot.getValue(Claro.class);
                    Double latitud = mp.getLatitud();
                    Double longitud = mp.getLongitud();
                    MarkerOptions meMarkerOptions = new MarkerOptions();
                    meMarkerOptions.position(new LatLng(latitud, longitud)).title(mp.getNombre().toString());
                    tmprealtimemarkers.add(mMap.addMarker(meMarkerOptions)); //agrego los marcadores de la base firebase
                    AgregarMarcador(latitud, longitud);
                }
                realtimemarkers.clear();
                realtimemarkers.addAll(tmprealtimemarkers);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Error", databaseError.getMessage());
            }
        });
    }

    public void marcadorPuntosEntel() {
        mdatabase.child("entel").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (Marker marker : realtimemarkers) {
                    marker.remove();
                }
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Entel mp = snapshot.getValue(Entel.class);
                    Double latitud = mp.getLatitud();
                    Double longitud = mp.getLongitud();
                    MarkerOptions meMarkerOptions = new MarkerOptions();
                    meMarkerOptions.position(new LatLng(latitud, longitud)).title(mp.getNombre().toString());
                    tmprealtimemarkers.add(mMap.addMarker(meMarkerOptions)); //agrego los marcadores de la base firebase
                    AgregarMarcador(latitud, longitud);

                }
                realtimemarkers.clear();
                realtimemarkers.addAll(tmprealtimemarkers);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Error", databaseError.getMessage());
            }
        });
    }

    //MI UBICACION GOOGLE MAPS
    private static int PETICION_PERMISO_LOCALIZACION = 101;

    private void miUbicacion() {
        try {
            if (ActivityCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext().getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(UbicanosActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PETICION_PERMISO_LOCALIZACION);
                return;
            } else {
                LocationManager locationManager = (LocationManager) getApplication().getSystemService(Context.LOCATION_SERVICE);
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                ActualizarUbicacion(location);
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locListener);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //actualizar la ubicacion
    private void ActualizarUbicacion(Location location) {
        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            AgregarMarcador(lat, lng);

        }
    }

        //agregar el marcador en el mapa
        private void AgregarMarcador(double lat, double lng) {
            LatLng coordenadas = new LatLng(lat, lng);
            //tipo de mapa
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            UiSettings uiSettings = mMap.getUiSettings();
            uiSettings.setZoomControlsEnabled(true);
            float zoomlevel = 15f;
            CameraUpdate MiUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, zoomlevel);

            if (marcador != null) marcador.remove();
            marcador = mMap.addMarker(new MarkerOptions()
                    .position(coordenadas)
                    .title("Mi Ubicación:" + direccion)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
            //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_person_home)));
            mMap.animateCamera(MiUbicacion);
        }

        //setear mi position
        public void setLocation(Location loc) {
            //Obtener la direccion de la calle a partir de la latitud y la longitud
            if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
                try {
                    Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                    List<Address> list = geocoder.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);

                    if (!list.isEmpty()) {
                        //obtner la direccion
                        Address DirCalle = list.get(0);
                        direccion = (DirCalle.getAddressLine(0));
                        //obten el pais
                        Locale country = new Locale("", "PE");
                        pais = country.getDisplayCountry();

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        //control del gps
        LocationListener locListener = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                try {

                    ActualizarUbicacion(location);
                    setLocation(location);

                } catch (Exception e) {
                    Log.i("error", e.getMessage());
                }

                //mLastLocation.set(location);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {
                mensaje1 = ("GPS Activado");
                Mensaje();

            }

            @Override
            public void onProviderDisabled(String s) {
                mensaje1 = ("GPS Desactivado");
                //locationStart();
                Mensaje();

            }
        };

        public void Mensaje() {
            Toast toast = Toast.makeText(getApplicationContext(), mensaje1, Toast.LENGTH_LONG);
            //toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
        }


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            return false;
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.img_ubicame) {
                miUbicacion();
            }
        }
    }

