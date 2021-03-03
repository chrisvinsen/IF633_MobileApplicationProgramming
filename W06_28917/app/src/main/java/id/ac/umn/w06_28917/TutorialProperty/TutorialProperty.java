package id.ac.umn.w06_28917.TutorialProperty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import id.ac.umn.w06_28917.R;

public class TutorialProperty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_property);

        Intent intReceived = getIntent();
        String title = intReceived.getStringExtra("Title");
        setTitle(title);
    }
}