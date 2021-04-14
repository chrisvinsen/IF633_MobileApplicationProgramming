package id.ac.umn.w10_28917;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void GoToAsyncTaskActivity(View view) {
        final Intent intent = new Intent(MainActivity.this, AsyncTaskActivity.class);
        startActivity(intent);
    }

    public void GoToAsyncTaskLoaderActivity(View view) {
        final Intent intent = new Intent(MainActivity.this, AsyncTaskLoaderActivity.class);
        startActivity(intent);
    }

    public void GoToServiceActivity(View view) {
        final Intent intent = new Intent(MainActivity.this, ServiceActivity.class);
        startActivity(intent);
    }
}