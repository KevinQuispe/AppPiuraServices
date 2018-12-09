package com.piuraservices.piuraservices.views.activitiesenosa;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.Toast;

import com.piuraservices.piuraservices.R;

public class EnosaActivity extends AppCompatActivity {

    ImageView imgtramites;
    ImageView imgreclamos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enosa);
        getSupportActionBar().setTitle("Entidad Enosa");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imgtramites = (ImageView) findViewById(R.id.img_tramitesenosa);
        imgreclamos = (ImageView) findViewById(R.id.img_reclamosenosa);
}

    public void onClickedreclamos(View v) {
        imgreclamos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("views.activitiesenosa.InfoReclamosEnosaActivity");
                startActivity(intent);
            }
        });
    }
    public void onClickedtramites(View v) {
        imgtramites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("views.activitiesenosa.InfoTramitesEnosaActivity");
                startActivity(intent);
            }
        });
    }
    public void onClickOpenGoogleMaps(View v) {
        /*
        Uri uri = Uri.parse("https://www.google.com/maps/@41.3825581,2.1704375,16z");
        if (URLUtil.isValidUrl(uri.toString())) {
            startActivity( new Intent(Intent.ACTION_VIEW, uri));
        */
        //'https: //maps.google.com/? Q = 38.6531004, -90.243462 & ll = 38.6531004, -90.243462 & z = 3'

        //Uri uri = Uri.parse("geo:41.3825581,2.1704375?z=16&q=-5.1919654,-80.6281973,15z(Enosa Piura)");
        //-5.1954659,-80.6318516
        Uri uri = Uri.parse("https://www.google.com.pe/maps/dir/-5.1952355,-80.6095077/ENOSA,+Sullana/@-5.1919654,-80.6281973,17z/data=!3m1!4b1!4m16!1m6!3m5!1s0x904a107f28c128d3:0xba15734a28591ed3!2sENOSA!8m2!3d-5.1939378!4d-80.6301963!4m8!1m1!4e1!1m5!1m1!1s0x904a107f28c128d3:0xba15734a28591ed3!2m2!1d-80.6301963!2d-5.1939378");
        //Uri uri = Uri.parse("geo:41.3825581,2.1704375?z=16&q=-5.1954659,-80.6318516(Enosa Piura)");
        //startActivity( new Intent(Intent.ACTION_VIEW, uri));
        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        Intent chooser=Intent.createChooser(intent,"Abrir Google Maps");
        startActivity(chooser);
    }
    public void onClickOpenEmail(View v) {

        Intent intent= new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("email"));
        String[]s={"contactoenosa@enosa.com"};
        intent.putExtra(Intent.EXTRA_EMAIL,s);
        intent.putExtra(Intent.EXTRA_SUBJECT," ");
        intent.putExtra(Intent.EXTRA_TEXT," ");
        intent.setType("message/rfc822");
        Intent chooser=Intent.createChooser(intent,"Enviar Email");
        startActivity(chooser);

    }
    public void onClickOpenWeb(View v) {
        Intent intent = new Intent(EnosaActivity.this,OpenWebEnosaActivity.class);
        startActivity(intent);
    }
    public void onClickOpenCall(View v) {
        Intent i = new Intent(Intent.ACTION_DIAL);
        String telenosa= "073 284050";
        if (telenosa.trim().isEmpty()) {
            i.setData(Uri.parse("tel:073 284050"));
        } else {
            i.setData(Uri.parse("tel:" + telenosa));
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
