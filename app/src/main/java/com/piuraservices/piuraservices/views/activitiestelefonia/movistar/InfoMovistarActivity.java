package com.piuraservices.piuraservices.views.activitiestelefonia.movistar;

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

public class InfoMovistarActivity extends AppCompatActivity {

    ImageView imgtramites;
    ImageView imgreclamos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_movistar);
        getSupportActionBar().setTitle("Empresa Movistar");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imgtramites = (ImageView) findViewById(R.id.img_tramitesmovistar);
        imgreclamos = (ImageView) findViewById(R.id.img_reclamosmovistar);


    }
        public void onClickedtramites(View v) {
            imgtramites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Intent intent = new Intent("views.activities.InfoTramitesEpsActivity");
                    Intent intent = new Intent(getApplicationContext(),InfoTramitesMovistarActivity.class);
                    startActivity(intent);
                }
            });
        }

        public void onClickedreclamos(View v) {
            imgreclamos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent intent = new Intent("views.activities.InfoReclamosEpsActivity");
                    Intent intent = new Intent(getApplicationContext(),InfoReclamosMovistarActivity.class);
                    startActivity(intent);
                }
            });
        }
        public void onClickOpenGoogleMaps(View v) {

            Uri uri = Uri.parse("geo:41.3825581,2.1704375?z=16&q=-5.19449, -80.6328201(Movistar Piura)");
            //startActivity( new Intent(Intent.ACTION_VIEW, uri));
            Intent intent=new Intent(Intent.ACTION_VIEW,uri);
            Intent chooser=Intent.createChooser(intent,"Abrir Google Maps");
            startActivity(chooser);
            //Uri uri = Uri.parse("geo:41.3825581,2.1704375?z=16&q=-5.19449, -80.6328201(EPS Grau Piura)");
            //startActivity(new Intent(Intent.ACTION_VIEW,uri));

        }
        public void onClickOpenEmail(View v) {

            Intent intent= new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("email"));
            String[]s={"movistar.com"};
            intent.putExtra(Intent.EXTRA_EMAIL,s);
            intent.putExtra(Intent.EXTRA_SUBJECT," ");
            intent.putExtra(Intent.EXTRA_TEXT," ");
            intent.setType("message/rfc822");
            Intent chooser=Intent.createChooser(intent,"Enviar Email");
            startActivity(chooser);

        }
        public void onClickOpenWeb(View v) {
            //Intent intent = new Intent("views.activities.OpenWebActivity");
            Intent intent = new Intent(InfoMovistarActivity.this,OpenWebMovistarActivity.class);
            startActivity(intent);
        }
        public void onClickOpenCall(View v) {
            Intent i = new Intent(Intent.ACTION_DIAL);
            String telmovistar= "104";
            if (telmovistar.trim().isEmpty()) {
                i.setData(Uri.parse("tel:104"));
            } else {
                i.setData(Uri.parse("tel:" + telmovistar));
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
