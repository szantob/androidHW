package hu.bme.mit.inf.modes3dashboard.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hu.bme.mit.inf.modes3dashboard.R;

public class TrainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        Log.i("myDebug","TrainFragment.onCreateView()");
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_train, container, false);
        return rootView;
    }

}
