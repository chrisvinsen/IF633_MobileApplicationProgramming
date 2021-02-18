package id.ac.umn.w04_28917;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FirstFragment extends Fragment {
    private FirstFragmentListener listener;
    private EditText editText;
    private Button btnSubmit;

    public interface FirstFragmentListener {
        void onInputSent(CharSequence input);
    }

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_first, container, false);
        editText = v.findViewById(R.id.editText);
        btnSubmit = v.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence input = editText.getText();
                listener.onInputSent(input);
            }
        });

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FirstFragmentListener) {
            listener = (FirstFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement FirstFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}