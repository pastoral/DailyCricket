package me.abir.dailycricketbd.adapter.viewholder;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import me.abir.dailycricketbd.R;
import me.abir.dailycricketbd.model.single_match_scorecard.ScoreCardWrapperKeyValue;

public class OtherVH extends ChildViewHolder {

    private TextView tvTitle;
    private TextView tvDetails;

    public OtherVH(View itemView) {
        super(itemView);
        tvTitle = itemView.findViewById(R.id.tvTitle);
        tvDetails = itemView.findViewById(R.id.tvDetails);
    }

    public void onBind(ScoreCardWrapperKeyValue keyValue) {
        tvTitle.setText(keyValue.getTitle());
        tvDetails.setText(keyValue.getDetails());
    }
}
