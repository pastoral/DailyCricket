package me.abir.dailycricketbd.view.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import me.abir.dailycricketbd.BaseActivity;
import me.abir.dailycricketbd.R;
import me.abir.dailycricketbd.util.Configuration;
import me.abir.dailycricketbd.view.fragment.AboutUsFragment;
import me.abir.dailycricketbd.view.fragment.ContactUsFragment;
import me.abir.dailycricketbd.view.fragment.ExtrasFragment;
import me.abir.dailycricketbd.view.fragment.PollFragment;

/**
 * Created by Abir on 08-Nov-17.
 */

public class FragmentHolderActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_holder);
        extractIntentInfo();
    }

    private void extractIntentInfo() {
        Intent intent = getIntent();
        if (intent.hasExtra(Configuration.EXTRAS_FRAGMENT_TAG)) {
            String fragmentTag = intent.getStringExtra(Configuration.EXTRAS_FRAGMENT_TAG);
            showFragment(fragmentTag);
        } else {
            finish();
        }
    }

    private void showFragment(String fragmentTag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        Fragment fragment;
        switch (fragmentTag) {
            case Configuration.FRAGMENT_TAG_POLL:
                fragment = new PollFragment();
                setToolbarTitle(R.string.extras_polls);
                break;
            case Configuration.FRAGMENT_TAG_CONTACT_US:
                fragment = new ContactUsFragment();
                setToolbarTitle(R.string.extras_contact_us);
                break;
            case Configuration.FRAGMENT_TAG_ABOUT_US:
                fragment = new AboutUsFragment();
                setToolbarTitle(R.string.extras_about_us);
                break;
            default:
                fragment = new ExtrasFragment();
                break;
        }

        if (getFragmentManager().findFragmentByTag(Configuration.EXTRAS_FRAGMENT_TAG) == null) {
            transaction.add(R.id.container, fragment, Configuration.EXTRAS_FRAGMENT_TAG).commit();
        } else {
            transaction.replace(R.id.container, fragment, Configuration.EXTRAS_FRAGMENT_TAG).commit();
        }
    }

    private void setToolbarTitle(int extras_polls) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(extras_polls);
    }


}
