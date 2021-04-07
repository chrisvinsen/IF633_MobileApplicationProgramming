package id.ac.umn.w09_28917.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import id.ac.umn.w09_28917.R;
import id.ac.umn.w09_28917.adapter.MahasiswaListAdapter;
import id.ac.umn.w09_28917.models.Mahasiswa;
import id.ac.umn.w09_28917.view_model.MahasiswaViewModel;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv;
    private MahasiswaViewModel mhsVM;
    private static final int REQUEST_TAMBAH = 1;
    private static final int REQUEST_EDIT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addIntent = new Intent(MainActivity.this, DetailActivity.class);
                startActivityForResult(addIntent, REQUEST_TAMBAH);
            }
        });

        rv = findViewById(R.id.rvMahasiswa);
        final MahasiswaListAdapter adapter = new MahasiswaListAdapter(getLayoutInflater());
        rv.setAdapter(adapter);

        rv.setLayoutManager(new LinearLayoutManager(this));
        mhsVM = ViewModelProviders.of(this).get(MahasiswaViewModel.class);
        mhsVM.getDaftarMahasiswa().observe(this, new Observer<List<Mahasiswa>>() {

            @Override
            public void onChanged(List<Mahasiswa> mahasiswas) {
                adapter.setDaftarMahasiswa(mahasiswas);
            }
        });

        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getAdapterPosition();
                        Mahasiswa mhs = adapter.getMahasiswaAtPosition(position);
                        if (direction == ItemTouchHelper.LEFT) {
                            Toast.makeText(MainActivity.this, "Mahasiswa dengan NIM = " + mhs.getNim() + " dihapus", Toast.LENGTH_LONG).show();
                            mhsVM.delete(mhs);
                        } else if (direction == ItemTouchHelper.RIGHT) {
                            Intent editIntent = new Intent(MainActivity.this, DetailActivity.class);
                            editIntent.putExtra("MAHASISWA", mhs);
                            startActivityForResult(editIntent, REQUEST_EDIT);
                        }
                    }
                }
        );
        helper.attachToRecyclerView(rv);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Mahasiswa mhs = (Mahasiswa) data.getSerializableExtra("MAHASISWA");
            if (requestCode == REQUEST_TAMBAH) {
                mhsVM.insert(mhs);
            } else if (requestCode == REQUEST_EDIT) {
                mhsVM.update(mhs);
            }
        }
        rv.getAdapter().notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            mhsVM.deleteAll();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}