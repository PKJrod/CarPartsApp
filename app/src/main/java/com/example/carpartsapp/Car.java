package com.example.carpartsapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Cars")
public class Car {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int CarModelId;

    @NonNull
    @ColumnInfo(name = "carManufacture")
    private String Manufacture;
    @NonNull
    private int CarYear;
    @NonNull
    private String CarModel;
    private String CarEngine;

    public Car(String Manufacture, int CarYear, String CarModel, String CarEngine) {
        this.CarModelId = CarModelId;
        this.Manufacture = Manufacture;
        this.CarYear = CarYear;
        this.CarModel = CarModel;
        this.CarEngine = CarEngine;
    }

    public int getCarModelId() {
        return this.CarModelId;
    }

    public String getManufacture() {
        return this.Manufacture;
    }

    public int getCarYear() {
        return this.CarYear;
    }

    public String getCarModel() {
        return this.CarModel;
    }

    public String getCarEngine() {
        return this.CarEngine;
    }

    public void setCarModelId(int carModelId) {
        this.CarModelId = carModelId;
    }

    public void setManufacture(String manufacture) { this.Manufacture = manufacture; }

    public void setCarYear(int carYear) {
        this.CarYear = carYear;
    }

    public void setCarModel(String carModel) {
        this.CarModel = carModel;
    }

    public void setCarEngine(String carEngine) {
        this.CarEngine = carEngine;
    }
}
