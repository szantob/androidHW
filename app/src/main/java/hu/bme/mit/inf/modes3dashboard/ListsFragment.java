package hu.bme.mit.inf.modes3dashboard;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ListsFragment extends Fragment {

    public ListsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        Log.i("myDebug","ListsFragment.onCreateView()");
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_lists, container, false);
        return rootView;
    }
}
