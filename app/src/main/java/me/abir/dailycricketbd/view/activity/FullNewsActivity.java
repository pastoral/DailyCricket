package me.abir.dailycricketbd.view.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.squareup.picasso.Picasso;

import me.abir.dailycricketbd.BaseActivity;
import me.abir.dailycricketbd.R;
import me.abir.dailycricketbd.interfaces.OnFullNewsResponse;
import me.abir.dailycricketbd.model.full_news_v2.Data;
import me.abir.dailycricketbd.model.full_news_v2.FullNewsModel;
import me.abir.dailycricketbd.rest.CricApiResponseHandler;
import me.abir.dailycricketbd.util.Configuration;
import me.abir.dailycricketbd.util.LocaleManager;

/**
 * Created by Abir on 07-Nov-17.
 */

public class FullNewsActivity extends BaseActivity implements OnFullNewsResponse {
    private TextView tvNewsTitle;
    private TextView tvNewsDate;
    private TextView tvNewsReporter;
    private ImageView ivImage;
    private ProgressBar progressBar;
    private WebView webViewFullNews;
    private AdView mAdView;

    private static final String MIME_TYPE = "text/html";
    private static final String ENCODING = "UTF-8";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_news);
        initViews();
        initAd();
        extractInfo();
    }

    private void initAd() {
        mAdView = findViewById(R.id.adBanner);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void initViews() {
        tvNewsTitle = findViewById(R.id.tvNewsTitle);
        tvNewsDate = findViewById(R.id.tvNewsDate);
        tvNewsReporter = findViewById(R.id.tvNewsReporter);
        ivImage = findViewById(R.id.ivImage);
        progressBar = findViewById(R.id.progressBar);
        webViewFullNews = findViewById(R.id.webViewFullNews);
    }

    private void extractInfo() {
        if (getIntent().hasExtra(Configuration.NEWS_SLUG_TAG)) {
            String slug = getIntent().getStringExtra(Configuration.NEWS_SLUG_TAG);
            getDataFromApi(slug);
        } else {
            finish();
        }
    }

    private void getDataFromApi(String slug) {
        CricApiResponseHandler handler = new CricApiResponseHandler(LocaleManager.getLanguage(this));
        handler.setOnFullNewsResponse(this);
        handler.getFullNews(slug);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFullNews(FullNewsModel fullNewsModel) {
        if (fullNewsModel != null) {
            Data data = fullNewsModel.getData();
            setViews(data);
        } else {
            Toast.makeText(this, R.string.something_went_wrong, Toast.LENGTH_SHORT).show();
        }
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void setViews(Data data) {
        tvNewsTitle.setText(data.getTitle());
        tvNewsDate.setText(data.getDate());
        tvNewsReporter.setText(data.getReporter());
        webViewFullNews.loadDataWithBaseURL(
                "", data.getContent(), MIME_TYPE, ENCODING, ""
        );

        Picasso.with(this)
                .load(data.getImage())
                .fit()
                .placeholder(R.drawable.background)
                .error(R.drawable.ic_flag_black)
                .into(ivImage);
    }
}
