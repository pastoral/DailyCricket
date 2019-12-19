package me.abir.dailycricketbd.adapter;

import android.content.Intent;
import androidx.core.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.List;

import me.abir.dailycricketbd.R;
import me.abir.dailycricketbd.model.results_module.ResultsGroupChildModel;
import me.abir.dailycricketbd.model.results_module.ResultsModelWrapper;
import me.abir.dailycricketbd.util.Configuration;
import me.abir.dailycricketbd.util.CricUtil;
import me.abir.dailycricketbd.view.activity.SingleMatchActivity;

/**
 * Created by Abir on 24-Nov-17.
 */

public class ResultsAdapter extends ExpandableRecyclerViewAdapter<GroupViewHolder, ChildViewHolder> {
    private boolean isLive;

    public ResultsAdapter(List<? extends ExpandableGroup> groups, boolean isLive) {
        super(groups);
        this.isLive = isLive;
    }

    @Override
    public GroupViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.section_header, parent,
                false);
        return new HeaderVH(view);
    }

    @Override
    public ChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_results, parent,
                false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(ChildViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        ViewHolder viewHolder = (ViewHolder) holder;
        ResultsModelWrapper modelWrapper = ((ResultsGroupChildModel) group).getResults().get(childIndex);
        viewHolder.setModel(modelWrapper);

        viewHolder.tvTournament.setText(CricUtil.getTitle(modelWrapper.getTournamentName()
                + ", ", modelWrapper.getDateAndMatchNo()));
        viewHolder.tvTeamA.setText(modelWrapper.getTeamA().toUpperCase());
        viewHolder.tvTeamB.setText(modelWrapper.getTeamB().toUpperCase());
        if (isLive){
            viewHolder.tvTeamAScore.setText(modelWrapper.getTeamAScoreFull());
            viewHolder.tvTeanBScore.setText(modelWrapper.getTeamBScoreFull());
        }else {
            viewHolder.tvTeamAScore.setText(modelWrapper.getTeamAScore());
            viewHolder.tvTeanBScore.setText(modelWrapper.getTeamBScore());
        }
        viewHolder.tvResult.setText(modelWrapper.getMatchNotes());
        String matchStatus = modelWrapper.getMatchStatus().toUpperCase();
        viewHolder.tvMatchStatus.setText(matchStatus);
        switch (matchStatus) {
            case "LIVE":
                viewHolder.tvMatchStatus.setTextColor(
                        ContextCompat.getColor(
                                viewHolder.tvMatchStatus.getContext(), R.color.colorPrimary
                        )
                );
                break;
            case "COMPLETED":
                viewHolder.tvMatchStatus.setTextColor(
                        ContextCompat.getColor(
                                viewHolder.tvMatchStatus.getContext(), R.color.gray_dark
                        )
                );
                break;
            default:
                break;
        }


        String teamALogo = modelWrapper.getTeamAUrl();
        if (teamALogo != null && teamALogo.trim().length() != 0) {
            Picasso.with(viewHolder.ivTeamA.getContext())
                    .load(teamALogo)
                    .fit()
                    .placeholder(R.mipmap.app_icon)
                    .error(R.mipmap.app_icon)
                    .into(viewHolder.ivTeamA);
        } else {
            viewHolder.ivTeamA.setImageResource(R.mipmap.app_icon);
        }

        String teamBLogo = modelWrapper.getTeamBUrl();
        if (teamBLogo != null && teamBLogo.trim().length() != 0) {
            Picasso.with(viewHolder.ivTeamB.getContext())
                    .load(teamBLogo)
                    .fit()
                    .placeholder(R.mipmap.app_icon)
                    .error(R.mipmap.app_icon)
                    .into(viewHolder.ivTeamB);
        } else {
            viewHolder.ivTeamB.setImageResource(R.mipmap.app_icon);
        }
    }

    @Override
    public void onBindGroupViewHolder(GroupViewHolder holder, int flatPosition, ExpandableGroup group) {
        HeaderVH headerVH = (HeaderVH) holder;
        String headerValue;
        if ((((ResultsGroupChildModel) group).getHeaderValue()).equals(Configuration.TAG_DOMESTIC)) {
            headerValue = headerVH.tvHeader.getContext().getResources().getString(R.string.text_domestic);
        } else {
            headerValue = headerVH.tvHeader.getContext().getResources().getString(R.string.text_international);
        }
        headerVH.tvHeader.setText(headerValue);
    }

    private class ViewHolder extends ChildViewHolder implements View.OnClickListener {
        private TextView tvTournament;
        private TextView tvTeamA;
        private TextView tvTeamB;
        private TextView tvTeanBScore;
        private TextView tvTeamAScore;
        private TextView tvResult;
        private TextView tvMatchStatus;
        private ImageView ivTeamA;
        private ImageView ivTeamB;
        private ResultsModelWrapper modelWrapper;

        ViewHolder(View itemView) {
            super(itemView);
            tvTournament = itemView.findViewById(R.id.tvTournament);
            tvTeamA = itemView.findViewById(R.id.tvTeamA);
            tvTeamB = itemView.findViewById(R.id.tvTeamB);
            tvTeanBScore = itemView.findViewById(R.id.tvTeanBScore);
            tvTeamAScore = itemView.findViewById(R.id.tvTeamAScore);
            tvMatchStatus = itemView.findViewById(R.id.tvMatchStatus);
            tvResult = itemView.findViewById(R.id.tvResult);
            ivTeamA = itemView.findViewById(R.id.ivTeamA);
            ivTeamB = itemView.findViewById(R.id.ivTeamB);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), SingleMatchActivity.class);
            intent.putExtra(Configuration.KEY_MATCH_INFO_ARG, modelWrapper);
            intent.putExtra(Configuration.KEY_MATCH_IS_LIVE, isLive);
            view.getContext().startActivity(intent);
        }

        void setModel(ResultsModelWrapper modelWrapper) {
            this.modelWrapper = modelWrapper;
        }
    }


    private class HeaderVH extends GroupViewHolder {
        private TextView tvHeader;

        public HeaderVH(View itemView) {
            super(itemView);
            tvHeader = itemView.findViewById(R.id.tvHeader);
        }
    }
}
