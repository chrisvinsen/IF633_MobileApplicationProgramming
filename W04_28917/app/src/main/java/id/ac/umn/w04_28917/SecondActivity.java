package id.ac.umn.w04_28917;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private TextView tvMsgReceived;
    private EditText etAnswer;
    private Button btnToFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tvMsgReceived = findViewById(R.id.tvMsgReceived);
        etAnswer = findViewById(R.id.etAnswer);
        btnToFragment = findViewById(R.id.btnToFragment);

        Intent mainIntent = getIntent();
        String msgReceived = mainIntent.getStringExtra("MsgFromMain");
        tvMsgReceived.setText(msgReceived);

        btnToFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, FragmentActivity.class);
                startActivity(intent);
            }
        });
    }

    public void sendBack(View view) {
        String answer = etAnswer.getText().toString();
        Intent replyIntent = new Intent();
        replyIntent.putExtra("Answer", answer);
        setResult(RESULT_OK, replyIntent);
        finish();
    }
}