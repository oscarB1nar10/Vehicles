package com.example.diannaliset.vehicles;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diannaliset.vehicles.handlerDB.Vehicle;
import com.example.diannaliset.vehicles.handlerDB.VehicleDB_Helper;

public class UpdateVehicle extends AppCompatActivity {
    private TextView plaque;
    private EditText brand,model,numOfDoors,typeOfVehicle;
    private Vehicle vehicle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_vehicle);

        //getSupportActionBar().setTitle("Actualizacion");
        vehicle=new Vehicle();
        plaque=findViewById(R.id.plaque);
        brand=findViewById(R.id.brand);
        model=findViewById(R.id.model);
        numOfDoors=findViewById(R.id.num_of_doors);
        typeOfVehicle=findViewById(R.id.type_of_vehicle);
        obtainVehiclesData();
    }

    public void obtainVehiclesData(){
        plaque.setText(getIntent().getExtras().getString("plaque"));
        brand.setText(getIntent().getExtras().getString("brand"));
        model.setText(getIntent().getExtras().getString("model"));
        numOfDoors.setText(""+getIntent().getExtras().getString("num_of_doors"));
        Toast.makeText(this,"number of doors: "+getIntent().getExtras().getString("num_of_doors"),Toast.LENGTH_LONG).show();
        typeOfVehicle.setText(getIntent().getExtras().getString("type_of_vehicle"));
    }

    public void updateVehicle(View v){
        VehicleDB_Helper vehicleDB_helper=new VehicleDB_Helper(this,vehicle);
        extractData();

        vehicleDB_helper.openDB();
        vehicleDB_helper.updateVehicle();
        vehicleDB_helper.closeDB();

        Toast.makeText(this,"Actualizacion exitoza!",Toast.LENGTH_LONG).show();

        android.os.Handler handler=new android.os.Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(getApplicationContext(), VehiclesList.class);
                startActivity(intent);
            }
        },2000);


    }

    public void extractData(){

        vehicle.setPlaque(plaque.getText().toString());
        vehicle.setBrand(brand.getText().toString());
        vehicle.setModel(model.getText().toString());
        vehicle.setNumOfDoors(Integer.parseInt(numOfDoors.getText().toString()));
        vehicle.setTypeOfVehicle(typeOfVehicle.getText().toString());
    }

    public void returToHome(View v){
        Intent intent= new Intent(getApplicationContext(), VehiclesList.class);
        startActivity(intent);
    }
}
