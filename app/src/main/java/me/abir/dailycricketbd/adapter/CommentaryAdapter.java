package me.abir.dailycricketbd.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.abir.dailycricketbd.R;
import me.abir.dailycricketbd.adapter.viewholder.BallVH;
import me.abir.dailycricketbd.adapter.viewholder.OverEndVH;
import me.abir.dailycricketbd.model.single_live_match.Commentary;

public class CommentaryAdapter extends RecyclerView.Adapter {
    private static final String TAG = "CommentaryAdapter";
    private static final int VIEW_BALL = 1;
    private static final int VIEW_OVER_END = 2;
    private List<Commentary> commentaries = new ArrayList<>();


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_BALL) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.adapter_commentary, parent, false);
            return new BallVH(view);
        } else if (viewType == VIEW_OVER_END) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.adapter_commentary_over_end, parent, false);
            return new OverEndVH(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BallVH) {
            BallVH ballVH = (BallVH) holder;
            ballVH.setUI(commentaries.get(position));
        } else if (holder instanceof OverEndVH) {
            OverEndVH endVH = (OverEndVH) holder;
            endVH.setUI(commentaries.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return commentaries.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (commentaries.get(position).getEvent().equals("overend")) {
            return VIEW_OVER_END;
        }
        return VIEW_BALL;
    }

    public void setCommentaries(List<Commentary> commentaryList) {
        commentaries.clear();
        commentaries.addAll(commentaryList);
        notifyDataSetChanged();
    }
}
