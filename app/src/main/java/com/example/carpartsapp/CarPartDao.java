package com.example.carpartsapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CarPartDao {

    @Insert
    void InsertCarPart(CarPart carPart);

    @Update
    void UpdateCarPart(CarPart... carParts);

    @Delete
    void DeleteCarPart(CarPart... carParts);

    @Query("SELECT * FROM carpart")
    List<CarPart> getAllCarParts();

    @Query("SELECT * FROM carpart WHERE CarPartId = :carPartId")
    List<CarPart> findCarParts(final int carPartId);
}
