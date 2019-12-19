package me.abir.dailycricketbd.adapter;

import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import me.abir.dailycricketbd.R;
import me.abir.dailycricketbd.model.video_module_v2.DataItem;
import me.abir.dailycricketbd.model.video_moule.WrapperVideoModel;
import me.abir.dailycricketbd.util.Configuration;
import me.abir.dailycricketbd.view.activity.VideoPlayerActivity;

/**
 * Created by Abir on 06-Nov-17.
 */

public class VideoAdapter extends RecyclerView.Adapter {
    private static final String TAG = "VideoAdapter";
    private List<WrapperVideoModel> videoModelList = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_video, parent,
                false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        WrapperVideoModel wrapperVideoModel = videoModelList.get(position);
        DataItem dataItem = wrapperVideoModel.getObject();
        final String ytVideoId = wrapperVideoModel.getYtVideoId();

        final VideoViewHolder holder = (VideoViewHolder) viewHolder;

        final String prevUrl = "https://img.youtube.com/vi/" + ytVideoId + "/sddefault.jpg";
        Log.w(TAG, "onBindViewHolder()  url:" + prevUrl);

        holder.tvMainTitle.setText(dataItem.getTitle());
        holder.tvMainTag.setText(dataItem.getCategory().getName());
        holder.tvMainTime.setText(dataItem.getCreatedAt());

        Picasso.with(holder.ivVideoPrev.getContext())
                .load(prevUrl)
                .fit()
                /*.placeholder(R.drawable.ban_vs_rsa)
                .error(R.drawable.ban_vs_rsa)*/
                .into(holder.ivVideoPrev);

        holder.ivVideoPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick() pos: " + holder.getAdapterPosition());
                Intent intent = new Intent(holder.ivVideoPrev.getContext(), VideoPlayerActivity.class);
                intent.putExtra(Configuration.VIDEO_ID_TAG, ytVideoId);

                holder.ivVideoPrev.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoModelList.size();
    }

    public void setData(List<WrapperVideoModel> videoModelList) {
        this.videoModelList = videoModelList;
        notifyDataSetChanged();
    }

    private class VideoViewHolder extends RecyclerView.ViewHolder {
        ImageView ivVideoPrev;
        ImageView ivVideoPlay;
        TextView tvMainTag;
        TextView tvMainTitle;
        TextView tvMainTime;

        public VideoViewHolder(View itemView) {
            super(itemView);
            ivVideoPrev = itemView.findViewById(R.id.ivVideoPrev);
            ivVideoPlay = itemView.findViewById(R.id.ivVideoPlay);
            tvMainTitle = itemView.findViewById(R.id.tvMainTitle);
            tvMainTag = itemView.findViewById(R.id.tvMainTag);
            tvMainTime = itemView.findViewById(R.id.tvMainTime);
        }
    }
}
