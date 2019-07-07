package com.example.diannaliset.vehicles.adapters

import android.app.Application
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.diannaliset.vehicles.R
import com.example.diannaliset.vehicles.activities.a.mainActivityVehicleList.model.VehicleDataClass
import com.example.diannaliset.vehicles.activities.a.showVehicle.ShowVehicle2
import kotlinx.android.synthetic.main.vehicle_item_recylcer.view.*

class RecyclerViewVehiclesListAdapter(private val vehicles: List<VehicleDataClass>, private val application: Application):
        RecyclerView.Adapter<RecyclerViewVehiclesListAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vehicle_item_recylcer, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return vehicles.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder?.vehiclePlaque?.text = "plaque: ${vehicles[position].plate}"
        holder?.itemContainerList.setOnClickListener {
            val intent= Intent(application, ShowVehicle2::class.java)
                    .putExtra("plaque", vehicles[position].plate)

            application.startActivity(intent)
        }
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val vehiclePlaque = view.txv_plaque!!
        val itemContainerList  = view.cv_item_container_vehicles!!
    }

}