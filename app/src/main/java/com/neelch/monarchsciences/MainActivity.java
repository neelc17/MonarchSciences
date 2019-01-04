package com.neelch.monarchsciences;

import android.app.Activity;
import android.os.Handler;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private final Activity tempRef = this;
    private WebView webView;
    private Button amber;
    private Button crimson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
            e.getStackTrace();
        }
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.monarchsciences.com/");

        amber = (Button) findViewById(R.id.amber);
        amber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl("javascript:ConfidentialInfo.unlockAmber()");
                Toast.makeText(tempRef, "Amber Clearance Unlocked!",
                        Toast.LENGTH_LONG).show();
                amber.setEnabled(false);
            }
        });

        crimson = (Button) findViewById(R.id.crimson);
        crimson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl("javascript:ConfidentialInfo.unlockCrimson()");
                Toast.makeText(tempRef, "Crimson Clearance Unlocked!",
                        Toast.LENGTH_LONG).show();
                crimson.setEnabled(false);
            }
        });
    }
}