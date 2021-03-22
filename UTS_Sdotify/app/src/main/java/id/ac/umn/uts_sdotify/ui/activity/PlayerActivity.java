package id.ac.umn.uts_sdotify.ui.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.PorterDuff;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Random;

import id.ac.umn.uts_sdotify.R;
import id.ac.umn.uts_sdotify.controller.AppController;
import id.ac.umn.uts_sdotify.object.MusicFiles;

import static id.ac.umn.uts_sdotify.ui.activity.MainActivity.musicFiles;
import static id.ac.umn.uts_sdotify.ui.activity.MainActivity.musicRepeated;
import static id.ac.umn.uts_sdotify.ui.activity.MainActivity.musicShuffled;

public class PlayerActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener {
    TextView tvTitle, tvArtist, tvDurationPlayed, tvDurationTotal;
    ImageView imgCoverArt, imgNext, imgPrev, imgBack, imgShuffle, imgRepeat;
    FloatingActionButton fabPlayPause;
    SeekBar seekBar;

    int position = -1;
    static ArrayList<MusicFiles> listSongs = new ArrayList<>();
    static Uri uri;
    static MediaPlayer mediaPlayer;
    private Handler handler = new Handler();
    private Thread playThread, prevThread, nextThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        initViews();
        getIntentMethod();
        tvTitle.setText(listSongs.get(position).getTitle());
        tvArtist.setText(listSongs.get(position).getArtist());

        mediaPlayer.setOnCompletionListener(this);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (mediaPlayer != null && b) {
                    mediaPlayer.seekTo(i * 1000);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        PlayerActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    int mCurrentPosition = mediaPlayer.getCurrentPosition() / 1000;
                    seekBar.setProgress(mCurrentPosition);
                    tvDurationPlayed.setText(formattedTime(mCurrentPosition));
                    tvDurationTotal.setText(formattedTime(mediaPlayer.getDuration() / 1000));
                }
                handler.postDelayed(this, 1000);
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppController.openMainActivity(PlayerActivity.this, false);
            }
        });

        imgShuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (musicShuffled) {
                    musicShuffled = false;
                    imgShuffle.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.gray), PorterDuff.Mode.SRC_IN);
                } else {
                    musicShuffled = true;
                    imgShuffle.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.green_var), PorterDuff.Mode.SRC_IN);
                    Toast.makeText(PlayerActivity.this, "Music shuffled", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imgRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (musicRepeated) {
                    musicRepeated = false;
                    imgRepeat.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.gray), PorterDuff.Mode.SRC_IN);
                } else {
                    musicRepeated = true;
                    imgRepeat.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.green_var), PorterDuff.Mode.SRC_IN);
                    Toast.makeText(PlayerActivity.this, "Music repeated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initViews() {
        tvTitle = findViewById(R.id.tvTitle);
        tvArtist = findViewById(R.id.tvArtist);
        tvDurationPlayed = findViewById(R.id.tvDurationPlayed);
        tvDurationTotal = findViewById(R.id.tvDurationTotal);
        imgCoverArt = findViewById(R.id.imgCoverArt);
        imgNext = findViewById(R.id.imgNext);
        imgPrev = findViewById(R.id.imgPrev);
        imgShuffle = findViewById(R.id.imgShuffle);
        imgRepeat = findViewById(R.id.imgRepeat);
        imgBack = findViewById(R.id.imgBack);
        fabPlayPause = findViewById(R.id.fabPlayPause);
        seekBar = findViewById(R.id.seekBar);
    }

    private void getIntentMethod() {
        position = getIntent().getIntExtra("position", -1);
        listSongs = musicFiles;
        if (listSongs != null) {
            fabPlayPause.setImageResource(R.drawable.ic_pause);
            uri = Uri.parse(listSongs.get(position).getPath());
        }

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();

            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
            mediaPlayer.start();
        } else {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
            mediaPlayer.start();
        }

        seekBar.setMax(mediaPlayer.getDuration() / 1000);
        metaData(uri);
    }

    private String formattedTime(int mCurrentPosition) {
        String totalOut = "";
        String totalNew = "";
        String seconds = String.valueOf(mCurrentPosition % 60);
        String minutes = String.valueOf(mCurrentPosition / 60);
        totalOut = minutes + ":" + seconds;
        totalNew = minutes + ":" + "0" + seconds;
        if (seconds.length() == 1) {
            return totalNew;
        } else {
            return totalOut;
        }
    }
    
    private void metaData(Uri uri) {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(String.valueOf(uri));
        byte[] art = retriever.getEmbeddedPicture();
        if (art != null) {
            ImageAnimation(this, imgCoverArt, art);
        } else {
            ImageAnimation(this, imgCoverArt, null);
        }
    }

    @Override
    protected void onResume() {
        playThreadButton();
        nextThreadButton();
        prevThreadButton();
        super.onResume();
    }

    private void prevThreadButton() {
        prevThread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                imgPrev.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        prevButtonClicked();
                    }
                });
            }
        };

        prevThread.start();
    }

    private void nextThreadButton() {
        nextThread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                imgNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        nextButtonClicked();
                    }
                });
            }
        };

        nextThread.start();
    }

    private void playThreadButton() {
        playThread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                fabPlayPause.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        playPauseButtonClicked();
                    }
                });
            }
        };

        playThread.start();
    }

    private void playPauseButtonClicked() {
        if (mediaPlayer.isPlaying()) {
            // Will be paused
            fabPlayPause.setImageResource(R.drawable.ic_play);
            mediaPlayer.pause();
        } else {
            // Will be played
            fabPlayPause.setImageResource(R.drawable.ic_pause);
            mediaPlayer.start();
        }

        PlayerActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    int mCurrentPosition = mediaPlayer.getCurrentPosition() / 1000;
                    seekBar.setProgress(mCurrentPosition);
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    private void prevButtonClicked() {
        if (musicShuffled && !musicRepeated) {
            position = getRandom(listSongs.size());
        } else if (!musicShuffled && !musicRepeated) {
            position = ((position - 1) < 0 ? (listSongs.size() - 1) : (position - 1) );
        }

        bothNextPrevButtonClicked(mediaPlayer.isPlaying());
    }

    private void nextButtonClicked() {
        if (musicShuffled && !musicRepeated) {
            position = getRandom(listSongs.size());
        } else if (!musicShuffled && !musicRepeated) {
            position = ((position + 1) % listSongs.size());
        }

        bothNextPrevButtonClicked(mediaPlayer.isPlaying());
    }

    private int getRandom(int i) {
        Random random = new Random();

        return random.nextInt(i); // Random
    }

    private void bothNextPrevButtonClicked(boolean isPlaying) {
        if (isPlaying) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }

        uri = Uri.parse(listSongs.get(position).getPath());
        mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
        metaData(uri);

        tvTitle.setText(listSongs.get(position).getTitle());
        tvArtist.setText(listSongs.get(position).getArtist());

        seekBar.setMax(mediaPlayer.getDuration() / 1000);
        PlayerActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    int mCurrentPosition = mediaPlayer.getCurrentPosition() / 1000;
                    seekBar.setProgress(mCurrentPosition);
                }
                handler.postDelayed(this, 1000);
            }
        });

        mediaPlayer.setOnCompletionListener(this);
        if (isPlaying) {
            mediaPlayer.start();
            fabPlayPause.setImageResource(R.drawable.ic_pause);
        } else {
            fabPlayPause.setImageResource(R.drawable.ic_play);
        }
    }

    public void ImageAnimation(Context context, ImageView imgView, @Nullable byte[] art) {
        Animation animOut = AnimationUtils.loadAnimation(context, android.R.anim.fade_out);
        Animation animIn = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);

        animOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (art != null) {
                    Glide.with(context).asBitmap().load(art).into(imgView);
                } else {
                    Glide.with(context).load(R.drawable.bg_default_music_art).into(imgView);
                }
                animIn.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) { }

                    @Override
                    public void onAnimationEnd(Animation animation) { }

                    @Override
                    public void onAnimationRepeat(Animation animation) { }
                });

                imgView.startAnimation(animIn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });

        imgView.startAnimation(animOut);
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        if (musicShuffled && !musicRepeated) {
            position = getRandom(listSongs.size());
        } else if (!musicShuffled && !musicRepeated) {
            position = ((position + 1) % listSongs.size());
        }

        bothNextPrevButtonClicked(true);
    }

    public static void stopMediaPlayer() {
        mediaPlayer.pause();
    }
}