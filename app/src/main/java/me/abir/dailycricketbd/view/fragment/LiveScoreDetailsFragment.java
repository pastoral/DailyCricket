package me.abir.dailycricketbd.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.List;

import me.abir.dailycricketbd.R;
import me.abir.dailycricketbd.adapter.LiveScoreDetailsAdapter;
import me.abir.dailycricketbd.model.ScoreCardLiveDetailsWrapper;
import me.abir.dailycricketbd.model.results_module.ResultsModelWrapper;

public class LiveScoreDetailsFragment extends Fragment {
    private static final String TAG = "LiveScoreDetailsFragmen";
    private static final String ARG_MATCH_ID = "arg_match_id";
    private Context context;
    private String matchId;
    private LiveScoreDetailsAdapter adapter;
    private RecyclerView rvLiveScoreDetails;
    private TextView tvMatchTitle;
    private TextView tvMatchStatus;
    private TextView tvTeamAName;
    private TextView tvTeamAScore;
    private TextView tvTeamBName;
    private TextView tvTeamBScore;
    private TextView tvMatchResult;
    private TextView tvPlayerOfTheMatch;
    private TextView tvPlayerOfTheMatchText;
    /*private ImageView ivTeamAFlag;
    private ImageView ivTeamBFlag;*/
    private ProgressBar pbLive;
    private AdView mAdView;
    private AdView mAdView2;


    public LiveScoreDetailsFragment() {

    }

    public static LiveScoreDetailsFragment newInstance(String matchId) {
        LiveScoreDetailsFragment fragment = new LiveScoreDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MATCH_ID, matchId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.matchId = getArguments().getString(ARG_MATCH_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_live_score_details, container, false);
        setUI(view);
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

    private void setUI(View view) {
        rvLiveScoreDetails = view.findViewById(R.id.rvLiveScoreDetails);
        rvLiveScoreDetails.setLayoutManager(new LinearLayoutManager(context));

        tvMatchTitle = view.findViewById(R.id.tvMatchTitle);
        tvMatchStatus = view.findViewById(R.id.tvMatchStatus);
        tvTeamAName = view.findViewById(R.id.tvTeamAName);
        tvTeamAScore = view.findViewById(R.id.tvTeamAScore);
        tvTeamBName = view.findViewById(R.id.tvTeamBName);
        tvTeamBScore = view.findViewById(R.id.tvTeamBScore);
        tvMatchResult = view.findViewById(R.id.tvMatchResult);
        tvMatchResult.setVisibility(View.VISIBLE);
        tvPlayerOfTheMatch = view.findViewById(R.id.tvPlayerOfTheMatch);
        tvPlayerOfTheMatch.setVisibility(View.VISIBLE);
        tvPlayerOfTheMatchText = view.findViewById(R.id.tvPlayerOfTheMatchText);
        tvPlayerOfTheMatchText.setVisibility(View.VISIBLE);

        /*ivTeamAFlag = view.findViewById(R.id.ivTeamAFlag);
        ivTeamBFlag = view.findViewById(R.id.ivTeamBFlag);*/

        pbLive = view.findViewById(R.id.pbLive);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.context = null;
    }

    public void setBasicLiveDetails(ScoreCardLiveDetailsWrapper lv, ResultsModelWrapper rmw) {
        try {
            //tvMatchTitle.setText(rmw.getTournamentName());
            String titleText = lv.getMatchStartTime() + "\n" + lv.getVenue();
            tvMatchTitle.setText(titleText);
            tvMatchStatus.setText(lv.getMatchStatus());
            tvTeamAName.setText(lv.getTeamNameA());
            tvTeamAScore.setText(lv.getTeamNameScoreA());
            tvTeamBName.setText(lv.getTeamNameB());
            tvTeamBScore.setText(lv.getTeamNameScoreB());
            tvMatchResult.setText(lv.getMatchResult());
            tvPlayerOfTheMatch.setText(lv.getManOfTheMatch());
            tvPlayerOfTheMatchText.setText(getString(R.string.text_player_of_the_match));

            /*if (lv.getTeamFlagA() != null && lv.getTeamFlagA().trim().length() != 0) {
                Picasso.with(context)
                        .load(lv.getTeamFlagA())
                        .fit()
                        .placeholder(R.mipmap.app_icon)
                        .error(R.mipmap.app_icon)
                        .into(ivTeamAFlag);
            } else {
                ivTeamAFlag.setImageResource(R.mipmap.app_icon);
            }
            if (lv.getTeamFlagB() != null && lv.getTeamFlagB().trim().length() != 0) {
                Picasso.with(context)
                        .load(lv.getTeamFlagB())
                        .fit()
                        .placeholder(R.mipmap.app_icon)
                        .error(R.mipmap.app_icon)
                        .into(ivTeamBFlag);
            } else {
                ivTeamBFlag.setImageResource(R.mipmap.app_icon);
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLiveScoreDetailsData(List<Object> liveScoreDetailsData) {
        if (rvLiveScoreDetails != null
                        && liveScoreDetailsData != null
                        && liveScoreDetailsData.size() > 0) {
            Log.d(TAG, "onScorecardResponse() called with: wrapperModel = [" + liveScoreDetailsData.size() + "]");
            adapter = new LiveScoreDetailsAdapter();
            rvLiveScoreDetails.setAdapter(adapter);
            adapter.setDetailsListData(liveScoreDetailsData);
        }
    }

    public void setProgressBarVisible(boolean visible) {
        if (pbLive != null)
            pbLive.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    }

}
