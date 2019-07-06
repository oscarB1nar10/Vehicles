package com.example.diannaliset.vehicles;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.diannaliset.vehicles.handlerDB.Vehicle;
import com.example.diannaliset.vehicles.handlerDB.VehicleDB_Helper;
import com.example.diannaliset.vehicles.handlerDBR.SyncUpWhitRemoteDBR;

import java.util.ArrayList;

public class VehiclesList extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<Vehicle> arrayVehicles;
    private String plaque[];
    private Toolbar toolbar;
    private VehicleDB_Helper vehicleDB_helper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehicles_list);

        //view references
        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        listView=findViewById(R.id.vehicles_list);
        readData();
    }

    public void readData(){
        Vehicle vehicle=new Vehicle();
        vehicle.setPlaque("wew");
        vehicle.setBrand("sds");
        vehicle.setModel("wesfdfw");
        vehicle.setNumOfDoors(2);
        vehicle.setTypeOfVehicle("wefw");
        vehicle.setTireColor(2);
        vehicle.setCapoColor(3);
        vehicle.setDoorColor(4);
        vehicleDB_helper=new VehicleDB_Helper(this);
        vehicleDB_helper.openDB();
        //vehicleDB_helper.dataRegister();
        arrayVehicles=vehicleDB_helper.readData();
        vehicleDB_helper.closeDB();
       plaque=new String[arrayVehicles.size()];
        readPlaques();
        arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,plaque);
       listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent=new Intent(getBaseContext(),ShowVehicle.class);
                intent.putExtra("plaque",arrayVehicles.get(position).getPlaque());
                intent.putExtra("brand",arrayVehicles.get(position).getBrand());
                intent.putExtra("model",arrayVehicles.get(position).getModel());
                intent.putExtra("num_of_doors",arrayVehicles.get(position).getNumOfDoors());
                intent.putExtra("type_of_vehicle",arrayVehicles.get(position).getTypeOfVehicle());
                intent.putExtra("tireColor",arrayVehicles.get(position).getTireColor());
                intent.putExtra("capoColor",arrayVehicles.get(position).getCapoColor());
                intent.putExtra("doorColor",arrayVehicles.get(position).getDoorColor());

                startActivity(intent);
            }
        });

    }

    public void readPlaques(){

        for(int i=0; i<plaque.length; i++){
            plaque[i]="vehiculo: "+arrayVehicles.get(i).getPlaque();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        Toast.makeText(this,"Bienvenido...",Toast.LENGTH_LONG).show();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i= item.getItemId();
            switch(i){
                case R.id.action_settings1:
                      Intent intent= new Intent(this,VehicleDriver.class);
                      startActivity(intent);
                    break;
                case R.id.sync_up_db_vehicles:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            SyncUpWhitRemoteDBR syncUpWhitRemoteDBR=new SyncUpWhitRemoteDBR(getBaseContext());
                            syncUpWhitRemoteDBR.syncUp();

                        }
                    }).start();
                    //Toast.makeText(getBaseContext(),"Se han sincronizado los datos correctamente",Toast.LENGTH_LONG).show();
                    break;


            }

        return true;
    }
}
