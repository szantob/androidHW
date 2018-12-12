package hu.bme.mit.inf.modes3dashboard.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hu.bme.mit.inf.modes3dashboard.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SectionListFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_section_list, container, false);
    }

}
