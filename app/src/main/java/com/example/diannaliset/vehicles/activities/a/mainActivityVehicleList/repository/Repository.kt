package com.example.diannaliset.vehicles.activities.a.mainActivityVehicleList.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.diannaliset.vehicles.activities.a.mainActivityVehicleList.model.AppDatabase
import com.example.diannaliset.vehicles.activities.a.mainActivityVehicleList.model.VehicleDao
import com.example.diannaliset.vehicles.activities.a.mainActivityVehicleList.model.VehicleDataClass
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Repository(application: Application){
    //vars
    private val appDB: AppDatabase?
    private val vehicleDao: VehicleDao?
    //initializer block
    init {
        appDB = AppDatabase.getInstance(application)
        // AppDatabase.getInstance(application)//Data base init
        vehicleDao = appDB.vehicleDao()
        //GlobalScope.launch {
        allVehicles()
        //}
    }

    /**
     * This method return all vehicles saved at Room
     */
    fun allVehicles(): LiveData<List<VehicleDataClass>> {
        return   vehicleDao!!.loadAllVehicles()
    }

    /**
     * This method allows retrieve a specific vehicle
     */
    fun vehicles(plaque: String): LiveData<VehicleDataClass> {
        return   vehicleDao!!.foundVehicle(plaque)
    }

    /**
     *This method allows save a specific vehicle
     */
    fun insertVehicle(vehicle: VehicleDataClass){
        GlobalScope.launch {
            vehicleDao!!.insertVehicle(vehicle)
        }
    }

    /**
     *This method allows update a specific vehicle
     */
    fun updateVehicle(vehicle: VehicleDataClass){
        GlobalScope.launch {
            vehicleDao!!.updateVehicles(vehicle)
        }
    }

    /**
     *This method allows delete a specific vehicle
     */
    fun deleteVehicle(vehicle: VehicleDataClass){
        GlobalScope.launch {
            vehicleDao!!.deleteVehicle(vehicle)
        }
    }

}