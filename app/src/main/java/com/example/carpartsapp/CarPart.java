package com.example.carpartsapp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "CarPart", foreignKeys = @ForeignKey(entity = Car.class,
                parentColumns = "CarModelId", childColumns = "CarPartId"))
public class CarPart {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int CarPartId;

    @NonNull
    private int CarModelId;

    private String CarPartName;
    private String CarPartCategory;
    private String CarPartSubCategory;
    private int CarPartPrice;

    public CarPart(String CarPartName, String CarPartCategory, String CarPartSubCategory
                    , int CarPartPrice) {
        this.CarModelId = CarModelId;
        this.CarPartId = CarPartId;
        this.CarPartName = CarPartName;
        this.CarPartCategory = CarPartCategory;
        this.CarPartSubCategory = CarPartSubCategory;
        this.CarPartPrice = CarPartPrice;
    }

    public int getCarModelId() {
        return this.CarModelId;
    }

    public int getCarPartId() { return this.CarPartId; }

    public String getCarPartName() { return this.CarPartName; }

    public String getCarPartCategory() {
        return this.CarPartCategory;
    }

    public String getCarPartSubCategory() { return this.CarPartSubCategory; }

    public int CarPartPrice() { return this.CarPartPrice; }

    public void setCarModelId(int carModelId) {
        this.CarModelId = carModelId;
    }

    public void setCarPartId(int carPartId) {
        this.CarPartId = carPartId;
    }

    public void setCarPartName(String carPartName) { this.CarPartName = carPartName; }

    public void setCarPartCategory(String carPartCategory) { this.CarPartCategory = carPartCategory; }

    public void setCarPartSubCategory(String carPartSubCategory) { this.CarPartSubCategory = carPartSubCategory; }

    public void setCarPartPrice(int carPartPrice) { this.CarPartPrice = carPartPrice; }
}
