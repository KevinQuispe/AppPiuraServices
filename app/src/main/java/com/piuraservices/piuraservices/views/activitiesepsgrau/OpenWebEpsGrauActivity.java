package com.piuraservices.piuraservices.views.activitiesepsgrau;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.piuraservices.piuraservices.R;

public class OpenWebEpsGrauActivity extends AppCompatActivity {

    WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_web_eps_grau);

        getSupportActionBar().setTitle("Web EPS Grau");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        webview = (WebView) findViewById(R.id.webviewepsgrau);

        String url = "http://www.epsgrau.pe/webpage/desktop/views/";
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
