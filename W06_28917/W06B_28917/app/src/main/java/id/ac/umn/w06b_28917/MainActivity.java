package id.ac.umn.w06b_28917;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    AnimationDrawable animHorse;
    ImageView imgHorse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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