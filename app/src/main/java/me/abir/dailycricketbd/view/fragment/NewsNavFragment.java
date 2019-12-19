package me.abir.dailycricketbd.view.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.abir.dailycricketbd.R;

/**
 * Created by Abir on 27-Oct-17.
 */

public class NewsNavFragment extends Fragment {

    private TabLayout tabNewsNav;
    private ViewPager pagerNewsNav;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_nav, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        pagerNewsNav = view.findViewById(R.id.viewpagerNewsNav);
        setUpViewPager(pagerNewsNav);
        tabNewsNav = view.findViewById(R.id.tabLayoutNewsNav);
        tabNewsNav.setupWithViewPager(pagerNewsNav);
    }

    private void setUpViewPager(ViewPager pagerMatches) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new NewsFragment(), getActivity().getString(R.string.tab_news));
        adapter.addFragment(new ImageFragment(), getActivity().getString(R.string.tab_images));
        adapter.addFragment(new VideoFragment(), getActivity().getString(R.string.tab_videos));

        pagerMatches.setAdapter(adapter);
        pagerMatches.setOffscreenPageLimit(2);
        pagerMatches.setCurrentItem(0);
    }


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

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
