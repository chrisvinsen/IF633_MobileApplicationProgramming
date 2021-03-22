package id.ac.umn.uts_sdotify.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import id.ac.umn.uts_sdotify.R;
import id.ac.umn.uts_sdotify.controller.AppController;
import id.ac.umn.uts_sdotify.ui.fragment.AboutFragment;
import id.ac.umn.uts_sdotify.ui.fragment.MusicFragment;
import id.ac.umn.uts_sdotify.object.MusicFiles;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 1;
    public static ArrayList<MusicFiles> musicFiles;
    static boolean musicShuffled = false;
    static boolean musicRepeated = false;

    BottomNavigationView bottomNavigation;
    AlertDialog.Builder builder;

    SharedPreferences sharedPreferences;
    private static final String IS_LOGGED_IN = "logged_in";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        permission();

        sharedPreferences = getSharedPreferences(IS_LOGGED_IN, MODE_PRIVATE);

        Intent intentReceived = getIntent();
        builder = new AlertDialog.Builder(this);
        String alertStatus = intentReceived.getStringExtra("AlertStatus");
        if (alertStatus != null) {
            builder.setMessage(R.string.dialog_welcome_message)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert = builder.create();
            Calendar c = Calendar.getInstance();
            int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
            if (timeOfDay >= 0 && timeOfDay < 12) {
                alert.setTitle(getString(R.string.text_good_morning));
            } else if (timeOfDay >= 12 && timeOfDay < 16) {
                alert.setTitle(getString(R.string.text_good_afternoon));
            } else if (timeOfDay >= 16 && timeOfDay < 21) {
                alert.setTitle(getString(R.string.text_good_evening));
            } else if (timeOfDay >= 21 && timeOfDay < 24) {
                alert.setTitle(getString(R.string.text_good_night));
            }
            alert.show();
        }
    }

    private void initViewPager() {
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        openFragment(MusicFragment.newInstance("", ""));
        BottomNavigationView bottomNavigationView;
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_music);
    }

    private void permission() {
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
        } else {
            musicFiles = getAllAudio(this);
            initViewPager();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                musicFiles = getAllAudio(this);
                initViewPager();
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
            }
        }
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_about:
                            openFragment(AboutFragment.newInstance("", ""));
                            return true;
                        case R.id.navigation_music:
                            openFragment(MusicFragment.newInstance("", ""));
                            return true;
                        case R.id.navigation_exit:
                            builder.setMessage(R.string.dialog_logout_message).setTitle(R.string.dialog_logout_title)
                                    .setCancelable(false)
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            PlayerActivity.stopMediaPlayer();

                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.clear();
                                            editor.commit();
                                            Toast.makeText(MainActivity.this, "Log out successfully", Toast.LENGTH_SHORT).show();
                                            finish();


                                            AppController.openWelcomeActivity(MainActivity.this);
                                        }
                                    })
                                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });

                            AlertDialog alert = builder.create();
                            alert.setTitle(R.string.dialog_logout_title);
                            alert.show();
                    }
                    return false;
                }
            };

    public static ArrayList<MusicFiles> getAllAudio(Context context) {
        ContentResolver contentResolver = context.getContentResolver();

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0";
        String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";
        Cursor cursor = contentResolver.query(uri, null, selection, null, sortOrder);

        ArrayList<MusicFiles> tempAudioList = new ArrayList<>();

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                String duration = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                String path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));

                File file = new File(path);
                if (file.exists() && file.canRead()) {
                    MusicFiles musicFiles = new MusicFiles(path, title, artist, album, duration);
                    tempAudioList.add(musicFiles);
                }
            }
            cursor.close();
        }

        return tempAudioList;
    }
}