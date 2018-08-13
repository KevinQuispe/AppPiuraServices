package com.piuraservices.piuraservices.views.activitiesenosa;

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

        Uri uri = Uri.parse("geo:41.3825581,2.1704375?z=16&q=-5.19449, -80.6328201(EPS Grau Piura)");
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
        String[]s={"epsgrau.com"};
        intent.putExtra(Intent.EXTRA_EMAIL,s);
        intent.putExtra(Intent.EXTRA_SUBJECT,"Solicitud");
        intent.putExtra(Intent.EXTRA_TEXT,"Contenido");
        intent.setType("message/rfc822");
        Intent chooser=Intent.createChooser(intent,"Enviar Email");
        startActivity(chooser);

    }
    public void onClickOpenWeb(View v) {
        //Uri uri = Uri.parse("https://www.epsgrau.pe/webpage/desktop/views/");
        //Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        //startActivity(intent);
        //WebView webView=new WebView(this);
        //webView.setWebViewClient(new WebViewClient());
        //webView.loadUrl("https://www.epsgrau.pe/webpage/desktop/views/");
        Intent intent = new Intent("views.activities.OpenWebActivity");
        startActivity(intent);
    }
    public void onClickOpenCall(View v) {
        Intent i = new Intent(Intent.ACTION_DIAL);
        String spsgrau= "073307741";
        if (spsgrau.trim().isEmpty()) {
            i.setData(Uri.parse("tel:073307741"));
        } else {
            i.setData(Uri.parse("tel:" + spsgrau));
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
