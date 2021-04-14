package id.ac.umn.w10_28917;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import id.ac.umn.w10_28917.bound_service.CustomBoundService;
import id.ac.umn.w10_28917.unbound_service.CustomService;
import id.ac.umn.w10_28917.unbound_service.SimpleIntentService;

public class ServiceActivity extends AppCompatActivity {
    CustomBoundService customBoundService;
    boolean isBound = false;

    private ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            CustomBoundService.CustomLocalBinder binder = (CustomBoundService.CustomLocalBinder) iBinder;
            customBoundService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        Intent serviceIntent = new Intent(this, SimpleIntentService.class);
        startService(serviceIntent);

        Button btnStartService = findViewById(R.id.main_button_startservice);
        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ServiceActivity.this, CustomService.class);
                startService(intent);
            }
        });

        Intent intent = new Intent(this, CustomBoundService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

        Button btnShowTime = findViewById(R.id.main_button_showtime);
        btnShowTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentTime = customBoundService.getCurrentTime();
                Toast.makeText(getApplicationContext(), currentTime, Toast.LENGTH_LONG).show();
            }
        });
    }
}