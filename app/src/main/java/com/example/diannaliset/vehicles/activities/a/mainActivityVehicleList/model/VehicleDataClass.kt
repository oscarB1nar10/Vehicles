package com.example.diannaliset.vehicles.activities.a.mainActivityVehicleList.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VehicleDataClass(

        @PrimaryKey val plate: String,
        val brand: String,
        val model: String,
        val numOfDoors: Int,
        val typeOfVehicle: String,
        val tireColor: Int,
        val capoColor: Int,
        val doorColor: Int
)