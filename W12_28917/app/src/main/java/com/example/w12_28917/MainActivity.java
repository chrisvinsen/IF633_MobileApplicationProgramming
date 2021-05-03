package com.example.w12_28917;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btnSensorDetection, btnSensorRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSensorDetection = findViewById(R.id.btnSensorDetection);
        btnSensorRead = findViewById(R.id.btnSensorRead);

        btnSensorDetection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(MainActivity.this, SensorDetectionActivity.class);
                startActivity(intent);
            }
        });

        btnSensorRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(MainActivity.this, SensorReadActivity.class);
                startActivity(intent);
            }
        });
    }
}