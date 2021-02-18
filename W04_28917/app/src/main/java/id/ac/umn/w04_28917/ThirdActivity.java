package id.ac.umn.w04_28917;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ThirdActivity extends AppCompatActivity implements FirstFragment.FirstFragmentListener, SecondFragment.SecondFragmentListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
    }

    @Override
    public void onInputSent(CharSequence input) {
        // Fragment yang ditambahkan secara statis lewat file XML tidak dapat diubah menggunakan Fragment Transaction
        // Kita hanya dapat mengubah fragment yang dibuat secara dinamis seperti pada FourthActivity
    }
}