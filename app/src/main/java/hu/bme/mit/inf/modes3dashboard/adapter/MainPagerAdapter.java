package hu.bme.mit.inf.modes3dashboard.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import hu.bme.mit.inf.modes3dashboard.fragments.SectionListFragment;
import hu.bme.mit.inf.modes3dashboard.fragments.TrainListFragment;
import hu.bme.mit.inf.modes3dashboard.fragments.TurnoutListFragment;

public class MainPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public MainPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
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
        return mNumOfTabs;
    }
}
