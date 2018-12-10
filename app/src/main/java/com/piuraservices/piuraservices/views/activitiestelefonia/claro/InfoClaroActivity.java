package com.piuraservices.piuraservices.views.activitiestelefonia.claro;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.piuraservices.piuraservices.R;

public class InfoClaroActivity extends AppCompatActivity {
    ImageView imgtramites;
    ImageView imgreclamos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_claro);
        getSupportActionBar().setTitle("Empresa Claro");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imgtramites = (ImageView) findViewById(R.id.img_tramitesclaro);
        imgreclamos = (ImageView) findViewById(R.id.img_reclamosclaro);

    }
    public void onClickedtramites(View v) {
        imgtramites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent = new Intent("views.activities.InfoTramitesEpsActivity");
                Intent intent = new Intent(getApplicationContext(),InfoTramitesClaroActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onClickedreclamos(View v) {
        imgreclamos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent("views.activities.InfoReclamosEpsActivity");
                Intent intent = new Intent(getApplicationContext(),InfoReclamosClaroActivity.class);
                startActivity(intent);
            }
        });
    }
    public void onClickOpenGoogleMaps(View v) {

        String centralclaro="Claro Piura, Open Plaza";
        Uri gmmIntentUri = Uri.parse("google.navigation:q="+centralclaro);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        Intent chooser = Intent.createChooser(mapIntent, "Abrir Google Maps");
        startActivity(chooser);

        //Uri uri = Uri.parse("geo:41.3825581,2.1704375?z=16&q=-5.19449, -80.6328201(Claro Piura)");
        //Uri uri = Uri.parse("https://www.google.com.pe/maps/place/Claro/@-5.1820349,-80.6225762,19z/data=!4m5!3m4!1s0x904a1072044aeeb5:0xcdd06e07e86c1d88!8m2!3d-5.1821711!4d-80.6220371(Claro Piura)");
        //Uri uri = Uri.parse("https://www.google.com.pe/maps/place/Claro/@-5.1822152,-80.6222758,20z/data=!4m6!3m5!1s0x904a1072044aeeb5:0xcdd06e07e86c1d88!4b1!8m2!3d-5.1821711!4d-80.6220371");
        //startActivity( new Intent(Intent.ACTION_VIEW, uri));
        //Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        //Intent chooser=Intent.createChooser(intent,"Abrir Google Maps");
        //startActivity(chooser);
        //Uri uri = Uri.parse("geo:41.3825581,2.1704375?z=16&q=-5.19449, -80.6328201(EPS Grau Piura)");
        //startActivity(new Intent(Intent.ACTION_VIEW,uri));

        // Create a Uri from an intent string. Use the result to create an Intent. PARA STRET VIEW
       // Uri gmmIntentUri = Uri.parse("google.streetview:cbll=-5.1820349,-80.6225762");
        // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
        //Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        // Make the Intent explicit by setting the Google Maps package
        //mapIntent.setPackage("com.google.android.apps.maps");
        // Attempt to start an activity that can handle the Intent
        //startActivity(mapIntent);

        //navegacion directa con drive
        //Uri gmmIntentUri = Uri.parse("google.navigation:q=Taronga+Zoo,+Sydney+Australia");
        //Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        //mapIntent.setPackage("com.google.android.apps.maps");
        //startActivity(mapIntent);

        // Display the location of Google, San Francisco using a global plus code.
        //Uri gmmIntentUri = Uri.parse("http://plus.codes/849VQJQ5+XX");
        // Equivalently, define the same location using a local plus code
        //String piura="Piura";
        //Uri  gmmIntentUri = Uri.parse("https://plus.codes/"+piura);
        //Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        //mapIntent.setPackage("com.google.android.apps.maps");
        //startActivity(mapIntent);


    }
    public void onClickOpenEmail(View v) {

        Intent intent= new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("email"));
        String[]s={"claro.com"};
        intent.putExtra(Intent.EXTRA_EMAIL,s);
        intent.putExtra(Intent.EXTRA_SUBJECT,"");
        intent.putExtra(Intent.EXTRA_TEXT,"");
        intent.setType("message/rfc822");
        Intent chooser=Intent.createChooser(intent,"Enviar Email");
        startActivity(chooser);

    }
    public void onClickOpenWeb(View v) {

        Intent intent = new Intent(InfoClaroActivity.this,OpenWebClaroActivity.class);
        startActivity(intent);
    }
    public void onClickOpenCall(View v) {
        Intent i = new Intent(Intent.ACTION_DIAL);
        String claro= "073307741";
        if (claro.trim().isEmpty()) {
            i.setData(Uri.parse("tel:073307741"));
        } else {
            i.setData(Uri.parse("tel:" + claro));
        }
        if (ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplication(), "Please conceda permisos para llamar", Toast.LENGTH_LONG).show();
            requestPermission();
        } else {
            startActivity(i);
        }
    }
    //permisos para llamadas
    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
    }
}
