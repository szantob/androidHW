package hu.bme.mit.inf.modes3dashboard.fragments;

import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hu.bme.mit.inf.modes3dashboard.R;
import hu.bme.mit.inf.modes3dashboard.data.DatabaseConnector;
import hu.bme.mit.inf.modes3dashboard.data.ModesDatabase;
import hu.bme.mit.inf.modes3dashboard.data.Train;

public class TrainListFragment extends Fragment implements TrainClickListener, DatabaseConnector.TrainDataReader {
    private RecyclerView recyclerView;

    private DatabaseConnector connector;
    private static TrainAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_train_list, container, false);

        DatabaseConnector.initialize(getActivity());
        connector = DatabaseConnector.getInstance();

        initRecyclerView(view);
        return view;
    }

    private void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.TrainListRecyclerView);
        adapter = new TrainAdapter(this);
        connector.loadTrains(this);
        //loadItemsInBackground();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemChanged(Train train) {

    }
    @Override
    public void getTrainData(List<Train> trains) {
        adapter.update(trains);
    }

    public static class TrainAdapter
            extends RecyclerView.Adapter<TrainAdapter.TrainViewHolder> {

        private final List<Train> trains;

        private TrainClickListener listener;

        public TrainAdapter(TrainClickListener listener) {
            this.listener = listener;
            trains = new ArrayList<>();
        }

        @NonNull
        @Override
        public TrainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.train_list_element, parent, false);
            LinearLayout container = itemView.findViewById(R.id.container);
            container.setClickable(true);
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Long trainId = Long.decode(((TextView)view.findViewById(R.id.TrainIdTextView)).getText().toString());
                    //TODO show fragment
                }
            });
            return new TrainViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull TrainViewHolder holder, int position) {
            Train item = trains.get(position);
            holder.nameTextView.setText(item.name);
            holder.idTextView.setText(/*Long.toString(item.modesId)*/"0");
            holder.statusTextView.setText("Disconnected");

            holder.item = item;
        }

        @Override
        public int getItemCount() {
            return trains.size();
        }


        class TrainViewHolder extends RecyclerView.ViewHolder {

            TextView nameTextView;
            TextView idTextView;
            TextView statusTextView;

            Train item;

            TrainViewHolder(View itemView) {
                super(itemView);
                nameTextView = itemView.findViewById(R.id.TrainNameTextView);
                idTextView = itemView.findViewById(R.id.TrainIdTextView);
                statusTextView = itemView.findViewById(R.id.TrainStatusTextView);
            }
        }
        public void addItem(Train item) {
            trains.add(item);
            notifyItemInserted(trains.size() - 1);
        }

        public void update(List<Train> Trains) {
            trains.clear();
            trains.addAll(Trains);
            notifyDataSetChanged();
        }
    }
}

interface TrainClickListener{
    void onItemChanged(Train train);
}