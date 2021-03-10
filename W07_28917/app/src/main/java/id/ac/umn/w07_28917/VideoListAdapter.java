package id.ac.umn.w07_28917;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.ItemVideoViewHolder> {
    private LinkedList<SourceVideo> mVideoList;
    private LayoutInflater mInflater;
    private Context mContext;

    public VideoListAdapter(Context mContext, LinkedList<SourceVideo> mVideoList) {
        this.mContext = mContext;
        this.mVideoList = mVideoList;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ItemVideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.video_item_layout, parent, false);
        return new ItemVideoViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemVideoViewHolder holder, int position) {
        SourceVideo mSourceVideo = mVideoList.get(position);
        holder.tvTitle.setText(mSourceVideo.getTitle());
        holder.tvDescription.setText(mSourceVideo.getDescription());
        holder.tvUri.setText(mSourceVideo.getVideoURI());
        holder.vidView.setVideoURI(Uri.parse(mSourceVideo.getVideoURI()));
        holder.vidView.seekTo(100);
    }

    @Override
    public int getItemCount() {
        return mVideoList.size();
    }

    protected class ItemVideoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private VideoView vidView;
        private TextView tvTitle;
        private TextView tvDescription;
        private TextView tvUri;
        private VideoListAdapter mAdapter;
        private int mPosition;
        private SourceVideo mSourceVideo;
        public ItemVideoViewHolder(@NonNull View itemView, VideoListAdapter adapter) {
            super(itemView);
            mAdapter = adapter;
            vidView = itemView.findViewById(R.id.vidView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvUri = itemView.findViewById(R.id.tvUri);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mPosition = getLayoutPosition();
            mSourceVideo = mVideoList.get(mPosition);
            Intent intent = new Intent(mContext, VideoDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("DetailVideo", mSourceVideo);
            intent.putExtras(bundle);
            mContext.startActivity(intent);
        }
    }
}
