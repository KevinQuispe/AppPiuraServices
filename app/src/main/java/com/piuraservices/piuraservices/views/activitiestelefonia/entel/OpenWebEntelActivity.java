package com.piuraservices.piuraservices.views.activitiestelefonia.entel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.piuraservices.piuraservices.R;

public class OpenWebEntelActivity extends AppCompatActivity {

    WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_web_entel);
        getSupportActionBar().setTitle("Web Entel");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        webview = (WebView) findViewById(R.id.webviewentel);
        String url = "http://www.entel.pe/personas/ayuda-y-soporte/reclamos-y-solicitudes/";
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
}
