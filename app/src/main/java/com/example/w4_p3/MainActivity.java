package com.example.w4_p3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private WebView web;
    private SensorManager sensorManager;
    private Sensor sensor;

    String xWeb = "https://www.ecosia.org/";
    String yWeb = "https://www.dogpile.com/";
    String zWeb = "https://webb.nasa.gov/";
    String shakeWeb = "https://jumpingjaxfitness.files.wordpress.com/2010/07/dizziness.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        web = (WebView) findViewById(R.id.web);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onResume() {
       super.onResume();
       sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {
    }
}