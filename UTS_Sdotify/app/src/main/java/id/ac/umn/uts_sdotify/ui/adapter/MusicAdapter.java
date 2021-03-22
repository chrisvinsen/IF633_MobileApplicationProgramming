package id.ac.umn.uts_sdotify.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.ac.umn.uts_sdotify.R;
import id.ac.umn.uts_sdotify.object.MusicFiles;
import id.ac.umn.uts_sdotify.ui.activity.PlayerActivity;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<MusicFiles> mFiles;

    public MusicAdapter(Context mContext, ArrayList<MusicFiles> mFiles) {
        this.mFiles = mFiles;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.music_items, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.file_name.setText(mFiles.get(position).getTitle());
        if (!mFiles.get(position).getPath().isEmpty()) {
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            retriever.setDataSource(mFiles.get(position).getPath());
            byte[] art = retriever.getEmbeddedPicture();
            if (art != null) {
                Glide.with(mContext)
                        .asBitmap()
                        .load(art)
                        .into(holder.album_art);
            } else {
                Log.e("NULL", "NULL");
                Glide.with(mContext)
                        .load(R.drawable.bg_default_music_art)
                        .into(holder.album_art);
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, PlayerActivity.class);
                intent.putExtra("position", position);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFiles.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView file_name;
        ImageView album_art;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            file_name = itemView.findViewById(R.id.tvTitle);
            album_art = itemView.findViewById(R.id.imgMusic);
        }
    }

    private Drawable getAlbumArt(String uri) {
        Drawable image = Drawable.createFromPath(uri);

        return image;
    }
}
