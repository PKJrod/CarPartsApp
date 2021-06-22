package com.example.carpartsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NewCarActivity extends AppCompatActivity {

    private EditText carManufacturerAddEd, carYearAddEd,
                carModelAddEd, carEngineAddEd;
    private Button saveCarCmd;

    public static final String EXTRA_CAR_MODEL_ID = "Extra_Car_Model_Id";
    public static final String EXTRA_CAR_MANUFACTURE = "Extra_Car_Manufacture";
    public static final String EXTRA_CAR_YEAR = "02";
    public static final String EXTRA_CAR_MODEL = "Extra_Car_Model";
    public static final String EXTRA_CAR_ENGINE = "Extra_Car_Engine";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_car);

        carManufacturerAddEd = findViewById(R.id.addEdCarManufacturer);
        carYearAddEd = findViewById(R.id.addEdCarYear);
        carModelAddEd = findViewById(R.id.addEdCarModel);
        carEngineAddEd = findViewById(R.id.addEdCarEngine);
        saveCarCmd = findViewById(R.id.cmdSaveCar);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_CAR_MODEL_ID)) {
            carManufacturerAddEd.setText(intent.getStringExtra(EXTRA_CAR_MANUFACTURE));
            carYearAddEd.setText(String.valueOf(intent.getIntExtra(EXTRA_CAR_YEAR, -1)));
            carModelAddEd.setText(intent.getStringExtra(EXTRA_CAR_MODEL));
            carEngineAddEd.setText(intent.getStringExtra(EXTRA_CAR_ENGINE));
        }

        saveCarCmd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String CarManufacture = carManufacturerAddEd.getText().toString();
                int CarYear = Integer.parseInt((carYearAddEd.getText().toString()));
                String CarModel = carModelAddEd.getText().toString();
                String CarEngine = carEngineAddEd.getText().toString();
                if (CarManufacture.isEmpty() || CarYear < 0 ||
                        CarModel.isEmpty() || CarEngine.isEmpty()) {
                    Toast.makeText(NewCarActivity.this,
                            "Please fill out all the fields.", Toast.LENGTH_SHORT).show();
                    return;
                }

                saveCar(CarManufacture, CarYear, CarModel, CarEngine);
            }
        });
    }

    private void saveCar(String CarManufacture, int CarYear, String CarModel, String CarEngine) {
        Intent data = new Intent();

        data.putExtra(EXTRA_CAR_MANUFACTURE, CarManufacture);
        data.putExtra(EXTRA_CAR_YEAR, CarYear);
        data.putExtra(EXTRA_CAR_MODEL, CarModel);
        data.putExtra(EXTRA_CAR_ENGINE, CarEngine);

        int id = getIntent().getIntExtra(EXTRA_CAR_MODEL_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_CAR_MODEL_ID, id);
        }

        setResult(RESULT_OK, data);

        Toast.makeText(this, "Car has been saved. ", Toast.LENGTH_SHORT).show();
    }
}