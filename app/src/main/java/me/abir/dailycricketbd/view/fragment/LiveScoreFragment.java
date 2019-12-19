package me.abir.dailycricketbd.view.fragment;


import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import me.abir.dailycricketbd.R;
import me.abir.dailycricketbd.adapter.ResultsAdapter;
import me.abir.dailycricketbd.busmodel.ScrollEventBus;
import me.abir.dailycricketbd.model.results_module.ResultsGroupChildModel;
import me.abir.dailycricketbd.util.CricUtil;
import me.abir.dailycricketbd.viewmodel.LiveScoreVM;

public class LiveScoreFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "LiveScoreFragment";
    private Context context;
    private RecyclerView rvLiveScore;
    private ResultsAdapter resultsAdapter;
    private ConstraintLayout cardPlaceholder;
    private SwipeRefreshLayout srLive;
    private boolean isRefreshing;
    private LiveScoreVM viewModel;
    private Handler handler;
    private AdView mAdView;
    //private List<ResultsModelWrapper> resultsModels;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_live_score, container, false);
        initView(view);
        initAd(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getResultsFromApi();
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    private void initAd(View view) {
        mAdView = view.findViewById(R.id.adBanner);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void initView(View view) {
        srLive = view.findViewById(R.id.srLive);
        srLive.setColorSchemeResources(R.color.colorAccent);
        srLive.setOnRefreshListener(this);
        cardPlaceholder = view.findViewById(R.id.cardPlaceholder);
        rvLiveScore = view.findViewById(R.id.rvLiveScore);
        rvLiveScore.setLayoutManager(new LinearLayoutManager(context));

        //rvLiveScore.addItemDecoration(setStickyHeader());

        rvLiveScore.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    EventBus.getDefault().post(new ScrollEventBus(true));
                } else {
                    EventBus.getDefault().post(new ScrollEventBus(false));
                }
            }
        });
    }

    /*private RecyclerSectionItemDecoration setStickyHeader() {
        return new RecyclerSectionItemDecoration(getResources()
                .getDimensionPixelSize(R.dimen.recycler_section_header_height),
                true, // true for sticky, false for not
                new RecyclerSectionItemDecoration.SectionCallback() {
                    @Override
                    public boolean isSection(int position) {
                        boolean value = resultsModels.get(position).isSection();
                        Log.w(TAG, "isSection() [" + value + "] position = [" + position + "]");
                        return value;
                    }

                    @Override
                    public String getSectionHeader(int position) {
                        return resultsModels.get(position).getDomestic() == 1 ?
                                "Domestic" : "International";
                    }
                });
    }*/


    private void getResultsFromApi() {
        viewModel = ViewModelProviders.of(getActivity()).get(LiveScoreVM.class);
        viewModel.getResults(CricUtil.getAccessToken(context)).observe(this, observer);

        handler = new Handler();
        handler.postDelayed(runnable, 500); // If there is already data then don't show loading
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Log.v(TAG, "run() called");
            isRefreshing = true;
            srLive.setRefreshing(true);
        }
    };

    private Observer<List<ResultsGroupChildModel>> observer = new Observer<List<ResultsGroupChildModel>>() {
        @Override
        public void onChanged(@Nullable List<ResultsGroupChildModel> resultsModels) {
            Log.w(TAG, "onCompletedMatchResponse() called with: resultsModel = [" + resultsModels + "]");
            if (resultsModels != null && resultsModels.size() > 0) {
                //this.resultsModels = resultsModels;
                resultsAdapter = new ResultsAdapter(resultsModels, true);
                if (resultsModels.size() == 1) {
                    resultsAdapter.toggleGroup(0);
                } else if (resultsModels.size() > 1) {
                    resultsAdapter.toggleGroup(resultsModels.size() - 1);
                }
                rvLiveScore.setAdapter(resultsAdapter);
                cardPlaceholder.setVisibility(View.GONE);
                //resultsAdapter.setData(resultsModels, true);
            } else {
                resultsAdapter = new ResultsAdapter(new ArrayList<ResultsGroupChildModel>(), false);
                rvLiveScore.setAdapter(resultsAdapter);
                //resultsAdapter.setData(new ArrayList<ResultsGroupChildModel>(), false);
                cardPlaceholder.setVisibility(View.VISIBLE);
            }
            isRefreshing = false;
            srLive.setRefreshing(false);

            if (runnable == null) return;
            handler.removeCallbacks(runnable);
        }
    };

    @Override
    public void onRefresh() {
        if (!isRefreshing && viewModel != null) {
            isRefreshing = true;
            viewModel.refreshResult(CricUtil.getAccessToken(context));
        }
    }
}
