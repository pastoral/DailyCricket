package me.abir.dailycricketbd.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import me.abir.dailycricketbd.BaseActivity;
import me.abir.dailycricketbd.R;
import me.abir.dailycricketbd.busmodel.ScrollEventBus;
import me.abir.dailycricketbd.util.Configuration;
import me.abir.dailycricketbd.view.fragment.ExtrasFragment;
import me.abir.dailycricketbd.view.fragment.LanguageSelectionDialog;
import me.abir.dailycricketbd.view.fragment.MatchesFragment;
import me.abir.dailycricketbd.view.fragment.NewsNavFragment;
import me.abir.dailycricketbd.viewmodel.BottomNavActivityVM;

public class BottomNavActivity extends BaseActivity {
    private static final String TAG = "BottomNavActivity";
    private BottomNavigationView navigation;
    private EventBus eventBus;
    private int selectedTab = -1;
    private BottomNavActivityVM viewModel;
    private SharedPreferences mSharedPreferences;
    private ExtrasFragment ef = new ExtrasFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);
        parseArguments();
        init();
        initViews();
        checkFCMTokenStatus();
        setFragment(new MatchesFragment());
        selectedTab = 0;
        registerEventBus();
    }


    private void init() {
        viewModel = ViewModelProviders.of(this).get(BottomNavActivityVM.class);
        mSharedPreferences = getSharedPreferences(
                Configuration.APP_SHARED_PREF, MODE_PRIVATE
        );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterEventBus();
    }

    private void registerEventBus() {
        eventBus = EventBus.getDefault();
        eventBus.register(this);
    }

    private void unregisterEventBus() {
        if (eventBus.isRegistered(this)) {
            eventBus.unregister(this);
        }
    }

    private void initViews() {
        navigation = findViewById(R.id.bottom_nav);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        // Top Menu Icon
        ImageView ivExtraMenu = findViewById(R.id.ivExtraMenu);
        ivExtraMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //navigation.setSelectedItemId(R.id.navigation_extras);
                navigation.setSelectedItemId(R.id.nav_appbar);
//                Toast.makeText(getApplicationContext(),"Language Selection",Toast.LENGTH_SHORT).show();
                FragmentManager fm = getSupportFragmentManager();
                showLanguageDialog(fm);
            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragment);
        ft.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_matches:
                    setFragment(new MatchesFragment());
                    selectedTab = 0;
                    return true;
                case R.id.navigation_news:
                    setFragment(new NewsNavFragment());
                    selectedTab = 1;
                    return true;
                case R.id.navigation_extras:
                    setFragment(new ExtrasFragment());
                    selectedTab = 2;
                    return true;
            }
            return false;
        }

    };

    @Subscribe
    public void onScrollEventListener(ScrollEventBus busModel) {
        /*if (busModel.isScrollingUp()) {
            //navigation.clearAnimation();
            //navigation.animate().translationY(150);
            navigation.setVisibility(View.GONE);
        } else {
            //navigation.clearAnimation();
            //navigation.animate().translationY(0);
            navigation.setVisibility(View.VISIBLE);
        }*/
    }

    @Override
    public void onBackPressed() {
        if (selectedTab != 0) {
            navigation.setSelectedItemId(R.id.navigation_matches);
        } else {
            this.finish();
        }
    }

    private void parseArguments() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(Configuration.KEY_WEB_LINK)) {
            String webPageUrl = intent.getStringExtra(Configuration.KEY_WEB_LINK);
            Log.d(TAG, "parseArguments() webPageUrl: " + webPageUrl);
            prepareCustomTab(webPageUrl);
        }
    }

    private void checkFCMTokenStatus() {
        boolean isFCMTokenSentToServer = mSharedPreferences.getBoolean(
                Configuration.PREF_IS_FCM_TOKEN_SENT_TO_SERVER, true
        );
        Log.i(TAG, "checkFCMTokenStatus() isFCMTokenSentToServer: " + isFCMTokenSentToServer);
        if (isFCMTokenSentToServer) return; // Don't need do anything
        String fcmToken = mSharedPreferences.getString(Configuration.PREF_FCM_TOKEN, "");
        String deviceID = mSharedPreferences.getString(Configuration.PREF_UNIQUE_ID, "");

        viewModel.getTokenSentToServerData(fcmToken, deviceID).observe(
                this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(@Nullable Boolean isSent) {
                        Log.w(TAG, "onChanged() called with: isSent = [" + isSent + "]");
                        if (isSent != null && isSent) {
                            SharedPreferences.Editor editor = mSharedPreferences.edit();
                            editor.putBoolean(Configuration.PREF_IS_FCM_TOKEN_SENT_TO_SERVER, true);
                            editor.apply();
                        }
                    }
                }
        );
    }


    private void showLanguageDialog(FragmentManager fm) {
        LanguageSelectionDialog selectionDialog = new LanguageSelectionDialog();
        selectionDialog.setCancelable(false);
        selectionDialog.setSelectionListener(new LanguageSelectionDialog.LanguageSelectionListener() {
            @Override
            public void onLanguageSelected(String selectedLanguage) {
                if (this != null) {
                    refresh();
                }
            }
        });
        selectionDialog.show(fm, "language");
    }

    public void refresh() {

        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
        // Intent intent = getIntent();

//        overridePendingTransition(0, 0);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//        finish();
//        overridePendingTransition(0, 0);
//        startActivity(intent);
    }

}
