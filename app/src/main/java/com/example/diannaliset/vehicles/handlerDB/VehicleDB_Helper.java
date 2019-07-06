package com.example.diannaliset.vehicles.handlerDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.diannaliset.vehicles.handlerDB.Vehicle;
import com.example.diannaliset.vehicles.handlerDB.VehicleSchema;

import java.util.ArrayList;

/*7
    This class extends of SQLiteOpenHelper that  it provide us
    whit all methods by handler the  data base built
 */

public class VehicleDB_Helper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Vehicles.db";
    public Vehicle vehicle;

    public VehicleDB_Helper(Context context, Vehicle vehicle){
        super(context,DATABASE_NAME,null, DATABASE_VERSION);
        this.vehicle=vehicle;

    }
    public VehicleDB_Helper(Context context){
        super(context,DATABASE_NAME,null, DATABASE_VERSION);
    }

    /*
        This method allow us build the tables when build firt time the data base.
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        System.out.println("Ingresando al Oncreate"+sqLiteDatabase.getPath());

            sqLiteDatabase.execSQL("CREATE TABLE " + VehicleSchema.VehicleColums.TABLE_NAME + " ("
                    + VehicleSchema.VehicleColums.PLAQUE + " TEXT PRIMARY KEY ,"
                    + VehicleSchema.VehicleColums.BRAND + " TEXT NOT NULL,"
                    + VehicleSchema.VehicleColums.MODEL + " TEXT NOT NULL,"
                    + VehicleSchema.VehicleColums.NUM_OF_DOORS + " INTEGER NOT NULL,"
                    + VehicleSchema.VehicleColums.TYPE_OF_VEHICLE + " TEXT NOT NULL,"
                    + VehicleSchema.VehicleColums.TIRE_COLOR + " INTEGER NOT NULL,"+
                      VehicleSchema.VehicleColums.CAPO_COLOR + " INTEGER NOT NULL,"+
                      VehicleSchema.VehicleColums.DOOR_COLOR + " INTEGER NOT NULL"+")");


    }
    /*
        this method allow us insert a data set in the data base
     */
    public  void dataRegister(){
        //Value container
        ContentValues contentValues=new ContentValues();
        //values
        contentValues.put(VehicleSchema.VehicleColums.PLAQUE, vehicle.getPlaque());
        contentValues.put(VehicleSchema.VehicleColums.BRAND, vehicle.getBrand());
        contentValues.put(VehicleSchema.VehicleColums.MODEL, vehicle.getModel());
        contentValues.put(VehicleSchema.VehicleColums.NUM_OF_DOORS, vehicle.getNumOfDoors());
        contentValues.put(VehicleSchema.VehicleColums.TYPE_OF_VEHICLE, vehicle.getTypeOfVehicle());
        contentValues.put(VehicleSchema.VehicleColums.TIRE_COLOR, vehicle.getTireColor());
        contentValues.put(VehicleSchema.VehicleColums.CAPO_COLOR, vehicle.getCapoColor());
        contentValues.put(VehicleSchema.VehicleColums.DOOR_COLOR, vehicle.getDoorColor());
        this.getWritableDatabase().insert(VehicleSchema.VehicleColums.TABLE_NAME, null, contentValues);


    }

    /*
        This method allow us read a data set from data base
     */
    public ArrayList<Vehicle> readData() {

        ArrayList<Vehicle> queryDate=new ArrayList<Vehicle>();
        int iterator=0;
        System.out.println("ingresando....."+VehicleSchema.VehicleColums.TABLE_NAME);

        Cursor c= getReadableDatabase().query(VehicleSchema.VehicleColums.TABLE_NAME,
                                              null,
                                            null,
                                            null,
                                            null,
                                            null,
                                            null);
        System.out.println("El numero total de resgistro es: "+c.getCount());
        if(c.getCount()!=0) {
            while (c.moveToNext()) {
                Vehicle vehicle=new Vehicle();
                vehicle.setPlaque(c.getString(c.getColumnIndex(VehicleSchema.VehicleColums.PLAQUE)));
                vehicle.setBrand(c.getString(c.getColumnIndex(VehicleSchema.VehicleColums.BRAND)));
                vehicle.setModel(c.getString(c.getColumnIndex(VehicleSchema.VehicleColums.MODEL)));
                vehicle.setNumOfDoors(c.getInt(c.getColumnIndex(VehicleSchema.VehicleColums.NUM_OF_DOORS)));
                vehicle.setTypeOfVehicle(c.getString(c.getColumnIndex(VehicleSchema.VehicleColums.TYPE_OF_VEHICLE)));
                vehicle.setTireColor(c.getInt(c.getColumnIndex(VehicleSchema.VehicleColums.TIRE_COLOR)));
                vehicle.setCapoColor(c.getInt(c.getColumnIndex(VehicleSchema.VehicleColums.CAPO_COLOR)));
                vehicle.setDoorColor(c.getInt(c.getColumnIndex(VehicleSchema.VehicleColums.DOOR_COLOR)));
                queryDate.add(vehicle);
                iterator++;
                // Acciones...
            }
        }
      return queryDate;
     }

    /*
        update the vehicle data, specifically the fields (brand,model,numOfDoors,typeOfVehicle)
     */
     public  void updateVehicle(){
        String updateStament="UPDATE "+ VehicleSchema.VehicleColums.TABLE_NAME+" SET brand='"+vehicle.getBrand()+"'," +
                              "model='"+vehicle.getModel()+"',numOfDoors="+vehicle.getNumOfDoors()+",typeOfVehicle='"+vehicle.getTypeOfVehicle()+"' " +
                              " WHERE plaque='"+vehicle.getPlaque()+"'";
         System.out.println(updateStament);

        this.getWritableDatabase().execSQL(updateStament);
     }

     /*
       Delete one vehicle data throught (plaque) field
      */

     public void deleteVehicle(){
         String deleteStament="DELETE FROM "+VehicleSchema.VehicleColums.TABLE_NAME+" WHERE plaque='"+vehicle.getPlaque()+"'";

         this.getWritableDatabase().execSQL(deleteStament);
     }

     public void openDB(){
        this.getWritableDatabase();
         System.out.println("ingreso aqui");
     }

     public void closeDB(){
        this.close();
     }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
