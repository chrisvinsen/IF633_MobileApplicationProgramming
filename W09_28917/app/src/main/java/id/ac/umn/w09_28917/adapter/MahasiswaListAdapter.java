package id.ac.umn.w09_28917.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.umn.w09_28917.R;
import id.ac.umn.w09_28917.models.Mahasiswa;

public class MahasiswaListAdapter extends RecyclerView.Adapter<MahasiswaListAdapter.MahasiswaViewHolder> {
    private final LayoutInflater mInflater;
    private List<Mahasiswa> daftarMahasiswa;

    public MahasiswaListAdapter(LayoutInflater mInflater) {
        this.mInflater = mInflater;
    }

    @NonNull
    @Override
    public MahasiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_mahasiswa, parent, false);
        return new MahasiswaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaViewHolder holder, int position) {
        if (daftarMahasiswa != null) {
            Mahasiswa mhs = daftarMahasiswa.get(position);
            holder.tvNim.setText(mhs.getNim());
            holder.tvNama.setText(mhs.getNama());
        } else {
            holder.tvNim.setText("Belum ada Mahasiswa");
        }
    }

    @Override
    public int getItemCount() {
        if (daftarMahasiswa != null) {
            return daftarMahasiswa.size();
        } else {
            return 0;
        }
    }

    public Mahasiswa getMahasiswaAtPosition(int position) {
        return daftarMahasiswa.get(position);
    }

    public void setDaftarMahasiswa(List<Mahasiswa> mhss) {
        daftarMahasiswa = mhss;
        notifyDataSetChanged();
    }

    class MahasiswaViewHolder extends RecyclerView.ViewHolder{
        private final TextView tvNim, tvNama;

        public MahasiswaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNim = itemView.findViewById(R.id.tvItemNim);
            tvNama = itemView.findViewById(R.id.tvItemNama);
        }
    }
}
