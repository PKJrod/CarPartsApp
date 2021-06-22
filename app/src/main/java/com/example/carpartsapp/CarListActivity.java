package com.example.carpartsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carpartsapp.ui.main.MainViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class CarListActivity extends AppCompatActivity {

    private RecyclerView carList;
    private static final int ADD_CAR_REQUEST = 1;
    private static final int EDIT_CAR_REQUEST = 2;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_list_activity);

        carList = findViewById(R.id.carList);
        FloatingActionButton fab = findViewById(R.id.idFABAdd);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CarListActivity.this, NewCarActivity.class);
                startActivityForResult(intent, ADD_CAR_REQUEST);
            }
        });

        carList.setLayoutManager(new LinearLayoutManager(this));
        carList.setHasFixedSize(true);

        final CarListAdapter adapter = new CarListAdapter();

        carList.setAdapter(adapter);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        mainViewModel.getAllCars().observe(this, new Observer<List<Car>>() {
            @Override
            public void onChanged(List<Car> models) {
                adapter.submitList(models);
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

               @Override
               public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
               MainViewModel.deleteCar(adapter.getCarAt(viewHolder.getAbsoluteAdapterPosition()));
               Toast.makeText(CarListActivity.this, "Car deleted", Toast.LENGTH_SHORT).show();
            }
        }).
                // below line is use to attach this to recycler view.
                        attachToRecyclerView(carList);
        // below line is use to set item click listener for our item of recycler view.
        adapter.setOnItemClickListener(new CarListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Car model) {

                Intent intent = new Intent(CarListActivity.this, NewCarActivity.class);
                intent.putExtra(NewCarActivity.EXTRA_CAR_MODEL_ID, model.getCarModelId());
                intent.putExtra(NewCarActivity.EXTRA_CAR_MANUFACTURE, model.getManufacture());
                intent.putExtra(String.valueOf(NewCarActivity.EXTRA_CAR_YEAR), model.getCarYear());
                intent.putExtra(NewCarActivity.EXTRA_CAR_MODEL, model.getCarModel());
                intent.putExtra(NewCarActivity.EXTRA_CAR_ENGINE, model.getCarEngine());

                // below line is to start a new activity and
                // adding a edit course constant.
                startActivityForResult(intent, EDIT_CAR_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_CAR_REQUEST && resultCode == RESULT_OK) {
            String carManufacturer = data.getStringExtra(NewCarActivity.EXTRA_CAR_MANUFACTURE);
            int carYear = data.getIntExtra(NewCarActivity.EXTRA_CAR_YEAR, -1);
            String carModel = data.getStringExtra(NewCarActivity.EXTRA_CAR_MODEL);
            String carEngine = data.getStringExtra(NewCarActivity.EXTRA_CAR_ENGINE);

            Car params = new Car(carManufacturer, carYear, carModel, carEngine);
            MainViewModel.insertCar(params);
            Toast.makeText(this, "Car saved", Toast.LENGTH_SHORT).show();

        }
        else if (requestCode == EDIT_CAR_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(NewCarActivity.EXTRA_CAR_MODEL_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Car can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String carManufacturer = data.getStringExtra(NewCarActivity.EXTRA_CAR_MANUFACTURE);
            int carYear = data.getIntExtra(NewCarActivity.EXTRA_CAR_YEAR, - 1);
            String carModel = data.getStringExtra(NewCarActivity.EXTRA_CAR_MODEL);
            String carEngine = data.getStringExtra(NewCarActivity.EXTRA_CAR_ENGINE);
            Car params = new Car(carManufacturer, carYear, carModel, carEngine);

            params.setCarModelId(id);
            mainViewModel.updateCar(params);
            Toast.makeText(this, "Car updated", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Car was not saved", Toast.LENGTH_SHORT).show();
        }
    }
}
