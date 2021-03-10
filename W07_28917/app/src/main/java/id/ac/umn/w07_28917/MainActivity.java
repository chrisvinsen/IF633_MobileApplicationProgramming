package id.ac.umn.w07_28917;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openCameraActivity(View v) {
        final Intent intent = new Intent(this, CameraActivity.class);
        this.startActivity(intent);
    }

    public void openGalleryActivity(View v) {
        final Intent intent = new Intent(this, GalleryActivity.class);
        this.startActivity(intent);
    }
}