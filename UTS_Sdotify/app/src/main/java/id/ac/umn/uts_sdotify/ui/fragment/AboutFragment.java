package id.ac.umn.uts_sdotify.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import id.ac.umn.uts_sdotify.R;

public class AboutFragment extends Fragment {
    TextView tvYt1, tvYt2, tvYt3, tvYt4, tvYt5, tvYt6;
    TextView tvWeb1, tvWeb2, tvWeb3, tvWeb4, tvWeb5, tvWeb6;

    public AboutFragment() {
        // Required empty public constructor
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AboutFragment newInstance(String param1, String param2) {
        AboutFragment fragment = new AboutFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_about, container, false);

        referenceListener(root);

        return root;
    }

    private void referenceListener(View root) {
        tvYt1 = root.findViewById(R.id.tvYt1);
        tvYt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUri(getString(R.string.text_reference_yt_url_1));
            }
        });
        tvYt2 = root.findViewById(R.id.tvYt2);
        tvYt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUri(getString(R.string.text_reference_yt_url_2));
            }
        });
        tvYt3 = root.findViewById(R.id.tvYt3);
        tvYt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUri(getString(R.string.text_reference_yt_url_3));
            }
        });
        tvYt4 = root.findViewById(R.id.tvYt4);
        tvYt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUri(getString(R.string.text_reference_yt_url_4));
            }
        });
        tvYt5 = root.findViewById(R.id.tvYt5);
        tvYt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUri(getString(R.string.text_reference_yt_url_5));
            }
        });
        tvYt6 = root.findViewById(R.id.tvYt6);
        tvYt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUri(getString(R.string.text_reference_yt_url_6));
            }
        });

        tvWeb1 = root.findViewById(R.id.tvWeb1);
        tvWeb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUri(getString(R.string.text_reference_web_url_1));
            }
        });
        tvWeb2 = root.findViewById(R.id.tvWeb2);
        tvWeb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUri(getString(R.string.text_reference_web_url_2));
            }
        });
        tvWeb3 = root.findViewById(R.id.tvWeb3);
        tvWeb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUri(getString(R.string.text_reference_web_url_3));
            }
        });
        tvWeb4 = root.findViewById(R.id.tvWeb4);
        tvWeb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUri(getString(R.string.text_reference_web_url_4));
            }
        });
        tvWeb5 = root.findViewById(R.id.tvWeb5);
        tvWeb5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUri(getString(R.string.text_reference_web_url_5));
            }
        });
        tvWeb6 = root.findViewById(R.id.tvWeb6);
        tvWeb6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUri(getString(R.string.text_reference_web_url_6));
            }
        });
    }

    private void openUri(String uri) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }
}
