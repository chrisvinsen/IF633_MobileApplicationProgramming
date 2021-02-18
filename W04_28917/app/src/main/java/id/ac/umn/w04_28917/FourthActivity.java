package id.ac.umn.w04_28917;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class FourthActivity extends AppCompatActivity implements FirstFragment.FirstFragmentListener, SecondFragment.SecondFragmentListener {
    private FirstFragment fmtOne;
    private SecondFragment fmtTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        FragmentManager fmtManager = getSupportFragmentManager();
        FragmentTransaction fmtTransaction = fmtManager.beginTransaction();

        fmtOne = new FirstFragment();
        fmtTransaction.replace(R.id.fmtOne, fmtOne);

        fmtTwo = new SecondFragment();
        fmtTransaction.replace(R.id.fmtTwo, fmtTwo);

        fmtTransaction.commit();
    }

    @Override
    public void onInputSent(CharSequence input) {
        fmtTwo.updateEditText(input);
    }
}