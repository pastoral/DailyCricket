package me.abir.dailycricketbd.view.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import me.abir.dailycricketbd.DailyCricketApp;
import me.abir.dailycricketbd.R;
import me.abir.dailycricketbd.adapter.OptionAdapter;
import me.abir.dailycricketbd.interfaces.OnPollResponseListener;
import me.abir.dailycricketbd.model.poll_v2.Data;
import me.abir.dailycricketbd.model.poll_v2.OptionsItem;
import me.abir.dailycricketbd.model.poll_v2.PollQuestionModel;
import me.abir.dailycricketbd.rest.CricApiResponseHandler;
import me.abir.dailycricketbd.util.LocaleManager;


public class PollFragment extends Fragment implements OnPollResponseListener, View.OnClickListener {
    private static final String TAG = "PollFragment";
    private RecyclerView rvOptions;
    private TextView tvPollQuestion;
    private Button btPollSubmit;
    private OptionAdapter adapter;
    private CricApiResponseHandler handler;
    private ProgressBar progressBar;
    private Data poll;
    private String pollId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_poll, container, false);
        init(view);
        getPollFromApi();
        return view;
    }

    private void init(View view) {
        rvOptions = view.findViewById(R.id.rvOptions);
        progressBar = view.findViewById(R.id.progressBar);
        tvPollQuestion = view.findViewById(R.id.tvPollQuestion);
        btPollSubmit = view.findViewById(R.id.btPollSubmit);

        final LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        rvOptions.setLayoutManager(manager);
        rvOptions.setHasFixedSize(true);

        adapter = new OptionAdapter();
        rvOptions.setAdapter(adapter);
    }

    private void getPollFromApi() {
        Log.d(TAG, "getPollFromApi() called");
        handler = new CricApiResponseHandler(LocaleManager.getLanguage(getContext()));
        handler.setOnPollResponseListener(this);
        handler.getPoll();
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btPollSubmit:
                submitPoll();

        }
    }

    @Override
    public void onPollResponse(PollQuestionModel pollModel) {
        Log.w(TAG, "onPollResponse() called with: featuredNews = [" + pollModel + "]");
        if (
                pollModel != null &&
                        pollModel.getData() != null &&
                        pollModel.getData().getOptions() != null
        ) {
            poll = pollModel.getData();
            pollId = String.valueOf(poll.getId());
            tvPollQuestion.setText(poll.getQuestion());
            adapter.setData(poll.getOptions());
            btPollSubmit.setOnClickListener(this);
        } else {
            Toast.makeText(getActivity(), R.string.something_went_wrong, Toast.LENGTH_SHORT).show();
            btPollSubmit.setClickable(false);
            btPollSubmit.setAlpha(.6f);
        }
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void submitPoll() {
        List<OptionsItem> options = adapter.getData();
        for (OptionsItem option : options) {
            if (option.getUserChecked() && pollId != null) {
                Log.i(TAG, "Option id " + option.getOptionId() + " is checked!");
                String deviceId = (String) DailyCricketApp.getUniqueID(getActivity()).subSequence(0, 8);
                handler.updatePoll(
                        String.valueOf(option.getOptionId()),
                        pollId,
                        deviceId
                );
            }
        }
    }

    @Override
    public void onVoteResponse(String voteResponse) {
        if (voteResponse != null) {
            Toast.makeText(getActivity(), voteResponse, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), R.string.something_went_wrong, Toast.LENGTH_SHORT).show();
        }
    }
}
