package me.abir.dailycricketbd.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.abir.dailycricketbd.R;
import me.abir.dailycricketbd.adapter.viewholder.BallVH;
import me.abir.dailycricketbd.adapter.viewholder.BatsmenVH;
import me.abir.dailycricketbd.adapter.viewholder.BowlerVH;
import me.abir.dailycricketbd.adapter.viewholder.InningsStatusVH;
import me.abir.dailycricketbd.adapter.viewholder.OverEndVH;
import me.abir.dailycricketbd.model.single_live_match.Commentary;
import me.abir.dailycricketbd.model.single_live_match.InningsStatusModel;
import me.abir.dailycricketbd.model.single_match_scorecard.ScoreCardWrapperBatsmen;
import me.abir.dailycricketbd.model.single_match_scorecard.ScoreCardWrapperBowler;

public class LiveScoreDetailsAdapter extends RecyclerView.Adapter {

    private List<Object> details = new ArrayList<>();
    private static final int VIEW_BALL = 1;
    private static final int VIEW_OVER_END = 2;
    private static final int VIEW_BATSMAN = 3;
    private static final int VIEW_BOWLER = 4;
    private static final int VIEW_INNINGS_STATUS = 5;

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
        } else if (viewType == VIEW_BATSMAN) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_score_card_batsmen, parent, false);
            return new BatsmenVH(view);
        } else if (viewType == VIEW_BOWLER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_score_card_batsmen, parent, false);
            return new BowlerVH(view);
        } else if (viewType == VIEW_INNINGS_STATUS) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_row_innings_status, parent, false);
            return new InningsStatusVH(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Object object = details.get(position);
        if (holder instanceof BallVH) {
            BallVH ballVH = (BallVH) holder;
            ballVH.setUI((Commentary) object);
        } else if (holder instanceof OverEndVH) {
            OverEndVH endVH = (OverEndVH) holder;
            endVH.setUI((Commentary) object);
        } else if (holder instanceof BatsmenVH) {
            ((BatsmenVH) holder).onBind((ScoreCardWrapperBatsmen) object, position);
        } else if (holder instanceof BowlerVH) {
            ((BowlerVH) holder).onBind((ScoreCardWrapperBowler) object, position);
        } else if (holder instanceof InningsStatusVH) {
            ((InningsStatusVH) holder).onBind((InningsStatusModel) object);
        }
    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    @Override
    public int getItemViewType(int position) {
        Object object = details.get(position);
        if (object instanceof Commentary) {
            if (((Commentary) object).getEvent().equals("overend")) {
                return VIEW_OVER_END;
            }
            return VIEW_BALL;
        } else if (object instanceof ScoreCardWrapperBatsmen) {
            return VIEW_BATSMAN;
        } else if (object instanceof ScoreCardWrapperBowler) {
            return VIEW_BOWLER;
        } else if (object instanceof InningsStatusModel) {
            return VIEW_INNINGS_STATUS;
        }
        return 0;
    }

    public void setDetailsListData(List<Object> objects) {
        details.clear();
        details.addAll(objects);
        notifyDataSetChanged();
    }
}
