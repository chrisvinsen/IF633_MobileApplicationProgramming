package id.ac.umn.w05_28917;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    private SeekBar sbRed, sbGreen, sbBlue;
    private RadioGroup rgShape;
    private RadioButton rbChosen;
    private ImageButton btnColor;

    private CustomView customView;
    private int red = 0;
    private int green = 0;
    private int blue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Seek Bar
        sbRed = findViewById(R.id.sbRed);
        sbRed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) { }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                red = sbRed.getProgress();
                customView.changeColor(red, green, blue);
                btnColor.setBackgroundColor(Color.rgb(red, green, blue));
            }
        });
        sbGreen = findViewById(R.id.sbGreen);
        sbGreen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) { }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                green = sbGreen.getProgress();
                customView.changeColor(red, green, blue);
                btnColor.setBackgroundColor(Color.rgb(red, green, blue));
            }
        });
        sbBlue = findViewById(R.id.sbBlue);
        sbBlue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) { }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                blue = sbBlue.getProgress();
                customView.changeColor(red, green, blue);
                btnColor.setBackgroundColor(Color.rgb(red, green, blue));
            }
        });
        // Button Color
        btnColor = findViewById(R.id.btnColor);

        // Custom View
        customView = findViewById(R.id.customView);
        customView = new CustomView(this);

        // Radio Group
        rgShape = findViewById(R.id.rgShape);
        rgShape.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int intRb = rgShape.getCheckedRadioButtonId();
                rbChosen = findViewById(intRb);
                String shape = rbChosen.getText().toString();
                customView.changeShape(shape);
            }
        });
    }
}