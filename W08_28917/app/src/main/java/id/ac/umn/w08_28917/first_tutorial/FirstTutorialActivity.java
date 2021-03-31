package id.ac.umn.w08_28917.first_tutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import id.ac.umn.w08_28917.R;

public class FirstTutorialActivity extends AppCompatActivity {
    private RadioGroup rgType;
    private EditText etFilename;
    private EditText etText;

    private File tempDir;
    private File localDir;
    private File extDir;
    private File currDir;

    private Context ctx;
    private Button btnOpen;
    private static PopupMenu chooseFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_tutorial);

        rgType = findViewById(R.id.rgType);
        etFilename = findViewById(R.id.etFilename);
        etText = findViewById(R.id.etText);

        tempDir = getCacheDir();
        localDir = getFilesDir();
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            extDir = getExternalFilesDir(null);
        } else {
            findViewById(R.id.rbExternal).setEnabled(false);
            extDir = null;
        }
        currDir = localDir;

        rgType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                String chosen = ((RadioButton) findViewById(rgType.getCheckedRadioButtonId())).getText().toString();
                if (chosen.equalsIgnoreCase("Temporary")) currDir = tempDir;
                else if (chosen.equalsIgnoreCase("Internal")) currDir = localDir;
                else currDir = extDir;
            }
        });

        ctx = this;
        btnOpen = findViewById(R.id.btnOpen);
        chooseFile = new PopupMenu(ctx, btnOpen);
        chooseFile.getMenuInflater().inflate(R.menu.empty_menu, chooseFile.getMenu());
        chooseFile.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                etFilename.setText(menuItem.getTitle().toString());
                readFile();
//                etFilename.setText("");
//                etText.setText("");
                return true;
            }
        });
    }

    public void onBtnOpenClicked(View view) {
        File[] files = null;
        if (currDir != null) files = currDir.listFiles();
        if (files != null && files.length > 0) {
            int n = files.length;
            chooseFile.getMenu().clear();
            for (int i = 0; i < files.length; i++) {
                chooseFile.getMenu().add(files[i].getName());
            }
            chooseFile.show();
//            readFile();
        } else {
            Toast.makeText(ctx, "Ada masalah akses folder atau folder masih kosong", Toast.LENGTH_LONG).show();
        }
    }

    public void onBtnSaveClicked(View view) {
        String filename = etFilename.getText().toString();
        String text = etText.getText().toString();
        if (filename.length() > 0 && text.length() > 0 && currDir != null) {
            File file = new File(currDir, filename);
            try {
                OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream((file)));
                writer.write(text);
                writer.close();
                Toast.makeText(this, "Teks berhasil disimpan", Toast.LENGTH_LONG).show();
            } catch (FileNotFoundException e) {
//                e.printStackTrace();
                Toast.makeText(this, "File tidak ditemukan", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
//                e.printStackTrace();
                Toast.makeText(this, "Ada kesalahan I/O", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Nama file dan teks wajib diisi", Toast.LENGTH_LONG).show();
        }
    }

    public void onBtnDeleteClicked(View view) {
        if (etFilename.getText().toString().length() > 0) {
            boolean success = false;
            if (currDir != null && currDir == localDir) {
                success = ctx.deleteFile(etFilename.getText().toString());
            } else {
                success = new File(currDir, etFilename.getText().toString()).delete();
            }

            if (success) Toast.makeText(ctx, "File berhasil dihapus", Toast.LENGTH_LONG).show();
            else Toast.makeText(ctx, "File Gagal Dihapus", Toast.LENGTH_LONG).show();

            etFilename.setText("");
            etText.setText("");
        }
    }

    public void onBtnClearClicked(View view) {
        etText.setText("");
        etFilename.setText("");
    }

    public void onBtnExitClicked(View view) {
        finishAffinity();
    }

    private void readFile() {
        if (etFilename.getText().toString().length() > 0) {
            File file = new File(currDir, etFilename.getText().toString());
            String text = "";

            try {
                InputStream inStream = new FileInputStream(file);
                if (inStream != null) {
                    InputStreamReader isReader = new InputStreamReader(inStream);
                    BufferedReader bReader = new BufferedReader(isReader);
                    String str = "";
                    StringBuilder sb = new StringBuilder();
                    while ((str = bReader.readLine()) != null) {
                        sb.append(str).append("\n");
                    }
                    inStream.close();
                    text = sb.toString();
                    etText.setText(text);
                    Toast.makeText(ctx, "File Dibaca", Toast.LENGTH_LONG).show();
                }
            } catch (FileNotFoundException e) {
//                e.printStackTrace();
                Toast.makeText(ctx, "File tidak ditemukan", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
//                e.printStackTrace();
                Toast.makeText(ctx, "Error di I/O", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        File[] tempFiles = tempDir.listFiles();
        for (File tempFile : tempFiles) {
            if (tempFile.isFile()) tempFile.delete();
        }
    }
}