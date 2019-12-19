package me.abir.dailycricketbd.view.fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.List;

import me.abir.dailycricketbd.R;
import me.abir.dailycricketbd.adapter.ScoreCardAdapter;
import me.abir.dailycricketbd.model.ScoreCardLiveDetailsWrapper;
import me.abir.dailycricketbd.model.single_match_scorecard.ScoreCardWrapperModel;
import me.abir.dailycricketbd.rest.CricApiResponseHandler;

public class ScoreCardFragment extends Fragment {
    private static final String TAG = "ScoreCardFragment";
    private static final String ARG_MATCH_ID = "arg_match_id";
    private static final String ARG_MATCH_STATUS = "arg_match_status";
    private CricApiResponseHandler apiResponseHandler;
    private String matchId;
    private String matchStatus;
    private ScoreCardAdapter adapter;
    private RecyclerView rvScoreCard;
    private Context context;
    private ProgressBar progressBar;
    private TextView tvMatchTitle;
    private TextView tvMatchStatus;
    private TextView tvTeamAName;
    private TextView tvTeamAScore;
    private TextView tvTeamBName;
    private TextView tvTeamBScore;
    private TextView tvMatchResult;
    //private TextView tvManOfTheMatch;
    //private TextView tvPlayerOfTheMatch;
    private AdView mAdView;
    private AdView mAdView2;

    public ScoreCardFragment() {
    }

    public static ScoreCardFragment newInstance(String matchId, String matchStatus) {
        ScoreCardFragment fragment = new ScoreCardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MATCH_ID, matchId);
        args.putString(ARG_MATCH_STATUS, matchStatus);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.matchId = getArguments().getString(ARG_MATCH_ID);
            this.matchStatus = getArguments().getString(ARG_MATCH_STATUS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_score_card, container, false);
        initView(view);
        initAd(view);
        return view;
    }

    private void initAd(View view) {
        mAdView = view.findViewById(R.id.adBanner);
        mAdView2 = view.findViewById(R.id.adBanner_2);

        AdRequest adRequest = new AdRequest.Builder().build();
        AdRequest adRequest2 = new AdRequest.Builder().build();

        mAdView.loadAd(adRequest);
        mAdView2.loadAd(adRequest2);
    }

    private void initView(View view) {
        tvMatchTitle = view.findViewById(R.id.tvMatchTitle);
        tvMatchStatus = view.findViewById(R.id.tvMatchStatus);
        tvTeamAName = view.findViewById(R.id.tvTeamAName);
        tvTeamAScore = view.findViewById(R.id.tvTeamAScore);
        tvTeamBName = view.findViewById(R.id.tvTeamBName);
        tvTeamBScore = view.findViewById(R.id.tvTeamBScore);
        /*tvManOfTheMatch = view.findViewById(R.id.tvManOfTheMatch);
        tvPlayerOfTheMatch = view.findViewById(R.id.tvPlayerOfTheMatch);*/
        tvMatchResult = view.findViewById(R.id.tvMatchResult);

        progressBar = view.findViewById(R.id.progressBar);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        rvScoreCard = view.findViewById(R.id.rvScoreCard);
        rvScoreCard.setLayoutManager(manager);
    }

    public void setScoreCardData(List<ScoreCardWrapperModel> wrapperModel) {
        if (rvScoreCard != null && wrapperModel != null && wrapperModel.size() > 0) {
            Log.d(TAG, "onScorecardResponse() called with: wrapperModel = [" + wrapperModel.size() + "]");
            adapter = new ScoreCardAdapter(wrapperModel);
            adapter.toggleGroup(wrapperModel.size() - 1); // To expand the last group
            rvScoreCard.setAdapter(adapter);
        }
    }

    public void setBaseScoreCard(ScoreCardLiveDetailsWrapper liveDetails) {
        try {
            String titleText = liveDetails.getMatchStartTime() + "\n" + liveDetails.getVenue();
            tvMatchTitle.setText(titleText);
            tvMatchStatus.setText(liveDetails.getMatchStatus());
            tvTeamAName.setText(liveDetails.getTeamNameA());
            tvTeamBName.setText(liveDetails.getTeamNameB());
            tvTeamAScore.setText(liveDetails.getTeamNameScoreA());
            tvTeamBScore.setText(liveDetails.getTeamNameScoreB());
            tvMatchResult.setText(liveDetails.getMatchResult());
            /*if (liveDetails.getManOfTheMatch().equals("")) {
                tvPlayerOfTheMatch.setVisibility(View.INVISIBLE);
            } else {
                tvPlayerOfTheMatch.setVisibility(View.VISIBLE);
                tvManOfTheMatch.setText(liveDetails.getManOfTheMatch());
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setProgressBarVisible(boolean visible) {
        if (progressBar != null)
            progressBar.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
