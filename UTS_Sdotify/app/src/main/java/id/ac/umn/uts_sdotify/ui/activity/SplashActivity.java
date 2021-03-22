package id.ac.umn.uts_sdotify.ui.activity;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import id.ac.umn.uts_sdotify.R;
import id.ac.umn.uts_sdotify.controller.AppController;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
    }

    private void init() {
        new Handler().postDelayed(() -> {
            AppController.openWelcomeActivity(this);
        }, 1000L); //1000 L = 1 second
    }
}
