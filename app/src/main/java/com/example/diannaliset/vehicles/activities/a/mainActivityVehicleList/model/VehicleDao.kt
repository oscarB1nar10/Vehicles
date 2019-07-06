package com.example.diannaliset.vehicles.activities.a.mainActivityVehicleList.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface VehicleDao {

    @Insert
    fun insertVehicle(vehicle: VehicleDataClass)

    @Query("SELECT * FROM VehicleDataClass")
    fun loadAllVehicles(): LiveData<List<VehicleDataClass>>

    @Query("SELECT * FROM VehicleDataClass WHERE plate = :plaque")
    fun foundVehicle(plaque: String): LiveData<VehicleDataClass>

    @Update
    fun updateVehicles(vehicle: VehicleDataClass)

    @Delete
    fun deleteVehicle(vehicle: VehicleDataClass)

}