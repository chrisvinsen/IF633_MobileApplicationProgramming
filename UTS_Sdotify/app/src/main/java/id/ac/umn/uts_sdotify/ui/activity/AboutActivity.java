package id.ac.umn.uts_sdotify.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import id.ac.umn.uts_sdotify.R;
import id.ac.umn.uts_sdotify.ui.fragment.AboutFragment;

public class AboutActivity extends AppCompatActivity {
    private AboutFragment fmtAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        fmtAbout = new AboutFragment();
        transaction.replace(R.id.container, fmtAbout);

        transaction.commit();
    }
}