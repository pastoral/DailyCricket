package me.abir.dailycricketbd.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import me.abir.dailycricketbd.R;
import me.abir.dailycricketbd.model.single_match_scorecard.TotalScoreWrapper;

public class TotalVH extends ChildViewHolder {

    private TextView tvTitle;
    private TextView tvDetails;

    public TotalVH(View itemView) {
        super(itemView);
        tvTitle = itemView.findViewById(R.id.tvTitle);
        tvDetails = itemView.findViewById(R.id.tvDetails);
    }

    public void onBind(TotalScoreWrapper keyValue) {
        tvTitle.setText(keyValue.getTitle());
        tvDetails.setText(keyValue.getDetails());
    }
}
