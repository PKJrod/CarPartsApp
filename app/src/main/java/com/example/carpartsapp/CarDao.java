package com.example.carpartsapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CarDao {

    @Insert
    void InsertCar(Car car);

    @Update
    void UpdateCar(Car car);

    @Delete
    void DeleteCar(Car car);

    @Query("SELECT * FROM cars")
    LiveData<List<Car>> getAllCars();

    @Query("SELECT * FROM cars WHERE CarModel = :carModel ")
    List<Car> findCar(String carModel);
}
