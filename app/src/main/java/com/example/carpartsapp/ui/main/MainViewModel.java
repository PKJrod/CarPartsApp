package com.example.carpartsapp.ui.main;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.carpartsapp.Car;
import com.example.carpartsapp.CarRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel
    private static CarRepository repository;
    private LiveData<List<Car>> allCars;
    private MutableLiveData<List<Car>> searchModel;

    public MainViewModel(Application application) {
        super(application);
        repository = new CarRepository(application);
        allCars = repository.getAllCars();
        searchModel = repository.getSearchResults();
    }

    public MutableLiveData<List<Car>> getSearchModel() {return searchModel;}

    public LiveData<List<Car>> getAllCars() {return allCars;}

    public static void insertCar(Car car) {repository.InsertCar(car);}

    public void updateCar(Car car) {repository.UpdateCar(car);}

    public static void deleteCar(Car car) {repository.DeleteCar(car);}

    public void findCar(String model){repository.findCarModel(model);}

}