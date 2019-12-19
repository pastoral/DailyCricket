package me.abir.dailycricketbd.view.fragment;


import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import me.abir.dailycricketbd.R;
import me.abir.dailycricketbd.adapter.FixtureAdapter;
import me.abir.dailycricketbd.busmodel.ScrollEventBus;
import me.abir.dailycricketbd.model.results_module.ResultsGroupChildModel;
import me.abir.dailycricketbd.util.CricUtil;
import me.abir.dailycricketbd.viewmodel.UpcomingFixturesVM;

public class UpcomingFixturesFragment extends Fragment {
    private static final String TAG = "UpcomingFixturesFragmen";
    private Context context;
    private RecyclerView rvFixtures;
    private ProgressBar pbFixtures;
    private FixtureAdapter fixtureAdapter;
    private AdView mAdView;
    //private List<ResultsModelWrapper> resultsModels;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upcoming_fixtures, container, false);
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
        pbFixtures = view.findViewById(R.id.pbFixtures);
        rvFixtures = view.findViewById(R.id.rvFixtures);
        rvFixtures.setLayoutManager(new LinearLayoutManager(context));
        /*fixtureAdapter = new FixtureAdapter();
        rvFixtures.setAdapter(fixtureAdapter);*/
        //rvFixtures.addItemDecoration(setStickyHeader());

        rvFixtures.addOnScrollListener(new RecyclerView.OnScrollListener() {
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


    private void getResultsFromApi() {
        UpcomingFixturesVM viewModel = ViewModelProviders.of(getActivity()).get(UpcomingFixturesVM.class);
        viewModel.getResults(CricUtil.getAccessToken(context)).observe(this, observer);
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

    private Observer<List<ResultsGroupChildModel>> observer = new Observer<List<ResultsGroupChildModel>>() {
        @Override
        public void onChanged(@Nullable List<ResultsGroupChildModel> resultsModels) {
            Log.w(TAG, "onCompletedMatchResponse() called with: resultsModel = [" + resultsModels + "]");
            if (resultsModels != null && resultsModels.size() > 0) {
                //this.resultsModels = resultsModels;
                //fixtureAdapter.setData(resultsModels);
                fixtureAdapter = new FixtureAdapter(resultsModels);
                if (resultsModels.size() == 1) {
                    fixtureAdapter.toggleGroup(0);
                } else if (resultsModels.size() > 1) {
                    fixtureAdapter.toggleGroup(resultsModels.size() - 1);
                }
                rvFixtures.setAdapter(fixtureAdapter);
            }
            pbFixtures.setVisibility(View.INVISIBLE);
        }
    };
}
