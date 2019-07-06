package com.example.diannaliset.vehicles.activities.a.mainActivityVehicleList

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.diannaliset.vehicles.R
import com.example.diannaliset.vehicles.VehicleDriver
import com.example.diannaliset.vehicles.activities.a.mainActivityVehicleList.model.VehicleDataClass
import com.example.diannaliset.vehicles.activities.a.mainActivityVehicleList.viewModel.VehiclesListViewModel
import com.example.diannaliset.vehicles.adapters.RecyclerViewVehiclesListAdapter
import com.example.diannaliset.vehicles.base.BaseActivity
import com.example.diannaliset.vehicles.handlerDBR.SyncUpWhitRemoteDBR
import kotlinx.android.synthetic.main.activity_main_vehicles_list.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import kotlinx.coroutines.launch

class MainActivityVehiclesList : BaseActivity(R.layout.activity_main_vehicles_list) {
    //vars
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun initViews() {
        setUpToolbarElements()
        viewManager = LinearLayoutManager(this)

        // Create a ViewModel the first time the system calls an activity's onCreate() method.
        // Re-created activities receive the same MyViewModel instance created by the first activity.
        val model = ViewModelProviders.of(this@MainActivityVehiclesList).get(VehiclesListViewModel(application)::class.java)
        rv_vehicles_list.setHasFixedSize(true)
        rv_vehicles_list.layoutManager = viewManager
        //Vehicles retrieve from Room
        lifecycleScope.launch {
            model.getVehicles().observe(this@MainActivityVehiclesList, Observer<List<VehicleDataClass>> {vehicles ->
                viewAdapter = RecyclerViewVehiclesListAdapter(vehicles, application)
                rv_vehicles_list.adapter = viewAdapter

            })
        }
    }

    /**
     * This function allows setup the elements inside the toolbar to this view
     */
    fun setUpToolbarElements(){
        imb_back_button.visibility = View.GONE
        imb_back_home.visibility = View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        Toast.makeText(this, "Bienvenido...", Toast.LENGTH_LONG).show()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings1 -> {
                val intent = Intent(this, VehicleDriver::class.java)
                startActivity(intent)
            }
            R.id.sync_up_db_vehicles -> Thread(Runnable {
                val syncUpWhitRemoteDBR = SyncUpWhitRemoteDBR(baseContext)
                syncUpWhitRemoteDBR.syncUp()
            }).start()
        }//Toast.makeText(getBaseContext(),"Se han sincronizado los datos correctamente",Toast.LENGTH_LONG).show();

        return true
    }


}