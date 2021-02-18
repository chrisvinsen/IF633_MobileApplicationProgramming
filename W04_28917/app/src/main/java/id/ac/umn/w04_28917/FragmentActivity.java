package id.ac.umn.w04_28917;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FragmentActivity extends AppCompatActivity {
    Button btnPage1, btnPage2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        btnPage1 = findViewById(R.id.btnPage1);
        btnPage2 = findViewById(R.id.btnPage2);

        btnPage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FragmentActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });

        btnPage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FragmentActivity.this, FourthActivity.class);
                startActivity(intent);
            }
        });
    }
}