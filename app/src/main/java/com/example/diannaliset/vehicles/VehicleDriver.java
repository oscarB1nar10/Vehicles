package com.example.diannaliset.vehicles;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.diannaliset.vehicles.handlerDB.Vehicle;
import com.example.diannaliset.vehicles.handlerDB.VehicleDB_Helper;

public class VehicleDriver extends AppCompatActivity {
    private EditText plaque,brand,model,numOfDoors,typeOfVehicles;
    private Vehicle vehicle;
    private Button registerButton,queryButton;
    private VehicleDB_Helper vehicleDB_helper;
    private ImageView features;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //references to views
        plaque= findViewById(R.id.plaque);
        brand=findViewById(R.id.brand);
        model=findViewById(R.id.model);
        numOfDoors=findViewById(R.id.num_of_doors);
        typeOfVehicles=findViewById(R.id.type_of_vehicle);
        features=findViewById(R.id.features);



    }


    public void addFeatures(View v){
        Intent intent=new Intent(this,VehicleFeatures.class);

        intent.putExtra("plaque",plaque.getText().toString());
        intent.putExtra("brand",brand.getText().toString());
        intent.putExtra("model",model.getText().toString());
        intent.putExtra("num_of_doors",Integer.parseInt(numOfDoors.getText().toString()));
        intent.putExtra("type_of_vehicle",typeOfVehicles.getText().toString());

        startActivity(intent);
    }




}
