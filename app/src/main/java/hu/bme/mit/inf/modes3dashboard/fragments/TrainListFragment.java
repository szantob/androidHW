package hu.bme.mit.inf.modes3dashboard.fragments;


import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import hu.bme.mit.inf.modes3dashboard.MainActivity;
import hu.bme.mit.inf.modes3dashboard.R;
import hu.bme.mit.inf.modes3dashboard.adapter.TrainAdapter;
import hu.bme.mit.inf.modes3dashboard.controllableElements.ModesDatabase;
import hu.bme.mit.inf.modes3dashboard.controllableElements.Train;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrainListFragment extends Fragment implements TrainAdapter.TrainClickListener {
    private RecyclerView recyclerView;
    private TrainAdapter adapter;

    private ModesDatabase database;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_train_list, container, false);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = Room.databaseBuilder(
                getActivity().getApplicationContext(),
                ModesDatabase.class,
                "modesDatabase"
        ).build();

        initRecyclerView();
    }
    private void initRecyclerView() {
        recyclerView = recyclerView.findViewById(R.id.TrainListRecyclerView);
        adapter = new TrainAdapter(this);
        loadItemsInBackground();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setAdapter(adapter);
    }

    private void loadItemsInBackground() {
        new AsyncTask<Void, Void, List<Train>>() {

            @Override
            protected List<Train> doInBackground(Void... voids) {
                return database.trainDao().getAll();
            }

            @Override
            protected void onPostExecute(List<Train> trains) {
                adapter.update(trains);
            }
        }.execute();
    }

    @Override
    public void onItemChanged(final Train train) {
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... voids) {
                database.trainDao().update(train);
                return true;
            }

            @Override
            protected void onPostExecute(Boolean isSuccessful) {
                Log.d("MainActivity", "ShoppingItem update was successful");
            }
        }.execute();
    }
}
