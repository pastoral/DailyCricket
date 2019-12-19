package me.abir.dailycricketbd.view.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.abir.dailycricketbd.R;
import me.abir.dailycricketbd.adapter.VideoAdapter;
import me.abir.dailycricketbd.interfaces.OnVideoResponseListener;
import me.abir.dailycricketbd.model.video_module_v2.DataItem;
import me.abir.dailycricketbd.model.video_module_v2.VideoModel;
import me.abir.dailycricketbd.model.video_moule.WrapperVideoModel;
import me.abir.dailycricketbd.rest.CricApiResponseHandler;
import me.abir.dailycricketbd.util.LocaleManager;

/**
 * Created by Abir on 05-Nov-17.
 */

public class VideoFragment extends Fragment implements OnVideoResponseListener {

    private static final String TAG = "VideoFragment";
    private RecyclerView rvVideos;
    private ProgressBar pbVideo;
    private VideoAdapter videoAdapter;
    private CricApiResponseHandler handler;
    private int pageNo = 1;
    private List<WrapperVideoModel> videoList;
    private boolean shouldRequest;
    private AdView mAdView;

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {

        View view = inflater.inflate(R.layout.fragment_video, container, false);
        init(view);
        initAd(view);
        getVideoFromApi();
        return view;
    }

    private void initAd(View view) {
        mAdView = view.findViewById(R.id.adBanner);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void getVideoFromApi() {
        Log.d(TAG, "getVideoFromApi() called");
        handler = new CricApiResponseHandler(LocaleManager.getLanguage(getContext()));
        handler.setOnVideoResponseListener(this);
        handler.getVideos(pageNo);
        shouldRequest = false;
    }

    private void init(View view) {
        videoList = new ArrayList<>();

        pbVideo = view.findViewById(R.id.pbVideo);
        rvVideos = view.findViewById(R.id.rvVideos);

        final LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        rvVideos.setLayoutManager(manager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvVideos.getContext(),
                manager.getOrientation());
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.divider_drawable));
        rvVideos.addItemDecoration(dividerItemDecoration);

        /*rvVideos.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                if (last == videoList.size() - 1 && pageNo != 0 && shouldRequest) {
                    handler.getVideos(pageNo);
                    pbVideo.setVisibility(View.VISIBLE);
                    shouldRequest = false;
                }

            }
        });*/

        videoAdapter = new VideoAdapter();
        rvVideos.setAdapter(videoAdapter);
    }

    @Override
    public void onVideoResponse(VideoModel videoModel) {
        Log.w(TAG, "onVideoResponse() called with: videoModel = [" + videoModel + "]");
        if (videoModel != null) {
            List<DataItem> dataList = videoModel.getData();
            for (DataItem dataItem : dataList) {
                String urlId = getYouTubeId(dataItem.getUrl());
                Log.i(TAG, "onVideoResponse() id: " + urlId);
                if (urlId != null) {
                    WrapperVideoModel model = new WrapperVideoModel();
                    model.setObject(dataItem);
                    model.setYtVideoId(urlId);
                    videoList.add(model);

                    videoAdapter.setData(videoList);
                }
            }
        } else {
            Toast.makeText(getActivity(), R.string.something_went_wrong, Toast.LENGTH_SHORT).show();
        }
        pbVideo.setVisibility(View.INVISIBLE);
    }

    private String getYouTubeId(String youTubeUrl) {
        String pattern = "(?<=youtu.be/|watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(youTubeUrl);
        if (matcher.find()) {
            return matcher.group();
        } else {
            return null;
        }
    }
}
