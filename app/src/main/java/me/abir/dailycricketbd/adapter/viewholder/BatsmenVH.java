package me.abir.dailycricketbd.adapter.viewholder;

import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import me.abir.dailycricketbd.R;
import me.abir.dailycricketbd.model.single_match_scorecard.ScoreCardWrapperBatsmen;

public class BatsmenVH extends ChildViewHolder {
    private static final String TAG = "BatsmenVH";

    private ConstraintLayout clHolder;
    private View grayDivider;
    private TextView tvName;
    private TextView tvDissmisal;
    private TextView tvRun;
    private TextView tvBall;
    private TextView tvFours;
    private TextView tvSixes;
    private TextView tvStrikeRate;

    public BatsmenVH(View itemView) {
        super(itemView);
        clHolder = itemView.findViewById(R.id.clHolder);
        grayDivider = itemView.findViewById(R.id.grayDivider);
        tvName = itemView.findViewById(R.id.tvName);
        tvDissmisal = itemView.findViewById(R.id.tvDissmisal);
        tvRun = itemView.findViewById(R.id.tvRun);
        tvBall = itemView.findViewById(R.id.tvBall);
        tvFours = itemView.findViewById(R.id.tvFours);
        tvSixes = itemView.findViewById(R.id.tvSixes);
        tvStrikeRate = itemView.findViewById(R.id.tvStrikeRate);
    }

    public void onBind(ScoreCardWrapperBatsmen wrapperBatsmen, int pos) {
        Log.i(TAG, "onBind() called with: wrapperBatsmen = [" + wrapperBatsmen + "], pos = [" + pos + "]");
        if (pos == 0) {
            clHolder.setBackgroundColor(clHolder.getContext().getResources().getColor(R.color.light_gray));
            tvDissmisal.setVisibility(View.GONE);
            grayDivider.setVisibility(View.GONE);
            tvName.setTypeface(tvName.getTypeface(), Typeface.NORMAL);
            tvRun.setTypeface(tvRun.getTypeface(), Typeface.NORMAL);
            //tvName.setTextColor(tvName.getContext().getResources().getColor(R.color.black));
        } else {
            clHolder.setBackgroundColor(clHolder.getContext().getResources().getColor(R.color.white));
            tvDissmisal.setVisibility(View.VISIBLE);
            grayDivider.setVisibility(View.VISIBLE);
            tvName.setTypeface(tvName.getTypeface(), Typeface.BOLD);
            tvRun.setTypeface(tvRun.getTypeface(), Typeface.BOLD);
            //tvName.setTextColor(tvName.getContext().getResources().getColor(R.color.light_blue));
        }

        if (wrapperBatsmen.getHowGotOut() == null) {
            tvDissmisal.setVisibility(View.GONE);
        } else {
            tvDissmisal.setVisibility(View.VISIBLE);
        }
        tvName.setText(wrapperBatsmen.getName());
        tvDissmisal.setText(wrapperBatsmen.getHowGotOut());
        tvRun.setText(wrapperBatsmen.getRun());
        tvBall.setText(wrapperBatsmen.getBall());
        tvFours.setText(wrapperBatsmen.getFours());
        tvSixes.setText(wrapperBatsmen.getSixes());
        tvStrikeRate.setText(wrapperBatsmen.getStrikeRate());
    }
}
