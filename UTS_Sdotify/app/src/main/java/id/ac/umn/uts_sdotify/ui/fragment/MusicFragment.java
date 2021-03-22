package id.ac.umn.uts_sdotify.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;

import id.ac.umn.uts_sdotify.R;
import id.ac.umn.uts_sdotify.ui.adapter.MusicAdapter;

import static id.ac.umn.uts_sdotify.ui.activity.MainActivity.musicFiles;

public class MusicFragment extends Fragment {
    RecyclerView recyclerView;
    MusicAdapter musicAdapter;
    TextView tvGreetings;

    public MusicFragment() {
        // Required empty public constructor
    }

    public static MusicFragment newInstance(String param1, String param2) {
        MusicFragment fragment = new MusicFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_music, container, false);
        recyclerView = view.findViewById(R.id.rvMusicList);
        recyclerView.setHasFixedSize(true);
        if (musicFiles.size() >= 1) {
            musicAdapter = new MusicAdapter(getContext(), musicFiles);
            recyclerView.setAdapter(musicAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        }

        tvGreetings = view.findViewById(R.id.tvGreetings);
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        if (timeOfDay >= 0 && timeOfDay < 12) {
            tvGreetings.setText(getString(R.string.text_good_morning));
        } else if (timeOfDay >= 12 && timeOfDay < 16) {
            tvGreetings.setText(getString(R.string.text_good_afternoon));
        } else if (timeOfDay >= 16 && timeOfDay < 21) {
            tvGreetings.setText(getString(R.string.text_good_evening));
        } else if (timeOfDay >= 21 && timeOfDay < 24) {
            tvGreetings.setText(getString(R.string.text_good_night));
        }

        return view;
    }
}
