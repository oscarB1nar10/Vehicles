package com.example.diannaliset.vehicles.base

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.diannaliset.vehicles.R
import com.example.diannaliset.vehicles.activities.a.mainActivityVehicleList.MainActivityVehiclesList
import kotlinx.android.synthetic.main.content_base.view.*

abstract class BaseActivity(private val layout: Int ) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        setSupportActionBar(findViewById(R.id.tb_base))
        initViews()
    }

    abstract fun initViews()

    override fun setContentView(layoutResID: Int) {
        var coordinatorLayout: CoordinatorLayout = layoutInflater.inflate(R.layout.content_base, null) as CoordinatorLayout
        var activityContainer: FrameLayout = coordinatorLayout.layout_container

        layoutInflater.inflate(layoutResID, activityContainer, true)
        super.setContentView(coordinatorLayout)
    }

    /**
     * This method allows return to home when we are in other activity
     */
    fun comeBackToHome(view: View){
        val intent = Intent(application, MainActivityVehiclesList::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    /**
     * This method allow return the previous view
     */
    fun back(view: View){
        onBackPressed()
    }


}
