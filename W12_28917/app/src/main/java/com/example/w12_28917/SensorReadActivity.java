package com.example.w12_28917;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class SensorReadActivity extends AppCompatActivity implements SensorEventListener {
    TextView tvLight, tvProximity;
    Sensor sensorLight, sensorProximity;
    SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_read);

        tvLight = findViewById(R.id.lightSensor);
        tvProximity = findViewById(R.id.proximitySensor);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        sensorLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int sensorType = event.sensor.getType();
        float currentValue = event.values[0];

        switch(sensorType) {
            case Sensor.TYPE_LIGHT:
                tvLight.setText("Light Sensor : " + String.format("%.2f", currentValue));
                break;
            case Sensor.TYPE_PROXIMITY:
                tvProximity.setText("Proximity Sensor : " + String.format("%.2f", currentValue));
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (sensorProximity != null) {
            mSensorManager.registerListener(this, sensorProximity, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (sensorLight != null) {
            mSensorManager.registerListener(this, sensorLight, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}