package me.abir.dailycricketbd.adapter.viewholder;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import me.abir.dailycricketbd.R;
import me.abir.dailycricketbd.model.single_live_match.InningsStatusModel;

/**
 * Created by Abir on 04-May-18.
 */
public class InningsStatusVH extends RecyclerView.ViewHolder {

    private TextView tvOther;
    private TextView tvFirstBat;
    private TextView tvCurrentBowler;
    private TextView tvInnStatus;
    private TextView tvSecondBat;
    private TextView tvBowlerFig;

    public InningsStatusVH(View itemView) {
        super(itemView);
        tvOther = itemView.findViewById(R.id.tvOther);
        tvFirstBat = itemView.findViewById(R.id.tvFirstBat);
        tvCurrentBowler = itemView.findViewById(R.id.tvCurrentBowler);
        tvInnStatus = itemView.findViewById(R.id.tvInnStatus);
        tvSecondBat = itemView.findViewById(R.id.tvSecondBat);
        tvBowlerFig = itemView.findViewById(R.id.tvBowlerFig);
    }

    public void onBind(InningsStatusModel model) {
        tvOther.setText(model.getLastFiveOvers());
        tvFirstBat.setText(model.getFirstBatsman());
        tvSecondBat.setText(model.getSecondBatsman());
        tvInnStatus.setText(model.getInningsStatus());
        tvCurrentBowler.setText(model.getCurrentBowler());
        tvBowlerFig.setText(model.getBowlerFigure());

    }

}
