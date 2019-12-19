package me.abir.dailycricketbd.adapter;

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

/**
 * Created by Abir on 26-Nov-17.
 */

public class FixtureAdapter extends ExpandableRecyclerViewAdapter<GroupViewHolder, ChildViewHolder> {

    public FixtureAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public GroupViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.section_header, parent,
                false);
        return new HeaderVH(view);
    }

    @Override
    public ChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_fixtures,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(ChildViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        ViewHolder viewHolder = (ViewHolder) holder;
        ResultsModelWrapper modelWrapper = ((ResultsGroupChildModel) group).getResults().get(childIndex);

        String matchStartText =
                viewHolder.ivTeamA.getContext().getString(R.string.text_match_scheduled_to_begin)
                        + " " + modelWrapper.getMatchStartTime();

        viewHolder.tvTournament.setText(CricUtil.getTitle(modelWrapper.getTournamentName()
                + ", ", modelWrapper.getDateAndMatchNo()));
        viewHolder.tvTeamA.setText(modelWrapper.getTeamA().toUpperCase());
        viewHolder.tvTeamB.setText(modelWrapper.getTeamB().toUpperCase());
        //viewHolder.tvResult.setText(modelWrapper.getMatchNotes());
        viewHolder.tvResult.setText(matchStartText);

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

    private class ViewHolder extends ChildViewHolder {
        private TextView tvTournament;
        private TextView tvTeamA;
        private TextView tvTeamB;
        private TextView tvResult;
        private ImageView ivTeamA;
        private ImageView ivTeamB;

        ViewHolder(View itemView) {
            super(itemView);
            tvTournament = itemView.findViewById(R.id.tvTournament);
            tvTeamA = itemView.findViewById(R.id.tvTeamA);
            tvTeamB = itemView.findViewById(R.id.tvTeamB);
            tvResult = itemView.findViewById(R.id.tvResult);
            ivTeamA = itemView.findViewById(R.id.ivTeamA);
            ivTeamB = itemView.findViewById(R.id.ivTeamB);
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
