package me.abir.dailycricketbd.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.List;

import me.abir.dailycricketbd.R;
import me.abir.dailycricketbd.model.poll_v2.OptionsItem;

/**
 * Created by Mayesha on 04-Nov-17.
 */

public class OptionAdapter extends RecyclerView.Adapter {

    private static List<OptionsItem> optionList = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.adapter_option, parent, false
        );
        return new PollViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        setView((PollViewHolder) holder, position);
    }

    private void setView(PollViewHolder holder, final int position) {
        final OptionsItem option = optionList.get(position);
        if (option != null) {
            holder.cbPollOption.setText(option.getOption());
        }
    }

    @Override
    public int getItemCount() {
        return optionList.size();
    }

    private class PollViewHolder extends RecyclerView.ViewHolder implements
            CompoundButton.OnCheckedChangeListener {

        private CheckBox cbPollOption;

        PollViewHolder(View itemView) {
            super(itemView);
            cbPollOption = itemView.findViewById(R.id.cbPollOption);
            cbPollOption.setOnCheckedChangeListener(this);
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            optionList.get(getAdapterPosition()).setUserChecked(isChecked);
        }
    }

    public void setData(List<OptionsItem> options) {
        optionList = options;
        notifyDataSetChanged();
    }

    public List<OptionsItem> getData() {
        return optionList;
    }

}
