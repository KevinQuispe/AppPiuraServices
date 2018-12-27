package com.piuraservices.piuraservices.views.fragments;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
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
import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.services.geolocation.Claro;
import com.piuraservices.piuraservices.services.geolocation.Entel;
import com.piuraservices.piuraservices.services.geolocation.Movistar;
import com.piuraservices.piuraservices.utils.FirebaseReferences;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * A simple {@link Fragment} subclass.
 */
public class UbicanosFragment extends Fragment implements OnMapReadyCallback, android.location.LocationListener {

    private GoogleMap mMap;
    MapView mMapView;
    View mView;
    private GoogleMap googleMap;
    private Marker marcador;
    double lat = 0.0;
    double lng = 0.0;
    String mensaje1;
    String direccion = "";
    String pais = "";
    Button ubicame;
    Button favorito;
    EditText direction, country;
    ImageView imgubicame;
    RatingBar star;
    //get location en google maps
    private FusedLocationProviderClient mFusedLocationClient;
    //variables para subir latitud y longitud a firebase
    private DatabaseReference mdatabase;
    private ArrayList<Marker> tmprealtimemarkers = new ArrayList<>();
    private ArrayList<Marker> realtimemarkers = new ArrayList<>();


    private OnFragmentInteractionListener mListener;
    public UbicanosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_ubicanos, container, false);
        direction = (EditText) mView.findViewById(R.id.text_direccion_mapa);
        country = (EditText) mView.findViewById(R.id.txt_ciudad_mapa);
        imgubicame = (ImageView) mView.findViewById(R.id.img_ubicame);
        //inicializar la variable de base de datos firebase
        mdatabase = FirebaseDatabase.getInstance().getReference();
        imgubicame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                miUbicacion();
            }
        });
        return mView;

    }

    //HERE MY CODE
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMapView = (MapView) mView.findViewById(R.id.map);

        if (mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        mMap = googleMap;
        miUbicacion();
        mostrarDatos();
        elijebaseentidad("claro");
        //subirpuntosClaro();
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
                    Toast.makeText(getContext(), "DatabaseUnkonw", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    //marcador de puntos para la empresa movistar
    public void subirpuntosMovistar() {
        //get location en google maps
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
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
                        }
                        else{
                            Toast.makeText(getContext(),"onMarkerss",Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }
    //marcador de puntos para la empresa movistar
    public void subirpuntosClaro() {
        //get location en google maps
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
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
                        }
                        else{
                            Toast.makeText(getActivity(),"onMarkers",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    //marcador de puntos para la empresa entel
    public void subirpuntosEntel() {
        //get location en google maps
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
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
                        }
                        else{
                            Toast.makeText(getContext(),"onMarkers",Toast.LENGTH_SHORT).show();
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


    //identifica la direccion con acces fine location
    private static int PETICION_PERMISO_LOCALIZACION = 101;
    private void miUbicacion() {
        if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext().getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this.getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PETICION_PERMISO_LOCALIZACION);
            return;
        } else {
            LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            ActualizarUbicacion(location);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locListener);
        }
    }

    //agregar el marcador en el mapa
    private void AgregarMarcador(double lat, double lng) {
        LatLng coordenadas = new LatLng(lat, lng);
        //tipo de mapa
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        float zoomlevel = 14.5f;
        CameraUpdate MiUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, zoomlevel);

        if (marcador != null) marcador.remove();
        marcador = mMap.addMarker(new MarkerOptions()
                .position(coordenadas)
                .title("Dirección:" + direccion)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));

        mMap.animateCamera(MiUbicacion);
    }

    public void mostrarDatos() {
        String country_name = null;
        String direccionexacta = null;
        LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Geocoder geocoder = new Geocoder(getActivity());
        for (String provider : lm.getAllProviders()) {
            @SuppressWarnings("ResourceType") Location location = lm.getLastKnownLocation(provider);
            if (location != null) {
                try {
                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    if (addresses != null && addresses.size() > 0) {
                        country_name = addresses.get(0).getCountryName();
                        Address DirCalle = addresses.get(0);
                        direccion = (DirCalle.getAddressLine(0));
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        country.setText(country_name);
        direction.setText(direccion);
        Toast toast = Toast.makeText(getContext(), "Your Location", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
        //Toast.makeText(getActivity(), "Tu Ubicacion", Toast.LENGTH_LONG).show();

    }

    //actualizar la ubicacion
    private void ActualizarUbicacion(Location location) {
        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            AgregarMarcador(lat, lng);

        }
    }

    //activar los servicios del gps cuando esten apagados
    public void locationStart() {
        LocationManager mlocManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
        }
    }

    //setear mi position
    public void setLocation(Location loc) {
        //Obtener la direccion de la calle a partir de la latitud y la longitud
        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
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
            try {
                mensaje1 = ("GPS Activado");
                Mensaje();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public void onProviderDisabled(String s) {
            try {
                mensaje1 = ("GPS Desactivado");
                //locationStart();
                Mensaje();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    };

    public void Mensaje() {
        Toast toast = Toast.makeText(getContext(), mensaje1, Toast.LENGTH_LONG);
        //toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }

    //new code
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}