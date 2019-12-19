package me.abir.dailycricketbd.view.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import me.abir.dailycricketbd.R;
import me.abir.dailycricketbd.adapter.NewsAdapter;
import me.abir.dailycricketbd.busmodel.ScrollEventBus;
import me.abir.dailycricketbd.interfaces.OnNewsResponseListener;
import me.abir.dailycricketbd.model.featured_news_v2.FeaturedArticleModel;
import me.abir.dailycricketbd.model.latest_news_v2.Datum;
import me.abir.dailycricketbd.model.latest_news_v2.LatestArticleModel;
import me.abir.dailycricketbd.rest.CricApiResponseHandler;
import me.abir.dailycricketbd.util.LocaleManager;


public class NewsFragment extends Fragment implements OnNewsResponseListener {
    private static final String TAG = "NewsFragment";
    private RecyclerView rvNews;
    private List<Object> newsList;
    private CricApiResponseHandler handler;
    private NewsAdapter adapter;
    private ProgressBar progressBar;
    private int latestPage = 1;
    private boolean shouldRequest;
    private AdView mAdView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        init(view);
        initAd(view);
        getNewsFromApi();
        return view;
    }

    private void getNewsFromApi() {
        Log.d(TAG, "getNewsFromApi() called");
        handler = new CricApiResponseHandler(LocaleManager.getLanguage(getContext()));
        handler.setOnNewsResponseListener(this);
        handler.getFeaturedNews();
        progressBar.setVisibility(View.VISIBLE);
    }

    private void initAd(View view) {
        mAdView = view.findViewById(R.id.adBanner);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void init(View view) {
        newsList = new ArrayList<>();

        progressBar = view.findViewById(R.id.progressBar);

        rvNews = view.findViewById(R.id.rvNews);
        final LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        rvNews.setLayoutManager(manager);

        rvNews.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

                int last = (manager).findLastVisibleItemPosition();
                Log.e(TAG, "onScrolled() lastPos: " + last);
                if (last == newsList.size() - 1 && latestPage != 0 && shouldRequest) {
                    handler.getLatestNews(latestPage);
                    progressBar.setVisibility(View.VISIBLE);
                    shouldRequest = false;
                }

            }
        });

        adapter = new NewsAdapter();
        rvNews.setAdapter(adapter);
    }

    @Override
    public void onLatestNewsResponse(LatestArticleModel latestNews) {
        Log.w(TAG, "onLatestNewsResponse() called with: latestNews = [" + latestNews + "]");
        if (latestNews != null) {
            List<Datum> singleLatestNewses = latestNews.getData();
            if (singleLatestNewses.size() > 0) {
                newsList.addAll(singleLatestNewses);
            }
            adapter.setData(newsList);

            int currentPageCount = latestNews.getMeta().getCurrentPage();
            int lastPageCount = latestNews.getMeta().getLastPage();

            if (currentPageCount < lastPageCount) {
                latestPage++;
            } else {
                latestPage = 0;
            }
            shouldRequest = true;
        } else {
            Toast.makeText(getActivity(), R.string.something_went_wrong, Toast.LENGTH_SHORT).show();
        }
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onFeaturedNewsResponse(FeaturedArticleModel featuredNews) {
        Log.w(TAG, "onFeaturedNewsResponse() called with: featuredNews = [" + featuredNews + "]");
        if (featuredNews != null) {
            newsList.add(0, featuredNews);
            handler.getLatestNews(latestPage);
        } else {
            //Toast.makeText(getActivity(), R.string.something_went_wrong, Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}
