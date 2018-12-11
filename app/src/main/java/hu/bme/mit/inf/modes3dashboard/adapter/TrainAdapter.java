package hu.bme.mit.inf.modes3dashboard.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hu.bme.mit.inf.modes3dashboard.R;
import hu.bme.mit.inf.modes3dashboard.controllableElements.Train;

public class TrainAdapter extends RecyclerView.Adapter<TrainAdapter.TrainViewHolder> {

    private final List<Train> trains;

    private TrainClickListener listener;

    public TrainAdapter(TrainClickListener listener) {
        this.listener = listener;
        trains = new ArrayList<>();
    }

    @NonNull
    @Override
    public TrainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View trainView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_train_list, parent, false);
        return new TrainViewHolder(trainView);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainViewHolder holder, int position) {
        Train train = trains.get(position);

        holder.nameTextView.setText(train.getName());
        holder.statusTextView.setText("TODO");
        holder.train = train;
    }

    @Override
    public int getItemCount() {
        return trains.size();
    }

    public interface TrainClickListener {
        void onItemChanged(Train item);
    }
    public void addItem(Train train) {
        trains.add(train);
        notifyItemInserted(trains.size() - 1);
    }

    public void update(List<Train> shoppingItems) {
        trains.clear();
        trains.addAll(shoppingItems);
        notifyDataSetChanged();
    }

    class TrainViewHolder extends RecyclerView.ViewHolder{
        TextView nameTextView;
        TextView statusTextView;

        Train train;

        public TrainViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.TrainNameTextView);
            statusTextView = itemView.findViewById(R.id.TrainStatusTextView);
        }

    }
}
