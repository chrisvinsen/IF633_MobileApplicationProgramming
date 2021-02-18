package id.ac.umn.w04_28917;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText etText, etUrl;
    private Button btnSend, btnBrowse, btnToFragment;
    private TextView tvAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etText = findViewById(R.id.etText);
        etUrl = findViewById(R.id.etUrl);
        btnSend = findViewById(R.id.btnSend);
        btnBrowse = findViewById(R.id.btnBrowse);
        tvAnswer = findViewById(R.id.tvAnswer);
        btnToFragment = findViewById(R.id.btnToFragment);

        btnBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String urlText = etUrl.getText().toString();

                if (urlText == null || urlText.isEmpty()) {
                    etUrl.setError(getString(R.string.warningUrl));
                } else {
                    if (!urlText.startsWith("http://") && !urlText.startsWith("https://")) {
                        urlText = "http://" + urlText;
                    }
//                    urlText = "http://www.umn.ac.id/";
                    Intent browseIntent = new Intent(Intent.ACTION_VIEW);
                    browseIntent.setData(Uri.parse(urlText));
                    if (browseIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(browseIntent);
                    }
                }
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = etText.getText().toString();

                if (text == null || text.isEmpty()) {
                    etText.setError(getString(R.string.warningText));
                } else {
                    Intent secondIntent = new Intent(MainActivity.this, SecondActivity.class);

                    secondIntent.putExtra("MsgFromMain", text);
                    startActivityForResult(secondIntent, 1);
                }
            }
        });

        btnToFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FragmentActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String answer = data.getStringExtra("Answer");
                tvAnswer.setText(answer);
            }
        }
    }
}