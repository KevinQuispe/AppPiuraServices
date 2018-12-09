package com.piuraservices.piuraservices.views.activitiestelefonia.entel;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.ColorFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.piuraservices.piuraservices.R;
public class InfoEntelActivity extends AppCompatActivity {
    ImageView imgtramites;
    ImageView imgreclamos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_entel);
        getSupportActionBar().setTitle("Empresa Entel");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imgtramites = (ImageView) findViewById(R.id.img_tramitesentel);
        imgreclamos = (ImageView) findViewById(R.id.img_reclamosentel);

    }
    public void onClickedtramites(View v) {
        imgtramites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent = new Intent("views.activities.InfoTramitesEpsActivity");
                Intent intent = new Intent(getApplicationContext(),InfoTramitesEntelActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onClickedreclamos(View v) {
        imgreclamos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent("views.activities.InfoReclamosEpsActivity");
                Intent intent = new Intent(getApplicationContext(),InfoReclamosEntelActivity.class);
                startActivity(intent);
            }
        });
    }
    public void onClickOpenGoogleMaps(View v) {

        //Uri uri = Uri.parse("geo:41.3825581,2.1704375?z=16&q=-5.19449, -80.6328201(Entel Piura)");
        //startActivity( new Intent(Intent.ACTION_VIEW, uri));
        //Uri uri = Uri.parse("https://www.google.com.pe/maps/place/Entel+Per%C3%BA/@-5.1955993,-80.6261248,19z/data=!4m12!1m6!3m5!1s0x904a107c378a0833:0x22691540f5193030!2sEntel+Per%C3%BA!8m2!3d-5.1955993!4d-80.6255776!3m4!1s0x904a107c378a0833:0x22691540f5193030!8m2!3d-5.1955993!4d-80.6255776");
        //Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        //Intent chooser=Intent.createChooser(intent,"Abrir Google Maps");
        //startActivity(chooser);
        //Uri uri = Uri.parse("geo:41.3825581,2.1704375?z=16&q=-5.19449, -80.6328201(EPS Grau Piura)");
        //startActivity(new Intent(Intent.ACTION_VIEW,uri));
        //Uri gmmIntentUri = Uri.parse("google.navigation:q=Taronga+Zoo,+Sydney+Australia");

        String centralentel="Jr. Libertad 607, Piura, Tienda Piura";
        Uri gmmIntentUri = Uri.parse("geo:0,0?q="+centralentel+"Entel Piura");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        Intent chooser = Intent.createChooser(mapIntent, "Abrir Google Maps");
        startActivity(chooser);
    }
    public void onClickOpenEmail(View v) {

        Intent intent= new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("email"));
        String[]s={"contactoentel@entel.com"};
        intent.putExtra(Intent.EXTRA_EMAIL,s);
        intent.putExtra(Intent.EXTRA_SUBJECT,"");
        intent.putExtra(Intent.EXTRA_TEXT,"");
        intent.setType("message/rfc822");
        Intent chooser=Intent.createChooser(intent,"Enviar Email");
        startActivity(chooser);

    }
    public void onClickOpenWeb(View v) {
        //Intent intent = new Intent("views.activities.OpenWebActivity");
        Intent intent = new Intent(InfoEntelActivity.this,OpenWebEntelActivity.class);
        startActivity(intent);
    }
    public void onClickOpenCall(View v) {
        Intent i = new Intent(Intent.ACTION_DIAL);
        String telentel= "0 800 11110";
        if (telentel.trim().isEmpty()) {
            i.setData(Uri.parse("tel:0 800 11110"));
        } else {
            i.setData(Uri.parse("tel:" + telentel));
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
