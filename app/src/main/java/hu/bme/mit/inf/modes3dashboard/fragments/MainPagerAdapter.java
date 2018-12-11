package hu.bme.mit.inf.modes3dashboard.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainPagerAdapter extends FragmentPagerAdapter {
    private static final int NUM_PAGES = 1;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public MainPagerAdapter(FragmentManager supportFragmentManager, int tabCount) {
        super(supportFragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MainFragment();
            case 1:
                return  new TrainFragment();
            default:
                return new MainFragment();
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}