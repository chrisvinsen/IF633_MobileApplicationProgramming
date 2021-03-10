package id.ac.umn.w07_28917;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoDetailActivity extends AppCompatActivity {
    private VideoView vidDetail;
    private EditText etTitle;
    private EditText etDescription;
    private EditText etUri;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);

        vidDetail = findViewById(R.id.vidView);
        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        etUri = findViewById(R.id.etUri);
        btnBack = findViewById(R.id.btnBack);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        SourceVideo sv = (SourceVideo) bundle.getSerializable("DetailVideo");
        etTitle.setText(sv.getTitle());
        etDescription.setText(sv.getDescription());
        etUri.setText(sv.getVideoURI());
        Log.e("URI", String.valueOf(Uri.parse(sv.getVideoURI())));
        vidDetail.setVideoURI(Uri.parse(sv.getVideoURI()));
        vidDetail.seekTo(100);
        MediaController controller = new MediaController(this);
        controller.setMediaPlayer(vidDetail);
        vidDetail.setMediaController(controller);
        vidDetail.start();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}