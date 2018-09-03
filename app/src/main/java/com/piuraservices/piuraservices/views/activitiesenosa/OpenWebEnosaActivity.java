package com.piuraservices.piuraservices.views.activitiesenosa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.piuraservices.piuraservices.R;

public class OpenWebEnosaActivity extends AppCompatActivity {

    WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_web_enosa);
        webview=(WebView) findViewById(R.id.webviewenosa);
        getSupportActionBar().setTitle("Web Enosa");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String url = "https://www.distriluz.com.pe/enosa/03_servicio/como_obt.html";
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
