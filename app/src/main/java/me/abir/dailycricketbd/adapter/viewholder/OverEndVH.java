package me.abir.dailycricketbd.adapter.viewholder;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import me.abir.dailycricketbd.R;
import me.abir.dailycricketbd.model.single_live_match.Commentary;

public class OverEndVH extends RecyclerView.ViewHolder {
    private TextView tvAnyThing;

    public OverEndVH(View itemView) {
        super(itemView);
        tvAnyThing = itemView.findViewById(R.id.tvAnyThing);
    }

    public void setUI(Commentary commentary) {
        tvAnyThing.setText(commentary.getCommentary());
    }
}
