package com.example.diannaliset.vehicles.activities.a.showVehicle

import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.example.diannaliset.vehicles.*
import com.example.diannaliset.vehicles.activities.a.mainActivityVehicleList.model.VehicleDataClass
import com.example.diannaliset.vehicles.activities.a.mainActivityVehicleList.viewModel.VehiclesListViewModel
import com.example.diannaliset.vehicles.base.BaseActivity
import kotlinx.android.synthetic.main.show_vehicle.*
import kotlinx.coroutines.launch

class ShowVehicle2 : BaseActivity(R.layout.activity_showvehicle2) {

    //vars
    private var tireColor: Int = 0
    private var capoColor: Int = 0
    private var doorColor: Int = 0

    override fun initViews() {

        val vModel = ViewModelProviders.of(this@ShowVehicle2).get(VehiclesListViewModel(application)::class.java)
        val plaqueValue = intent.getStringExtra("plaque")
        lifecycleScope.launch {
            vModel.getVehicle(plaqueValue).observe(this@ShowVehicle2, Observer<VehicleDataClass> { vehicle ->
                fillVehicleFields(vehicle)
            })
        }
    }


    /**
     * This method allows fill all vehicle fields into our layout
     */
    private fun fillVehicleFields(vehicle: VehicleDataClass){
        plaque.text = vehicle.plate
        brand.text = vehicle.brand
        model.text = vehicle.model
        num_of_doors.text = vehicle.numOfDoors.toString()
        type_of_vehicle.text = vehicle.typeOfVehicle
        tireColor = vehicle.tireColor
        capoColor = vehicle.capoColor
        doorColor = vehicle.doorColor
    }



    fun updateVehicle(v: View) {
        val intent = Intent(this, UpdateVehicle::class.java)

        intent.putExtra("plaque", plaque.text.toString())
        intent.putExtra("brand", brand.text.toString())
        intent.putExtra("model", model.text.toString())
        intent.putExtra("num_of_doors", num_of_doors.text.toString())
        intent.putExtra("type_of_vehicle", type_of_vehicle.text.toString())

        startActivity(intent)

    }

    fun additionalFeatures(v: View) {

        val intent = Intent(this, ShowVehicleFeatures::class.java)

        intent.putExtra("tireColor", tireColor)
        intent.putExtra("capoColor", capoColor)
        intent.putExtra("doorColor", doorColor)

        startActivity(intent)


    }

    fun deleteVehicle(v: View) {

        val deleteVehicle = DeleteVehicle(plaque.text.toString(), this)
        deleteVehicle.deleteV()

    }

    fun returToHome(v: View) {
        val intent = Intent(applicationContext, VehiclesList::class.java)
        startActivity(intent)
    }
}