package id.ac.umn.w09_28917.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import id.ac.umn.w09_28917.R;
import id.ac.umn.w09_28917.models.Mahasiswa;

public class DetailActivity extends AppCompatActivity {
    private EditText etNim, etNama, etEmail, etNomorHp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        etNim = findViewById(R.id.etNim);
        etNama = findViewById(R.id.etNama);
        etEmail = findViewById(R.id.etEmail);
        etNomorHp = findViewById(R.id.etNomorHp);

        Intent intent = getIntent();
        if (intent.hasExtra("MAHASISWA")) {
            Mahasiswa mhs = (Mahasiswa) intent.getSerializableExtra("MAHASISWA");
            etNim.setText(mhs.getNim());
            etNama.setText(mhs.getNama());
            etEmail.setText(mhs.getEmail());
            etNomorHp.setText(mhs.getNomorhp());
            etNim.setEnabled(false);
        } else {
            etNim.setEnabled(true);
        }
    }

    public void saveData(View view) {
        String mNIM = etNim.getText().toString();
        String mNama = etNama.getText().toString();
        String mEmail = etEmail.getText().toString();
        String mNoHp = etNomorHp.getText().toString();

        if (mNIM.length() <= 0 || mNama.length() <= 0 || mEmail.length() <= 0 || mNoHp.length() <= 0) {
            Toast.makeText(this, "Semua harus diisi", Toast.LENGTH_LONG).show();
        } else {
            Intent intentResponse = new Intent();
            Mahasiswa mhs = new Mahasiswa(mNIM, mNama, mEmail, mNoHp);
            intentResponse.putExtra("MAHASISWA", mhs);
            setResult(RESULT_OK, intentResponse);
            finish();
        }
    }

    public void cancel(View view) {
        Intent intentResponse = new Intent();
        setResult(RESULT_CANCELED, intentResponse);
        finish();
    }
}