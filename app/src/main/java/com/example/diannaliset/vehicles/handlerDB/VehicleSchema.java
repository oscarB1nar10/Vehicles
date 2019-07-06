package com.example.diannaliset.vehicles.handlerDB;

/*
    Schema about vehicles table
 */

import android.provider.BaseColumns;

public class VehicleSchema {

    public static abstract class VehicleColums implements BaseColumns {
        public static final String TABLE_NAME ="vehicles2";

        public static final String PLAQUE = "plaque";
        public static final String BRAND = "brand";
        public static final String MODEL = "model";
        public static final String NUM_OF_DOORS = "numOfDoors";
        public static final String TYPE_OF_VEHICLE = "typeOfVehicle";
        public static final String TIRE_COLOR = "tireColor";
        public static final String CAPO_COLOR = "capoColor";
        public static final String DOOR_COLOR = "doorColor";
    }

}
