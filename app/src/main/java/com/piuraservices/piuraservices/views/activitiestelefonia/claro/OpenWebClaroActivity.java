package com.piuraservices.piuraservices.views.activitiestelefonia.claro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.piuraservices.piuraservices.R;

public class OpenWebClaroActivity extends AppCompatActivity {

    WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_web_claro);

        getSupportActionBar().setTitle("Web Claro");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        webview = (WebView) findViewById(R.id.webviewclaro);
        String url = "http://www.claro.com.pe/libro-de-reclamaciones/";
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
