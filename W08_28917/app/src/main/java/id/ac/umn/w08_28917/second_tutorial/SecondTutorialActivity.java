package id.ac.umn.w08_28917.second_tutorial;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Field;

import id.ac.umn.w08_28917.R;

public class SecondTutorialActivity extends AppCompatActivity {
    private int counter;
    private int color;
    private TextView tvCounter;
    private Context ctx;

    private final String COUNTER_KEY = "counter";
    private final String COLOR_KEY = "color";

    private SharedPreferences sharedPreferences;
    private String sharedPrefFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_tutorial);

        ctx = this;
        tvCounter = findViewById(R.id.tvCounter);

        /*if (savedInstanceState != null) {
            counter = savedInstanceState.getInt(COUNTER_KEY);
            Log.d("tag", "Counter : " + color);
            tvCounter.setText(String.valueOf(counter));
            color = savedInstanceState.getInt(COLOR_KEY);
            tvCounter.setBackgroundColor(color);
        } else {
            Log.d("tag", "Empty");
        }*/

        sharedPrefFile = ctx.getPackageName();
        sharedPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        counter = sharedPreferences.getInt(COUNTER_KEY, 0);
        tvCounter.setText(String.valueOf(counter));
        color = sharedPreferences.getInt(COLOR_KEY, color);
        tvCounter.setBackgroundColor(color);
    }

    public void changeBackground(View view) {
        Button btn = (Button) view;
        String name = btn.getText().toString();
        if (name.equalsIgnoreCase("Hitam")) {
            color = Color.BLACK;
        } else if (name.equalsIgnoreCase("Merah")) {
            color = Color.RED;
        } else if (name.equalsIgnoreCase("Biru")) {
            color = Color.BLUE;
        } else if (name.equalsIgnoreCase("Hijau")) {
            color = Color.GREEN;
        }
        tvCounter.setBackgroundColor(color);
    }

    public void addCounter(View view) {
        counter++;
        tvCounter.setText(String.valueOf(counter));
    }

    public void resetCounter(View view) {
        counter = 0;
        tvCounter.setText(String.valueOf(counter));
        color = ContextCompat.getColor(ctx, R.color.white);
        tvCounter.setBackgroundColor(color);
    }

    /*@Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d("tag", "save : " + color);
        super.onSaveInstanceState(outState);
        outState.putInt(COUNTER_KEY, counter);
        outState.putInt(COLOR_KEY, color);
    }*/

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor preferencesEditor = sharedPreferences.edit();
        preferencesEditor.putInt(COUNTER_KEY, counter);
        preferencesEditor.putInt(COLOR_KEY, color);
        preferencesEditor.apply();
    }
}