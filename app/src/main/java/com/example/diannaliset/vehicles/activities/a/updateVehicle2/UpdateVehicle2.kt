package com.example.diannaliset.vehicles.activities.a.updateVehicle2

import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.example.diannaliset.vehicles.R
import com.example.diannaliset.vehicles.activities.a.mainActivityVehicleList.MainActivityVehiclesList
import com.example.diannaliset.vehicles.activities.a.mainActivityVehicleList.model.VehicleDataClass
import com.example.diannaliset.vehicles.activities.a.mainActivityVehicleList.viewModel.VehiclesListViewModel
import com.example.diannaliset.vehicles.base.BaseActivity
import kotlinx.android.synthetic.main.activity_updatevehicle2.*
import kotlinx.coroutines.launch

class UpdateVehicle2 : BaseActivity(R.layout.activity_updatevehicle2) {

    //vars
    lateinit var vehiclesListViewModel: VehiclesListViewModel
    lateinit var vehicleDataClass: VehicleDataClass
    private var tireColor: Int = 0
    private var capoColor: Int = 0
    private var doorColor: Int = 0

    override fun initViews() {
        vehiclesListViewModel = ViewModelProviders.of(this@UpdateVehicle2).get(VehiclesListViewModel(application)::class.java)

        val plaqueValue = intent.getStringExtra("plaque")
        lifecycleScope.launch {
            vehiclesListViewModel.getVehicle(plaqueValue).observe(this@UpdateVehicle2, Observer<VehicleDataClass> { vehicle ->
                fillVehicleFields(vehicle)
            })
        }
    }

    /**
     * This method allows fill all vehicle fields into our layout
     */
    private fun fillVehicleFields(vehicle: VehicleDataClass){

        plaque_up.setText(vehicle.plate)
        brand_up.setText(vehicle.brand)
        model_up.setText(vehicle.model)
        num_of_doors_up.setText(vehicle.numOfDoors.toString())
        type_of_vehicle_up.setText(vehicle.typeOfVehicle)
        tireColor = vehicle.tireColor
        capoColor = vehicle.capoColor
        doorColor = vehicle.doorColor



    }

    /**
     * This function will be trigger when touch over update button
     */
     fun updateVehi(view: View){

        vehicleDataClass = VehicleDataClass(plaque_up.text.toString(), brand_up.text.toString(), model_up.text.toString(),
                num_of_doors_up.text.toString().toInt(), type_of_vehicle_up.text.toString(),
                tireColor, capoColor, doorColor)

        vehiclesListViewModel.updateVehicle(vehicleDataClass)

        val intent = Intent(this@UpdateVehicle2, MainActivityVehiclesList::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

        startActivity(intent)

    }

}