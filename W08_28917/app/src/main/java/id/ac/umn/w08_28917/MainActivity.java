package id.ac.umn.w08_28917;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import id.ac.umn.w08_28917.first_tutorial.FirstTutorialActivity;
import id.ac.umn.w08_28917.second_tutorial.SecondTutorialActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnTutorial1OnClicked(View view) {
        Intent intent = new Intent(this, FirstTutorialActivity.class);
        Button btn = (Button) view;
        intent.putExtra("Title", btn.getText().toString());
        startActivity(intent);
    }

    public void btnTutorial2OnClicked(View view) {
        Intent intent = new Intent(this, SecondTutorialActivity.class);
        Button btn = (Button) view;
        intent.putExtra("Title", btn.getText().toString());
        startActivity(intent);
    }
}