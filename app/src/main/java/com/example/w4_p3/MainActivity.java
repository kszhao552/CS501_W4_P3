package com.example.w4_p3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {
    WebView web;

    String xWeb = "https://www.ecosia.org/";
    String yWeb = "https://www.dogpile.com/";
    String zWeb = "https://webb.nasa.gov/";
    String shakeWeb = "https://jumpingjaxfitness.files.wordpress.com/2010/07/dizziness.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        web = (WebView) findViewById(R.id.web);
    }
}