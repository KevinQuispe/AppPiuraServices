package com.piuraservices.piuraservices.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.piuraservices.piuraservices.R;

public class OpenWebActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        WebView webview;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_web);
        getSupportActionBar().setTitle("Open Web");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webview = (WebView) findViewById(R.id.webview);
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
