package com.example.carpartsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class CarListAdapter extends ListAdapter<Car, CarListAdapter.ViewHolder> {

    private OnItemClickListener listener;

    CarListAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Car> DIFF_CALLBACK
            = new DiffUtil.ItemCallback<Car>() {
        @Override
        public boolean areItemsTheSame(Car oldCar, Car newCar) {
            return oldCar.getCarModelId() == newCar.getCarModelId();
        }

        @Override
        public boolean areContentsTheSame(Car oldCar, Car newCar) {
            return oldCar.getManufacture().equals(newCar.getManufacture()) &&
                    oldCar.getCarModel().equals(newCar.getCarModel()) &&
                    oldCar.getCarYear() == newCar.getCarYear() &&
                    oldCar.getCarEngine().equals(newCar.getCarEngine());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View car = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.car_items_list, parent, false);
        return new ViewHolder(car);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Car model = getCarAt(position);
        holder.carManufacturer.setText(model.getManufacture());
        holder.carYear.setText(String.valueOf(model.getCarYear()));
        holder.carModel.setText(model.getCarModel());
        holder.carEngine.setText(model.getCarEngine());
    }

    public Car getCarAt(int position) {
        return getItem(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView carManufacturer, carYear, carModel, carEngine;

        ViewHolder(@NonNull View carView) {
            super(carView);

            carManufacturer = carView.findViewById(R.id.carManufacturer);
            carYear = carView.findViewById(R.id.carYear);
            carModel = carView.findViewById(R.id.carModel);
            carEngine = carView.findViewById(R.id.carEngine);

            carView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    int position = getAbsoluteAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Car model);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
