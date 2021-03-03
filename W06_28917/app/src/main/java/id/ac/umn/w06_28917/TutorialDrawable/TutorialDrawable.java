package id.ac.umn.w06_28917.TutorialDrawable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import id.ac.umn.w06_28917.R;

public class TutorialDrawable extends AppCompatActivity {
    AnimationDrawable animHorse;
    ImageView imgHorse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_drawable);

        Intent intReceived = getIntent();
        String title = intReceived.getStringExtra("Title");
        setTitle(title);

        imgHorse = findViewById(R.id.imgHorse);
        imgHorse.setBackgroundResource(R.drawable.kuda_lari);
        animHorse = (AnimationDrawable) imgHorse.getBackground();
        imgHorse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animHorse.start();
            }
        });
    }
}