package id.ac.umn.w07_28917;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

import java.util.LinkedList;

public class GalleryActivity extends AppCompatActivity {
    RecyclerView rvDaftarVideo;
    VideoListAdapter mAdapter;
    LinkedList<SourceVideo> daftarVideo = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        isiDaftarVideo();
        rvDaftarVideo = findViewById(R.id.recycleView);
        mAdapter = new VideoListAdapter(this, daftarVideo);
        rvDaftarVideo.setAdapter(mAdapter);
        rvDaftarVideo.setLayoutManager(new LinearLayoutManager(this));
    }

    public void isiDaftarVideo() {
        daftarVideo.add(new SourceVideo("Mini Cooper Drag", "Drag Race Mini Cooper dengan Honda Civic hatchback", "android.resource://" + getPackageName() + "/" + R.raw.mini01));
        daftarVideo.add(new SourceVideo("Porcsche 918", "Menikmati mobil sport Porsche 918 Spyder", "android.resource://" + getPackageName() + "/" + R.raw.porsche918));
        daftarVideo.add(new SourceVideo("Drift H2H", "Head to Head Drifting hasil cuplikan dari film The                   Fast and The Furious", "android.resource://" + getPackageName() + "/" + R.raw.drift01));
        daftarVideo.add(new SourceVideo("Kejar-kejaran", "Kejar-kejaran di jalanan hasil cuplikan dari film The                  Fast and The Furious", "android.resource://" + getPackageName() + "/" + R.raw.drift02));
        daftarVideo.add(new SourceVideo("Mini Cooper Drag Race", "Drag Race Mini Cooper dengan Ford Fiesta hatchback", "android.resource://" + getPackageName() + "/" + R.raw.mini02));
    }

}