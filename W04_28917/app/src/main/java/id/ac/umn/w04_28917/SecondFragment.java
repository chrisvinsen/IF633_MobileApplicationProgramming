package id.ac.umn.w04_28917;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondFragment extends Fragment {
    private SecondFragment.SecondFragmentListener listener;
    private TextView tvText;

    public interface SecondFragmentListener {
        void onInputSent(CharSequence input);
    }
    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_second, container, false);
        tvText = v.findViewById(R.id.tvText);

        // Inflate the layout for this fragment
        return v;
    }

    public void updateEditText(CharSequence newText) {
        tvText.setText(newText);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SecondFragmentListener) {
            listener = (SecondFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement SecondFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}