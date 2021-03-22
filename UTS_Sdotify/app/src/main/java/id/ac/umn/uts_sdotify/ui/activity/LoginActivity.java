package id.ac.umn.uts_sdotify.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import id.ac.umn.uts_sdotify.R;
import id.ac.umn.uts_sdotify.controller.AppController;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private Button btnLogin;
    private TextView tvWarning;
    SharedPreferences sharedPreferences;
    private static final String IS_LOGGED_IN = "logged_in";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences(IS_LOGGED_IN, MODE_PRIVATE);
        String loginStatus = sharedPreferences.getString(IS_LOGGED_IN, null);
         if (loginStatus != null) {
             AppController.openMainActivity(LoginActivity.this, true);
         }

        initializeViewVariable();
        initializeViewListener();
    }

    private void initializeViewVariable() {
        btnLogin = findViewById(R.id.btnLogin);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        tvWarning = findViewById(R.id.tvWarning);
    }

    private void initializeViewListener() {
        etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                onKeyListener();
            }
        });

        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                onKeyListener();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String uname = etUsername.getText().toString();
                final String pass = etPassword.getText().toString();

                if (uname.isEmpty() || pass.isEmpty()) {
                    btnLogin.setEnabled(false);
                }
                if (uname.equals("uasmobile") && pass.equals("uasmobilegenap")) {
                    tvWarning.setText("");

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(IS_LOGGED_IN, "true");
                    editor.apply();

                    AppController.openMainActivity(LoginActivity.this, true);

                    Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                } else {
                    tvWarning.setText(R.string.text_login_warning);

                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void onKeyListener() {
        String uname = etUsername.getText().toString();
        String pass = etPassword.getText().toString();

        if (uname.isEmpty() || pass.isEmpty()) {
            btnLogin.setEnabled(false);
        } else {
            btnLogin.setEnabled(true);
        }
    }
}