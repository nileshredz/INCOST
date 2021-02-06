package com.example.incost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tv_new;
    ImageView ref,sh;
    WebView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ///////////////////
        // Find View By Id
        ///////////////////
        tv_new = findViewById(R.id.tv);
        ref = findViewById(R.id.refresh);
        view = findViewById(R.id.web_view);
        sh = findViewById(R.id.share);


        checkNetworkConnnectionStatus();

        //////////////////////////////////////////////////////////////////
        // Check Internet connection mode whenever clicked on refresh icon.
        //////////////////////////////////////////////////////////////////

        ref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkNetworkConnnectionStatus();
            }
        });

        ////////////////////////////////////////////////////////////
        // HTML View for Speed checking using JavaScript in WebView
        ///////////////////////////////////////////////////////////

        String my_url ="file:///android_asset/net.html";
        WebSettings webSettings = view.getSettings();
        webSettings.setJavaScriptEnabled(true);
        view.setWebChromeClient(new WebChromeClient());
        view.loadUrl(my_url);

        ///////////////////////////////////////////////////
        // Share the INCOST App when clicked on Share icon
        //////////////////////////////////////////////////

        sh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String website = "https://raw.githubusercontent.com/nileshredz/INCOST/master/Incost.apk";
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String subject = "Hey! \nI would like you to check out this INCOST (Internet Connection Status). \n\nINCOST is a compact Internet Connection Analyzer.\nWant to get your hands on it?   \n\nDownload INCOST using the Link: \n" +website+" \n \n Thank You! ";
                //intent.putExtra(Intent.EXTRA_SUBJECT,website);
                intent.putExtra(Intent.EXTRA_TEXT,subject);
                startActivity(Intent.createChooser(intent,"Share INCOST using:"));
            }
        });

    }
    ////////////////////////////////////////
    // Function to Check the Connection mode
    ////////////////////////////////////////

    private void checkNetworkConnnectionStatus() {
        boolean wifiConnected;
        boolean mobileConnected;

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = connectivityManager.getActiveNetworkInfo();

        if(activeInfo != null && activeInfo.isConnected()){
            wifiConnected = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
            mobileConnected = activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;
            if(wifiConnected){
                tv_new.setText("Connected with Wifi");
            }
            else if(mobileConnected){
                tv_new.setText("Connected to the Mobile Data");
            }

        }
        else{
            tv_new.setText("No Internet Connection");

        }

    }
}
