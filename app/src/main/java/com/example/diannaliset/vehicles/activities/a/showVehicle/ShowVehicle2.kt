package com.example.diannaliset.vehicles.activities.a.showVehicle

import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.example.diannaliset.vehicles.R
import com.example.diannaliset.vehicles.activities.a.showVehicleFeatures.ShowVehicleFeatures
import com.example.diannaliset.vehicles.activities.a.mainActivityVehicleList.MainActivityVehiclesList
import com.example.diannaliset.vehicles.activities.a.mainActivityVehicleList.model.VehicleDataClass
import com.example.diannaliset.vehicles.activities.a.mainActivityVehicleList.viewModel.VehiclesListViewModel
import com.example.diannaliset.vehicles.activities.a.updateVehicle2.UpdateVehicle2
import com.example.diannaliset.vehicles.base.BaseActivity
import kotlinx.android.synthetic.main.activity_showvehicle2.*
import kotlinx.coroutines.launch

class ShowVehicle2 : BaseActivity(R.layout.activity_showvehicle2) {

    //vars
    private var tireColor: Int = 0
    private var capoColor: Int = 0
    private var doorColor: Int = 0
    private lateinit var plaqueValue: String
    private lateinit var vehiclesListViewModel: VehiclesListViewModel
    private lateinit var vehicleDataClass: VehicleDataClass

    override fun initViews() {

        vehiclesListViewModel = ViewModelProviders.of(this@ShowVehicle2).get(VehiclesListViewModel(application)::class.java)
        plaqueValue = intent.getStringExtra("plaque")
        lifecycleScope.launch {
            vehiclesListViewModel.getVehicle(plaqueValue).observe(this@ShowVehicle2, Observer<VehicleDataClass> { vehicle ->
                fillVehicleFields(vehicle)
            })
        }
    }


    /**
     * This method allows fill all vehicle fields into our layout
     */
    private fun fillVehicleFields(vehicle: VehicleDataClass?){
        if(vehicle != null){

            plaque_sh.text = vehicle.plate
            brand_sh.text = vehicle.brand
            model_sh.text = vehicle.model
            num_of_doors_sh.text = vehicle.numOfDoors.toString()
            type_of_vehicle_sh.text = vehicle.typeOfVehicle
            tireColor = vehicle.tireColor
            capoColor = vehicle.capoColor
            doorColor = vehicle.doorColor

            vehicleDataClass = vehicle
        }

    }



    fun updateVehicle(v: View) {
        val intent = Intent(this, UpdateVehicle2::class.java)
                .putExtra("plaque",plaqueValue)
        /*
        intent.putExtra("plaque", plaque.text.toString())
        intent.putExtra("brand", brand.text.toString())
        intent.putExtra("model", model.text.toString())
        intent.putExtra("num_of_doors", num_of_doors.text.toString())
        intent.putExtra("type_of_vehicle", type_of_vehicle.text.toString())
        */

        startActivity(intent)

    }

    fun additionalFeatures(v: View) {

        val intent = Intent(this, ShowVehicleFeatures::class.java)

        intent.putExtra("tireColor", tireColor)
        intent.putExtra("capoColor", capoColor)
        intent.putExtra("doorColor", doorColor)

        startActivity(intent)


    }

    /**
     * This method allows delete a specific vehicle
     */
    fun deleteVehicle(v: View) {

        vehiclesListViewModel.deleteVehicle(vehicleDataClass)
        //val deleteVehicle = DeleteVehicle(plaque.text.toString(), this)
        //deleteVehicle.deleteV()
        val intent = Intent(this@ShowVehicle2, MainActivityVehiclesList::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    /*fun returToHome(v: View) {
        val intent = Intent(applicationContext, VehiclesList::class.java)
        startActivity(intent)
    }*/
}