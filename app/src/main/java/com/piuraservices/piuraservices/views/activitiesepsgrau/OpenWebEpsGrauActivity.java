package com.piuraservices.piuraservices.views.activitiesepsgrau;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.piuraservices.piuraservices.R;

public class OpenWebEpsGrauActivity extends AppCompatActivity {

    WebView webview;
    String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_web_eps_grau);

        getSupportActionBar().setTitle("Web EPS Grau");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        webview = (WebView) findViewById(R.id.webviewepsgrau);
        openPaginaWeb();

    }

    //abrir pagina web
    public void openPaginaWeb() {
        url = "http://www.epsgrau.pe/webpage/desktop/views/";
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.loadUrl(url);
        webview.setWebViewClient(new WebViewClient() {
                                     public boolean shouldOverrideLoadURL(WebView v, String url) {
                                         return false;
                                     }
                                 }
        );
    }
    //abrir navegador web con web view no funtion
    public void openInfoWeb(){
        Intent thisForm = getIntent();
        Bundle parametros = thisForm.getExtras();
        String infourl = parametros.getString("descripcionKey");
        final String parseurl=infourl.toString();
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.loadUrl(parseurl);
        webview.setWebViewClient(new WebViewClient() {
                                     public boolean shouldOverrideLoadURL(WebView v, String url) {

                                         return false;
                                     }
                                 }
        );
        Toast.makeText(OpenWebEpsGrauActivity.this,"Click en el Link "+parseurl,Toast.LENGTH_SHORT).show();
    }
}
