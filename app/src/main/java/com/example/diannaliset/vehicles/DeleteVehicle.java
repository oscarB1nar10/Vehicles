package com.example.diannaliset.vehicles;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.diannaliset.vehicles.handlerDB.Vehicle;
import com.example.diannaliset.vehicles.handlerDB.VehicleDB_Helper;

public class DeleteVehicle {
    private String plaque;
    private Vehicle vehicle;
    private Context context;



    public DeleteVehicle(String plaque, Context context){
        this.plaque=plaque;
        this.context=context;
        vehicle=new Vehicle();
    }

    public void deleteV(){
        vehicle.setPlaque(plaque);
        VehicleDB_Helper vehicleDB_helper=new VehicleDB_Helper(context,vehicle);

        vehicleDB_helper.openDB();
        vehicleDB_helper.deleteVehicle();
        vehicleDB_helper.closeDB();

        Toast.makeText(context,"Eliminacion exitoza!",Toast.LENGTH_LONG).show();
        android.os.Handler handler=new android.os.Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(context, VehiclesList.class);
                context.startActivity(intent);
            }
        },2000);
    }




}
