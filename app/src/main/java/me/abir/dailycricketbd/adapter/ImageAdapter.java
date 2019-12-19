package me.abir.dailycricketbd.adapter;

import android.content.Intent;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import me.abir.dailycricketbd.R;
import me.abir.dailycricketbd.model.image_module.WrapperImageModel;
import me.abir.dailycricketbd.model.image_module_v2.Photo;
import me.abir.dailycricketbd.util.Configuration;
import me.abir.dailycricketbd.view.activity.FullImageActivity;

/**
 * Created by Abir on 01-Nov-17.
 */

public class ImageAdapter extends RecyclerView.Adapter {

    private List<Object> imageList = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_image_single, parent, false);
            viewHolder = new SingleImageViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_image_double, parent, false);
            viewHolder = new DoubleImageViewHolder(view);

        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 0) {
            setView((SingleImageViewHolder) holder, position);
        } else {
            setView((DoubleImageViewHolder) holder, position);

        }
    }

    private void setView(SingleImageViewHolder holder, int position) {
        Photo imageObject = (Photo) imageList.get(position);

        holder.tvMainTitle.setText(imageObject.getTitle());
        holder.tvMainTag.setText(imageObject.getCategory().getName());
        holder.tvMainTime.setText(imageObject.getCreatedAt());
        Picasso.with(holder.ivMainImage.getContext())
                .load(imageObject.getUrl())
                .fit()
                .placeholder(R.drawable.ban_vs_rsa)
                .error(R.drawable.ban_vs_rsa)
                .into(holder.ivMainImage);
    }

    private void setView(DoubleImageViewHolder holder, int position) {
        WrapperImageModel imageModel = (WrapperImageModel) imageList.get(position);

        Photo firstImageObject = imageModel.getFirstImageModel();
        Photo secondImageObject = imageModel.getSecondImageModel();

        if (firstImageObject != null) {
            holder.tvFirstTitle.setText(firstImageObject.getTitle());
            holder.tvFirstTag.setText(firstImageObject.getCategory().getName());
            holder.tvFirstTime.setText(firstImageObject.getCreatedAt());
            Picasso.with(holder.ivFirstMainImage.getContext())
                    .load(firstImageObject.getUrl())
                    .fit()
                    .placeholder(R.mipmap.app_icon)
                    .error(R.mipmap.app_icon)
                    .into(holder.ivFirstMainImage);
        }

        if (secondImageObject != null) {
            holder.rlSecondView.setVisibility(View.VISIBLE);
            holder.tvSecondTitle.setText(secondImageObject.getTitle());
            holder.tvSecondTag.setText(secondImageObject.getCategory().getName());
            holder.tvSecondTime.setText(secondImageObject.getCreatedAt());
            Picasso.with(holder.ivSecondMainImage.getContext())
                    .load(secondImageObject.getUrl())
                    .fit()
                    .placeholder(R.mipmap.app_icon)
                    .error(R.mipmap.app_icon)
                    .into(holder.ivSecondMainImage);
        } else {
            holder.rlSecondView.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    @Override
    public int getItemViewType(int position) {
        /*if (position == 0) {
            return 0;
        }*/
        return 0;
    }

    private class SingleImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView rlSingleBox;
        ImageView ivMainImage;
        TextView tvMainTag;
        TextView tvMainTitle;
        TextView tvMainTime;

        public SingleImageViewHolder(View itemView) {
            super(itemView);
            ivMainImage = itemView.findViewById(R.id.ivMainImage);
            tvMainTitle = itemView.findViewById(R.id.tvMainTitle);
            tvMainTag = itemView.findViewById(R.id.tvMainTag);
            tvMainTime = itemView.findViewById(R.id.tvMainTime);
            rlSingleBox = itemView.findViewById(R.id.rlSingleBox);
            rlSingleBox.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            showFullImage(v, (Photo) imageList.get(getLayoutPosition()));
        }
    }

    private class DoubleImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivFirstMainImage;
        TextView tvFirstTag;
        TextView tvFirstTitle;
        TextView tvFirstTime;
        CardView rlFirstBox;

        ImageView ivSecondMainImage;
        TextView tvSecondTag;
        TextView tvSecondTitle;
        TextView tvSecondTime;
        CardView rlSecondView;

        public DoubleImageViewHolder(View itemView) {
            super(itemView);
            ivFirstMainImage = itemView.findViewById(R.id.ivFirstMainImage);
            tvFirstTag = itemView.findViewById(R.id.tvFirstTag);
            tvFirstTitle = itemView.findViewById(R.id.tvFirstTitle);
            tvFirstTime = itemView.findViewById(R.id.tvFirstTime);
            rlFirstBox = itemView.findViewById(R.id.rlFirstBox);
            rlFirstBox.setOnClickListener(this);

            ivSecondMainImage = itemView.findViewById(R.id.ivSecondMainImage);
            tvSecondTag = itemView.findViewById(R.id.tvSecondTag);
            tvSecondTitle = itemView.findViewById(R.id.tvSecondTitle);
            tvSecondTime = itemView.findViewById(R.id.tvSecondTime);
            rlSecondView = itemView.findViewById(R.id.rlSecondView);
            rlSecondView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = getLayoutPosition();
            WrapperImageModel imageModel = (WrapperImageModel) imageList.get(pos);
            switch (v.getId()) {
                case R.id.rlFirstBox:
                    showFullImage(v, imageModel.getFirstImageModel());
                    break;
                case R.id.rlSecondView:
                    showFullImage(v, imageModel.getSecondImageModel());
                    break;
            }
        }
    }

    public void setData(List<Object> imageList) {
        this.imageList = imageList;
        notifyDataSetChanged();
    }

    private void showFullImage(View view, Photo object) {
        Intent intent = new Intent(view.getContext(), FullImageActivity.class);
        intent.putExtra(Configuration.TAG_IMAGE_MODEL, object);
        view.getContext().startActivity(intent);
    }
}
