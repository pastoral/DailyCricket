package me.abir.dailycricketbd.view.fragment;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Collections;
import java.util.List;

import me.abir.dailycricketbd.R;
import me.abir.dailycricketbd.adapter.CommentaryAdapter;
import me.abir.dailycricketbd.interfaces.CommentaryResponseListener;
import me.abir.dailycricketbd.model.single_live_match.Commentary;
import me.abir.dailycricketbd.rest.CricApiResponseHandler;
import me.abir.dailycricketbd.util.CricUtil;
import me.abir.dailycricketbd.util.LocaleManager;

public class CommentaryFragment extends Fragment implements AdapterView.OnItemSelectedListener, CommentaryResponseListener {
    private static final String TAG = "CommentaryFragment";
    private static final String ARG_MATCH_ID = "arg_match_id";
    private CricApiResponseHandler apiResponseHandler;
    private String matchId;
    private Context context;
    private Spinner spinnerInnings;
    private RecyclerView rvCommentary;
    private CommentaryAdapter adapter;
    private ProgressBar progressBar;
    private List<String> inningsNames;
    private AdView mAdView;

    public CommentaryFragment() {

    }

    public static CommentaryFragment newInstance(String matchId) {
        CommentaryFragment fragment = new CommentaryFragment();
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
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_commentary, container, false);
        initView(view);
        initAd(view);
        setUpRest();
        return view;
    }

    private void initAd(View view) {
        mAdView = view.findViewById(R.id.adBanner);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void setUpRest() {
        apiResponseHandler = new CricApiResponseHandler(LocaleManager.getLanguage(getContext()));
        apiResponseHandler.setCommentaryResponseListener(this);
    }

    private void initView(View view) {
        progressBar = view.findViewById(R.id.progressBar);
        spinnerInnings = view.findViewById(R.id.spinnerInnings);
        spinnerInnings.setOnItemSelectedListener(this);

        rvCommentary = view.findViewById(R.id.rvCommentary);
        rvCommentary.setLayoutManager(new LinearLayoutManager(context));
        adapter = new CommentaryAdapter();
        rvCommentary.setAdapter(adapter);
    }

    public void setUpInnings(List<String> inningsNames) {
        try {
            this.inningsNames = inningsNames;
            if (inningsNames != null) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(context,
                        R.layout.support_simple_spinner_dropdown_item, inningsNames);
                if (spinnerInnings != null) {
                    spinnerInnings.setVisibility(View.VISIBLE);
                    spinnerInnings.setAdapter(adapter);
                    spinnerInnings.setSelection(inningsNames.size() - 1);
                }
            } else {
                spinnerInnings.setVisibility(View.GONE);
            }
            progressBar.setVisibility(View.GONE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Log.w(TAG, "onItemSelected() pos: " + i);
        if (apiResponseHandler != null) {
            apiResponseHandler.getCommentariesByInnings(matchId, CricUtil.getAccessToken(context),
                    String.valueOf(i + 1));
            progressBar.setVisibility(View.VISIBLE);
            Log.d(TAG, "API called");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onCommentaryResponse(List<Commentary> commentaries) {
        if (adapter != null && commentaries != null && commentaries.size() > 0) {
            Collections.reverse(commentaries);
            adapter.setCommentaries(commentaries);
        }
        if (progressBar == null) return;
        progressBar.setVisibility(View.GONE);
    }
}
