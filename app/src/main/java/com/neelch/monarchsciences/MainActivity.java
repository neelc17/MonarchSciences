package com.neelch.monarchsciences;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Build;
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

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    private final Activity tempRef = this;
    private WebView webView;
    private Button amber;
    private Button crimson;
    private Button reset;
    private int modalsShowing;

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
        webView.addJavascriptInterface(this, "android");
        webView.loadUrl("https://www.monarchsciences.com/");

        amber = (Button) findViewById(R.id.amber);
        amber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl("javascript:ConfidentialInfo.unlockAmber()");
                Toast.makeText(tempRef, "Amber Clearance Unlocked!",
                        Toast.LENGTH_LONG).show();
                amber.setEnabled(false);
                amber.setAlpha(0.5f);
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
                crimson.setAlpha(0.5f);
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
                amber.setAlpha(1.0f);
                crimson.setEnabled(true);
                crimson.setAlpha(1.0f);
            }
        });

        Typeface font = Typeface.createFromAsset(getAssets(), "font/futura_book.ttf");
        amber.setTypeface(font);
        crimson.setTypeface(font);
        reset.setTypeface(font);

        if (Build.VERSION.SDK_INT >= 21)
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark));

        modalsShowing = -1;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            webView.loadUrl("javascript:android.onData(ModalManager.modalsShowing)");
            String s = waitForModal();
            if(s.equals("")) {
                Toast.makeText(tempRef, "Took too long to load!",
                        Toast.LENGTH_LONG).show();
                return true;
            } else {
                boolean result = Boolean.parseBoolean(s);
                if(result){
                    webView.loadUrl("javascript:ModalManager.closeModals()");
                    return true;
                } else {
                    return super.onKeyDown(keyCode, event);
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @JavascriptInterface
    public void onData(String value) {
        if(value.equals("true")) modalsShowing = 1;
        else modalsShowing = 0;
    }

    public String waitForModal(){
        long start = System.currentTimeMillis();
        String value = "";
        while(true){
            if(System.currentTimeMillis() - start > 5000) break;
            if(modalsShowing != -1){
                if(modalsShowing == 0) value = "false";
                else value = "true";
                break;
            }
        }
        modalsShowing = -1;
        return value;
    }
}