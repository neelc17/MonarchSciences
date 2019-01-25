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
    private Button reset;

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

        reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl("https://www.monarchsciences.com/");
                Toast.makeText(tempRef, "Page Refreshed!",
                        Toast.LENGTH_LONG).show();
                amber.setEnabled(true);
                crimson.setEnabled(true);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Toast.makeText(this, "Back pressed", Toast.LENGTH_LONG).show();
        }
        return super.onKeyDown(keyCode, event);
    }
}