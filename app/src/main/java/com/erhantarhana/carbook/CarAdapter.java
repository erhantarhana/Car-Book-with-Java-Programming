package com.erhantarhana.carbook;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.erhantarhana.carbook.databinding.RecyclerRowBinding;

import java.util.ArrayList;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarHolder> {

    ArrayList<Car> carArrayList;

    public CarAdapter(ArrayList<Car> carArrayList) {
        this.carArrayList = carArrayList;
    }

    @NonNull
    @Override
    public CarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowBinding recyclerRowBinding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CarHolder(recyclerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CarHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.binding.recyclerViewTextView.setText(carArrayList.get(position).brand);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), CarActivity.class);
                intent.putExtra("info", "old");
                intent.putExtra("carId", carArrayList.get(position).id);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return carArrayList.size();
    }

    public class CarHolder extends RecyclerView.ViewHolder {
        private RecyclerRowBinding binding;
        public CarHolder(RecyclerRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
