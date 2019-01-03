package com.neelch.monarchsciences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private Button amber;
    private Button crimson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.monarchsciences.com/");

        amber = (Button) findViewById(R.id.amber);
        amber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl("javascript:ConfidentialInfo.unlockAmber()");
            }
        });

        crimson = (Button) findViewById(R.id.crimson);
        crimson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    webView.loadUrl("javascript:ConfidentialInfo.unlockCrimson()");
            }
        });
    }
}