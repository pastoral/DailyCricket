package me.abir.dailycricketbd.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import me.abir.dailycricketbd.BaseActivity;
import me.abir.dailycricketbd.R;
import me.abir.dailycricketbd.interfaces.OnScorecardResponseListener;
import me.abir.dailycricketbd.interfaces.SingleLiveMatchDetailsListener;
import me.abir.dailycricketbd.model.ScoreCardLiveDetailsWrapper;
import me.abir.dailycricketbd.model.results_module.ResultsModelWrapper;
import me.abir.dailycricketbd.model.single_match_scorecard.ScoreCardWrapperModel;
import me.abir.dailycricketbd.rest.CricApiResponseHandler;
import me.abir.dailycricketbd.util.Configuration;
import me.abir.dailycricketbd.util.CricResponseParser;
import me.abir.dailycricketbd.util.CricUtil;
import me.abir.dailycricketbd.util.LocaleManager;
import me.abir.dailycricketbd.view.fragment.CommentaryFragment;
import me.abir.dailycricketbd.view.fragment.LiveScoreDetailsFragment;
import me.abir.dailycricketbd.view.fragment.ScoreCardFragment;

public class SingleMatchActivity extends BaseActivity implements OnScorecardResponseListener,
        SingleLiveMatchDetailsListener {
    private static final String TAG = "SingleMatchActivity";
    public static final int REFRESH_DELAY = 30000;
    private final Handler handler = new Handler();
    private TabLayout tabLayoutLive;
    private ViewPager viewpagerLive;
    private boolean isLive;
    private String matchId;
    private String matchStatus;
    private ResultsModelWrapper matchInfo;
    private CricApiResponseHandler apiResponseHandler;
    private ScoreCardFragment scoreCardFragment;
    private CommentaryFragment commentaryFragment;
    private LiveScoreDetailsFragment liveScoreDetailsFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_match);
        parseArguments();
        initTabLayout();
        getScorecard();
        getData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getScoreCardRunnable != null) {
            handler.removeCallbacks(getScoreCardRunnable);
            Log.e(TAG, "onDestroy() Removed refresh call");
        }
    }

    private void parseArguments() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(Configuration.KEY_MATCH_INFO_ARG)) {
            isLive = intent.getBooleanExtra(Configuration.KEY_MATCH_IS_LIVE, false);
            matchInfo = intent.getParcelableExtra(Configuration.KEY_MATCH_INFO_ARG);
            matchId = matchInfo.getMatchId();
            matchStatus = matchInfo.getMatchStatus();
            Log.d(TAG, "parseArguments() MatchId: " + matchId);
        }
    }

    private void initTabLayout() {
        viewpagerLive = findViewById(R.id.viewpagerLive);
        setUpViewPager(viewpagerLive);
        tabLayoutLive = findViewById(R.id.tabLayoutLive);
        tabLayoutLive.setupWithViewPager(viewpagerLive);
    }

    private void setUpViewPager(ViewPager viewPager) {
        scoreCardFragment = ScoreCardFragment.newInstance(matchId, matchStatus);
        commentaryFragment = CommentaryFragment.newInstance(matchId);
        liveScoreDetailsFragment = LiveScoreDetailsFragment.newInstance(matchId);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(liveScoreDetailsFragment, getString(R.string.tab_live));
        adapter.addFragment(scoreCardFragment, getString(R.string.tab_scorecard));
        adapter.addFragment(commentaryFragment, getString(R.string.tab_stats));

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setCurrentItem(0);
    }

    private void getScorecard() {
        String token = CricUtil.getAccessToken(this);
        apiResponseHandler = new CricApiResponseHandler(LocaleManager.getLanguage(this));
        apiResponseHandler.getSingleMatchScoreCardData(matchId, token);
        apiResponseHandler.setOnScorecardResponseListener(this);
        scoreCardFragment.setProgressBarVisible(true);

        apiResponseHandler.getSingleLiveMatchData(matchId, token,isLive);
        apiResponseHandler.setSingleLiveMatchDetailsListener(this);
        liveScoreDetailsFragment.setProgressBarVisible(true);
    }

    @Override
    public void onSingleLiveMatchResponse(List<Object> liveMatchDataList) {
        if (liveMatchDataList != null && liveMatchDataList.size() > 0) {
            Log.d(TAG, "onSingleLiveMatchResponse() size: " + liveMatchDataList.size());
            liveScoreDetailsFragment.setLiveScoreDetailsData(liveMatchDataList);
        }
        liveScoreDetailsFragment.setProgressBarVisible(false);
    }

    @Override
    public void onScorecardResponse(ScoreCardLiveDetailsWrapper liveDetailsWrapper) {
        Log.d(TAG, "onScorecardResponse() called with: liveDetailsWrapper = [" + liveDetailsWrapper + "]");
        if (liveDetailsWrapper != null) {
            liveScoreDetailsFragment.setBasicLiveDetails(liveDetailsWrapper, matchInfo);
            setScoreCard(liveDetailsWrapper);
        }
        scoreCardFragment.setProgressBarVisible(false);
    }

    private void setScoreCard(ScoreCardLiveDetailsWrapper liveDetailsWrapper) {
        List<ScoreCardWrapperModel> wrapperModel = liveDetailsWrapper.getScoreCardWrapperModels();
        scoreCardFragment.setBaseScoreCard(liveDetailsWrapper);
        if (scoreCardFragment != null && wrapperModel != null && wrapperModel.size() > 0) {
            scoreCardFragment.setScoreCardData(wrapperModel);

            List<String> inningsNames = new ArrayList<>();
            int size = wrapperModel.size();

            for (int i = 0; i < size; i++) {
                //String val = wrapperModel.get(i).getTitle();
                String val = CricResponseParser.getInningsName(i) + " " + wrapperModel.get(i).getTitle();
                inningsNames.add(val);
            }
            commentaryFragment.setUpInnings(inningsNames);
        } else {
            commentaryFragment.setUpInnings(null);
        }
    }

    private void getData() {
        Log.i(TAG, "getData() called. isLive: " + isLive);
        if (isLive)
            handler.postDelayed(getScoreCardRunnable, REFRESH_DELAY);
    }

    Runnable getScoreCardRunnable = new Runnable() {
        @Override
        public void run() {
            Log.i(TAG, "getScoreCardRunnable.run() called");
            getScorecard();
            getData();
        }
    };


    /**
     * Adapter class of the view pager
     */
    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
    }
}
