package me.abir.dailycricketbd.adapter.viewholder;

import android.content.res.ColorStateList;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import me.abir.dailycricketbd.R;
import me.abir.dailycricketbd.model.single_live_match.Commentary;

public class BallVH extends RecyclerView.ViewHolder {

    private TextView tvOver;
    private TextView tvScore;
    private TextView tvCommentary;

    public BallVH(View itemView) {
        super(itemView);
        tvOver = itemView.findViewById(R.id.tvOver);
        tvScore = itemView.findViewById(R.id.tvScore);
        tvCommentary = itemView.findViewById(R.id.tvCommentary);
    }

    public void setUI(Commentary commentary) {
        String over = commentary.getOver() + "." + commentary.getBall();
        String score = commentary.getScore();
        if (score.contains("4")) {
            setBgTint(tvScore, R.color.tag_four);
        } else if (score.contains("6")) {
            setBgTint(tvScore, R.color.tag_six);
        } else if (score.equals("w")) {
            setBgTint(tvScore, R.color.tag_wicket);
        } else {
            setBgTint(tvScore, R.color.gray_dark);
        }
        tvOver.setText(over);
        tvScore.setText(score);
        tvCommentary.setText(commentary.getCommentary());
    }

    private void setBgTint(TextView tv, int tint) {
        ViewCompat.setBackgroundTintList(tv,
                ColorStateList.valueOf(tv.getContext().getResources().getColor(tint)));
    }
}