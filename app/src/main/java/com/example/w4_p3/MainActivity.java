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

    private final float NOISE = (float) 10.0;
    private final float SHAKE = (float) 800;
    private final int TIME_DELAY = 100;
    private long lastTime = 0;
    private boolean flag;
    private float lastX;
    private float lastY;
    private float lastZ;

    String xWeb = "https://www.ecosia.org/";
    String yWeb = "https://www.dogpile.com/";
    String zWeb = "https://webb.nasa.gov/";
    String shakeWeb = "https://jumpingjaxfitness.files.wordpress.com/2010/07/dizziness.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        web = (WebView) findViewById(R.id.web);
        flag = false;

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
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        long curTime = System.currentTimeMillis();

        if (!flag){
            lastX = x;
            lastY = y;
            lastZ = z;
            flag = true;
        }
        else {
            float deltaX = Math.abs(lastX - x);
            float deltaY = Math.abs(lastY - y);
            float deltaZ = Math.abs(lastZ - z);

            if (deltaX < NOISE) deltaX = (float)0.0;
            if (deltaY < NOISE) deltaY = (float)0.0;
            if (deltaZ < NOISE) deltaZ = (float)0.0;

            if (deltaX != 0 && deltaX >= deltaY && deltaX >= deltaZ) {
                web.loadUrl(xWeb);
            } else if (deltaY != 0 && deltaY > deltaX && deltaY > deltaZ) {
                web.loadUrl(yWeb);
            } else if (deltaZ != 0 && deltaZ > deltaX && deltaZ > deltaY) {
                web.loadUrl(zWeb);
            }

            if (curTime-lastTime>TIME_DELAY) {
                long diffTime = curTime-lastTime;
                lastTime = curTime;
                float speed = Math.abs(x+y+z - lastX - lastY - lastZ) / diffTime * 10000;
                if (speed > SHAKE){
                    web.loadUrl(shakeWeb);
                }
            }


            lastX = x;
            lastY = y;
            lastZ = z;
        }
    }
}