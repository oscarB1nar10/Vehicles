package com.example.diannaliset.vehicles.activities.a.mainActivityVehicleList.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.diannaliset.vehicles.activities.a.mainActivityVehicleList.model.VehicleDataClass
import com.example.diannaliset.vehicles.activities.a.mainActivityVehicleList.repository.Repository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class VehiclesListViewModel( application: Application) : AndroidViewModel(application){
    //vars
    var repository: Repository? = null
    init {
        repository = Repository(application)
    }
    /**
     * This method allow us to retrieve all vehicles saved on Room from ViewModel
     * because this may take long time and could block the main thread so i  solve use coroutines to run operation in other thread
     * and return the value when all ok.(async)
     */
    suspend fun getVehicles(): LiveData<List<VehicleDataClass>> {

        val deferred = GlobalScope.async {
            repository!!.allVehicles()
        }
        return deferred.await()
    }

    suspend fun getVehicle(plaque: String): LiveData<VehicleDataClass> {
        val deferred = GlobalScope.async {
            repository!!.vehicles(plaque)
        }
        return deferred.await()
    }


    fun insertVehicle(vehicle: VehicleDataClass){
        repository!!.insertVehicle(vehicle)
    }

    fun updateVehicle(vehicle: VehicleDataClass){
        repository!!.updateVehicle(vehicle)
    }

    fun deleteVehicle(vehicle: VehicleDataClass){
        repository!!.deleteVehicle(vehicle)
    }

}