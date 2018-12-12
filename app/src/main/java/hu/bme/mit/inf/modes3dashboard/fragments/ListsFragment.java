package hu.bme.mit.inf.modes3dashboard.fragments;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hu.bme.mit.inf.modes3dashboard.R;

public class ListsFragment extends Fragment {
    private static final int NUM_PAGES = 3;
    private ViewPager mPager;
    private android.support.v4.view.PagerAdapter mPagerAdapter;

    public ListsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        Log.i("myDebug","ListsFragment.onCreateView()");
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_lists, container, false);
        TabLayout tabs = rootView.findViewById(R.id.ListsFragmantTabLayout);
        tabs.addTab(tabs.newTab().setText("Tab 1"));
        tabs.addTab(tabs.newTab().setText("Tab 2"));
        tabs.addTab(tabs.newTab().setText("Tab 3"));
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) rootView.findViewById(R.id.ListsFragmantPager);
        mPagerAdapter = new PagerAdapter(getChildFragmentManager());
        mPager.setAdapter(mPagerAdapter);



        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        return rootView;
    }
    private class PagerAdapter extends FragmentStatePagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new TrainListFragment();
                case 1:
                    return new SectionListFragment();
                case 2:
                    return new TurnoutListFragment();
                default:
                    return new TrainListFragment();
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
