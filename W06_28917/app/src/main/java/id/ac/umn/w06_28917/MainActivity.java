package id.ac.umn.w06_28917;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import id.ac.umn.w06_28917.TutorialDrawable.TutorialDrawable;
import id.ac.umn.w06_28917.TutorialPhysics.TutorialPhysics;
import id.ac.umn.w06_28917.TutorialProperty.TutorialProperty;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void redirectTProperty(View view) {
        Intent intent = new Intent(this, TutorialProperty.class);
        Button btn = findViewById(R.id.btnTProperty);
        String title = btn.getText().toString();
        intent.putExtra("Title", title);
        startActivity(intent);
    }

    public void redirectTDrawable(View view) {
        Intent intent = new Intent(this, TutorialDrawable.class);
        Button btn = findViewById(R.id.btnTDrawable);
        String title = btn.getText().toString();
        intent.putExtra("Title", title);
        startActivity(intent);
    }

    public void redirectTPhysics(View view) {
        Intent intent = new Intent(this, TutorialPhysics.class);
        Button btn = findViewById(R.id.btnTPhysics);
        String title = btn.getText().toString();
        intent.putExtra("Title", title);
        startActivity(intent);
    }
}