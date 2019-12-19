package me.abir.dailycricketbd.adapter.viewholder;

import androidx.constraintlayout.widget.ConstraintLayout;
import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import me.abir.dailycricketbd.R;
import me.abir.dailycricketbd.model.single_match_scorecard.ScoreCardWrapperBowler;
import me.abir.dailycricketbd.util.Configuration;

public class BowlerVH extends ChildViewHolder {

    private ConstraintLayout clHolder;
    private View grayDivider;
    private TextView tvName;
    private TextView tvDissmisal;
    private TextView tvOvers;
    private TextView tvMaidens;
    private TextView tvRunsConceded;
    private TextView tvWickets;
    private TextView tvEconomy;

    public BowlerVH(View itemView) {
        super(itemView);
        clHolder = itemView.findViewById(R.id.clHolder);
        grayDivider = itemView.findViewById(R.id.grayDivider);
        tvDissmisal = itemView.findViewById(R.id.tvDissmisal);
        tvName = itemView.findViewById(R.id.tvName);
        tvOvers = itemView.findViewById(R.id.tvRun);
        tvMaidens = itemView.findViewById(R.id.tvBall);
        tvRunsConceded = itemView.findViewById(R.id.tvFours);
        tvWickets = itemView.findViewById(R.id.tvSixes);
        tvEconomy = itemView.findViewById(R.id.tvStrikeRate);
    }

    public void onBind(ScoreCardWrapperBowler bowler, int pos) {
        tvDissmisal.setVisibility(View.GONE);
        if (bowler.getName().equals(Configuration.HEADER_TEXT_BOWLER)) {
            clHolder.setBackgroundColor(clHolder.getContext().getResources().getColor(R.color.light_gray));
            grayDivider.setVisibility(View.GONE);
            //tvName.setTextColor(tvName.getContext().getResources().getColor(R.color.black));
        } else {
            clHolder.setBackgroundColor(clHolder.getContext().getResources().getColor(R.color.white));
            grayDivider.setVisibility(View.VISIBLE);
            //tvName.setTextColor(tvName.getContext().getResources().getColor(R.color.light_blue));
        }

        tvName.setText(bowler.getName());
        tvOvers.setText(bowler.getOvers());
        tvMaidens.setText(bowler.getMaidens());
        tvRunsConceded.setText(bowler.getRunsConceded());
        tvWickets.setText(bowler.getWickets());
        tvEconomy.setText(bowler.getEconomyRate());
    }

}
