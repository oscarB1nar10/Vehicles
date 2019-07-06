package com.example.diannaliset.vehicles;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class ShowVehicle extends AppCompatActivity{
    Toolbar toolbar;
    TextView plaque,brand,model,numOfDoors,typeOfVehicle;
    private int tireColor,capoColor,doorColor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_vehicle);
        toolbar=findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Informacion");
        plaque=findViewById(R.id.plaque);
        brand=findViewById(R.id.brand);
        model=findViewById(R.id.model);
        numOfDoors=findViewById(R.id.num_of_doors);
        typeOfVehicle=findViewById(R.id.type_of_vehicle);
        addDataToElements();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_for_show_vehicle, menu);
        return true;
    }

    public void addDataToElements(){
        plaque.setText(getIntent().getExtras().getString("plaque"));
        brand.setText(getIntent().getExtras().getString("brand"));
        model.setText(getIntent().getExtras().getString("model"));
        numOfDoors.setText(""+getIntent().getExtras().getInt("num_of_doors"));
        typeOfVehicle.setText(getIntent().getExtras().getString("type_of_vehicle"));
        tireColor=getIntent().getExtras().getInt("tireColor");
        capoColor=getIntent().getExtras().getInt("capoColor");
        doorColor=getIntent().getExtras().getInt("doorColor");
    }

    public void updateVehicle(View v){
        Intent intent=new Intent(this,UpdateVehicle.class);

        intent.putExtra("plaque",plaque.getText().toString());
        intent.putExtra("brand",brand.getText().toString());
        intent.putExtra("model",model.getText().toString());
        intent.putExtra("num_of_doors",numOfDoors.getText().toString());
        intent.putExtra("type_of_vehicle",typeOfVehicle.getText().toString());

        startActivity(intent);

    }

    public void additionalFeatures(View v){

        Intent intent=new Intent(this,ShowVehicleFeatures.class);

        intent.putExtra("tireColor",tireColor);
        intent.putExtra("capoColor",capoColor);
        intent.putExtra("doorColor",doorColor);

        startActivity(intent);


    }

    public void deleteVehicle(View v){

        DeleteVehicle deleteVehicle=new DeleteVehicle(plaque.getText().toString(),this);
        deleteVehicle.deleteV();

    }

    public void returToHome(View v){
        Intent intent= new Intent(getApplicationContext(), VehiclesList.class);
        startActivity(intent);
    }
}
