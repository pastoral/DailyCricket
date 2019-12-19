package me.abir.dailycricketbd.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thoughtbot.expandablerecyclerview.MultiTypeExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.List;

import me.abir.dailycricketbd.R;
import me.abir.dailycricketbd.adapter.viewholder.BatsmenVH;
import me.abir.dailycricketbd.adapter.viewholder.BowlerVH;
import me.abir.dailycricketbd.adapter.viewholder.InningsNameVH;
import me.abir.dailycricketbd.adapter.viewholder.OtherVH;
import me.abir.dailycricketbd.adapter.viewholder.TotalVH;
import me.abir.dailycricketbd.model.single_match_scorecard.ScoreCardWrapperBatsmen;
import me.abir.dailycricketbd.model.single_match_scorecard.ScoreCardWrapperBowler;
import me.abir.dailycricketbd.model.single_match_scorecard.ScoreCardWrapperKeyValue;
import me.abir.dailycricketbd.model.single_match_scorecard.ScoreCardWrapperModel;
import me.abir.dailycricketbd.model.single_match_scorecard.TotalScoreWrapper;

public class ScoreCardAdapter extends MultiTypeExpandableRecyclerViewAdapter<GroupViewHolder, ChildViewHolder> {
    private static final String TAG = "ScoreCardAdapter";
    private static final int CARD_BATSMEN = 3; // type 1 and 2 are taken by the library
    private static final int CARD_BOWLER = 4;
    private static final int CARD_OTHER = 5;
    private static final int CARD_TOTAL = 6;

    public ScoreCardAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public GroupViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_score_card_title, parent, false);
        return new InningsNameVH(view);
    }

    @Override
    public ChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case CARD_BATSMEN:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_score_card_batsmen, parent, false);
                return new BatsmenVH(view);
            case CARD_BOWLER:
                View bowlerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_score_card_batsmen, parent, false);
                return new BowlerVH(bowlerView);
            case CARD_TOTAL:
                View totalView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_total_score, parent, false);
                return new TotalVH(totalView);
            case CARD_OTHER:
                View favorite =
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_score_card_other, parent, false);
                return new OtherVH(favorite);
            default:
                throw new IllegalArgumentException("Invalid viewType");
        }
    }

    @Override
    public void onBindChildViewHolder(ChildViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        int viewType = getItemViewType(flatPosition);
        Object object = ((ScoreCardWrapperModel) group).getObjects().get(childIndex);
        switch (viewType) {
            case CARD_BATSMEN:
                ((BatsmenVH) holder).onBind((ScoreCardWrapperBatsmen) object, childIndex);
                break;
            case CARD_BOWLER:
                ((BowlerVH) holder).onBind((ScoreCardWrapperBowler) object, childIndex);
                break;
            case CARD_TOTAL:
                ((TotalVH) holder).onBind((TotalScoreWrapper) object);
                break;
            case CARD_OTHER:
                ((OtherVH) holder).onBind((ScoreCardWrapperKeyValue) object);
                break;
        }
    }

    @Override
    public void onBindGroupViewHolder(GroupViewHolder holder, int flatPosition, ExpandableGroup group) {
        InningsNameVH inningsNameVH = (InningsNameVH) holder;
        inningsNameVH.setInningsTitle(group, isGroupExpanded(group));
    }

    @Override
    public int getChildViewType(int position, ExpandableGroup group, int childIndex) {
        Log.w(TAG, "getChildViewType: pos: " + position + " childPos: " + childIndex);
        if (((ScoreCardWrapperModel) group).getObjects().get(childIndex) instanceof ScoreCardWrapperBatsmen) {
            return CARD_BATSMEN;
        } else if (((ScoreCardWrapperModel) group).getObjects().get(childIndex) instanceof ScoreCardWrapperBowler) {
            return CARD_BOWLER;
        } else if (((ScoreCardWrapperModel) group).getObjects().get(childIndex) instanceof TotalScoreWrapper) {
            return CARD_TOTAL;
        } else {
            return CARD_OTHER;
        }
    }

    @Override
    public boolean isChild(int viewType) {
        return viewType == CARD_BATSMEN || viewType == CARD_BOWLER ||
                viewType == CARD_OTHER || viewType == CARD_TOTAL;
    }
}
