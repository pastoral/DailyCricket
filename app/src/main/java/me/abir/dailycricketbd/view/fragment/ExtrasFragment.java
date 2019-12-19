package me.abir.dailycricketbd.view.fragment;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.browser.customtabs.CustomTabsClient;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.browser.customtabs.CustomTabsServiceConnection;
import androidx.browser.customtabs.CustomTabsSession;
import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import me.abir.dailycricketbd.R;
import me.abir.dailycricketbd.util.Configuration;
import me.abir.dailycricketbd.view.activity.FragmentHolderActivity;

public class ExtrasFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "ExtrasFragment";
    private Context context;
    private LinearLayout llFacebook;
    private LinearLayout llTwitter;
    private LinearLayout llAboutUs;
    private LinearLayout llRateUs;
    private LinearLayout llLanguage;

    private CustomTabsClient mClient;
    private CustomTabsSession mCustomTabsSession;
    private CustomTabsIntent.Builder builder;
    private boolean warmedUp = false;
    private AdView mAdView;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_extras_v2, container, false);
        initView(view);
        initListeners();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAd(view);
    }

    private void initAd(View view) {
        mAdView = view.findViewById(R.id.adBanner);
        AdRequest adRequest = new AdRequest.Builder().build();
        if (mAdView != null) mAdView.loadAd(adRequest);
    }

    private void initView(View view) {
        llFacebook = view.findViewById(R.id.llFacebook);
        llTwitter = view.findViewById(R.id.llTwitter);
        llAboutUs = view.findViewById(R.id.llAboutUs);
        llRateUs = view.findViewById(R.id.llRateUs);
        llLanguage = view.findViewById(R.id.llLanguage);
    }

    private void initListeners() {
        llFacebook.setOnClickListener(this);
        llTwitter.setOnClickListener(this);
        llAboutUs.setOnClickListener(this);
        llRateUs.setOnClickListener(this);
        llLanguage.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llRateUs:
                onRateUsClick();
                break;
            case R.id.llTwitter:
                prepareCustomTab("https://twitter.com/dailycricketbd");
                break;
            case R.id.llFacebook:
                prepareCustomTab("https://www.facebook.com/dailycricketcombd/");
                break;
            case R.id.llAboutUs:
                showNewActivity(Configuration.FRAGMENT_TAG_ABOUT_US);
                break;
            case R.id.llLanguage:
                showLanguageDialog();
                break;
        }

    }

    private void onRateUsClick() {
        startActivity(
                new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=com.onedigital.dailycricket")
                )
        );
    }

    public void showLanguageDialog() {
        LanguageSelectionDialog selectionDialog = new LanguageSelectionDialog();
        selectionDialog.setCancelable(false);
        selectionDialog.setSelectionListener(new LanguageSelectionDialog.LanguageSelectionListener() {
            @Override
            public void onLanguageSelected(String selectedLanguage) {
                if (getActivity() != null) {
                    getActivity().recreate();
                }
            }
        });
        selectionDialog.show(getChildFragmentManager(), "language");
    }

    private void showNewActivity(String tag) {
        Intent intent = new Intent(getActivity(), FragmentHolderActivity.class);
        intent.putExtra(Configuration.EXTRAS_FRAGMENT_TAG, tag);
        startActivity(intent);
    }

    private void prepareCustomTab(final String url) {
        builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(getResources().getColor(R.color.colorPrimary));
        builder.setShowTitle(true);
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_dashboard_black_24dp);
        PendingIntent pendingIntent = createPendingShareIntent(url);
        builder.setActionButton(icon, getString(R.string.app_name), pendingIntent, true);
        builder.addMenuItem("Share this page", pendingIntent);
        CustomTabsServiceConnection mCustomTabsServiceConnection = new CustomTabsServiceConnection() {
            @Override
            public void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
                //Pre-warming
                mClient = customTabsClient;
                mClient.warmup(0L);
                mCustomTabsSession = mClient.newSession(null);
                mCustomTabsSession.mayLaunchUrl(Uri.parse(url), null, null);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mClient = null;
            }
        };
        warmedUp = CustomTabsClient.bindCustomTabsService(context,
                "com.android.chrome", mCustomTabsServiceConnection);

        builder.build().launchUrl(context, Uri.parse(url));
    }

    private PendingIntent createPendingShareIntent(String url) {
        Intent actionIntent = new Intent(Intent.ACTION_SEND);
        actionIntent.setType("text/plain");
        actionIntent.putExtra(Intent.EXTRA_TEXT, url);
        return PendingIntent.getActivity(
                context.getApplicationContext(), 0, actionIntent, 0);
    }
}
