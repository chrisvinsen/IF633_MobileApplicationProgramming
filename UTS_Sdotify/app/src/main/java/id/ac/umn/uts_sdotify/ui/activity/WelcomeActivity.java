package id.ac.umn.uts_sdotify.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import id.ac.umn.uts_sdotify.R;
import id.ac.umn.uts_sdotify.controller.AppController;

public class WelcomeActivity extends AppCompatActivity {
    private Button btnLogin;
    private Button btnAbout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btnLogin = findViewById(R.id.btnLogin);
        btnAbout = findViewById(R.id.btnAbout);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppController.openLoginActivity(WelcomeActivity.this, false);
            }
        });
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppController.openAboutActivity(WelcomeActivity.this);
            }
        });
    }
}