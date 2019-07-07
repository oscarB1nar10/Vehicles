package com.example.diannaliset.vehicles.activities.a.registerVehicle

import android.content.Intent
import android.view.View
import com.example.diannaliset.vehicles.R
import com.example.diannaliset.vehicles.activities.a.vehicleFeatures.VehicleFeatures
import com.example.diannaliset.vehicles.base.BaseActivity
import kotlinx.android.synthetic.main.activity_register_vehicle.*

class RegisterVehicle : BaseActivity(R.layout.activity_register_vehicle){


    override fun initViews() {

    }

    /**
     * This method allows setup additional vehicle features
     */
    fun addFeatures(v: View) {
        val intent = Intent(this, VehicleFeatures::class.java)

        intent.putExtra("plaque", plaque_r.text.toString())
        intent.putExtra("brand", brand_r.text.toString())
        intent.putExtra("model", model_r.text.toString())
        intent.putExtra("num_of_doors", Integer.parseInt(num_of_doors_r.text.toString()))
        intent.putExtra("type_of_vehicle", type_of_vehicle_r.text.toString())

        startActivity(intent)
    }

}