package id.ac.umn.w07_28917;

        import androidx.annotation.Nullable;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.net.Uri;
        import android.os.Bundle;
        import android.provider.MediaStore;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.VideoView;

public class CameraActivity extends AppCompatActivity {
    private Button btnImg, btnVid;
    private ImageView imgView;
    private VideoView vidView;

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_VIDEO_CAPTURE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        btnImg = findViewById(R.id.btnImg);
        btnVid = findViewById(R.id.btnVid);
        imgView = findViewById(R.id.imgView);
        vidView = findViewById(R.id.vidView);

        btnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

        btnVid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imgBitmap = (Bitmap) extras.get("data");
            imgView.setImageBitmap(imgBitmap);
        } else if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Uri vidUri = data.getData();
            vidView.setVideoURI(vidUri);
            vidView.seekTo(100);
            vidView.start();
        }
    }
}