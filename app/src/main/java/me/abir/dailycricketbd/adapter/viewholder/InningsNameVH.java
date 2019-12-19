package me.abir.dailycricketbd.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import me.abir.dailycricketbd.R;
import me.abir.dailycricketbd.model.single_match_scorecard.ScoreCardWrapperModel;

public class InningsNameVH extends GroupViewHolder {

    private TextView tvInningsTitle;
    private TextView tvScoresFull;
    private ImageView ivArrow;

    public InningsNameVH(View itemView) {
        super(itemView);
        tvInningsTitle = itemView.findViewById(R.id.tvInningsTitle);
        tvScoresFull = itemView.findViewById(R.id.tvScoresFull);
        ivArrow = itemView.findViewById(R.id.ivArrow);
    }

    public void setInningsTitle(ExpandableGroup group, boolean isExpanded) {
        //tvInningsTitle.setText(group.getTitle());
        tvInningsTitle.setText(((ScoreCardWrapperModel) group).getHeaderValue().getTitle());
        tvScoresFull.setText(((ScoreCardWrapperModel) group).getHeaderValue().getDetails());
        if (isExpanded) {
            ivArrow.setImageResource(R.drawable.ic_arrow_up);
        } else {
            ivArrow.setImageResource(R.drawable.ic_arrow_down);
        }
    }

    @Override
    public void expand() {
        super.expand();
        ivArrow.setImageResource(R.drawable.ic_arrow_up);
    }

    @Override
    public void collapse() {
        super.collapse();
        ivArrow.setImageResource(R.drawable.ic_arrow_down);
    }
}
