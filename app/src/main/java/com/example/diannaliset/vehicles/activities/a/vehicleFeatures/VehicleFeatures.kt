package com.example.diannaliset.vehicles.activities.a.vehicleFeatures

import android.app.Application
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.diannaliset.vehicles.R
import com.example.diannaliset.vehicles.activities.a.mainActivityVehicleList.MainActivityVehiclesList
import com.example.diannaliset.vehicles.activities.a.mainActivityVehicleList.model.VehicleDataClass
import com.example.diannaliset.vehicles.activities.a.mainActivityVehicleList.viewModel.VehiclesListViewModel

import com.example.diannaliset.vehicles.handlerDB.Vehicle
import com.example.diannaliset.vehicles.handlerDB.VehicleDB_Helper

class VehicleFeatures : AppCompatActivity() {
    private var linearLayout: LinearLayout? = null
    lateinit var canvas: Canvas
    lateinit var vehicle: Vehicle
    lateinit var model: VehiclesListViewModel
    var colorTire = Color.BLUE
    var colorCapo = Color.BLUE
    var colorDoor = Color.BLUE
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vehicle_features)

        // Create a ViewModel the first time the system calls an activity's onCreate() method.
        // Re-created activities receive the same MyViewModel instance created by the first activity.

        model = ViewModelProviders.of(this@VehicleFeatures).get(VehiclesListViewModel(application)::class.java)

        val spinnerTire = findViewById<Spinner>(R.id.spinner_tire)
        val spinnerCapo = findViewById<Spinner>(R.id.spinner_baul)
        val spinnerDoor = findViewById<Spinner>(R.id.spinner_door)
        vehicle = Vehicle()

        val word = arrayOf("Azul", "Verde", "Amarillo")
        spinnerTire.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, word)
        spinnerCapo.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, word)
        spinnerDoor.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, word)

        spinnerTire.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                val panel: Panel
                when (i) {
                    0 -> colorTire = Color.BLUE
                    1 -> colorTire = Color.GREEN
                    2 -> colorTire = Color.YELLOW
                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {

            }
        }
        spinnerCapo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                val panel: Panel
                when (i) {
                    0 -> colorCapo = Color.BLUE
                    1 -> colorCapo = Color.GREEN
                    2 -> colorCapo = Color.YELLOW
                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {

            }
        }
        spinnerDoor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                val panel: Panel
                when (i) {
                    0 -> colorDoor = Color.BLUE
                    1 -> colorDoor = Color.GREEN
                    2 -> colorDoor = Color.YELLOW
                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {

            }
        }


        linearLayout = findViewById(R.id.cavas_view)
        linearLayout!!.addView(Panel(application))


    }


    fun insertData(v: View) {
        vehicle.plaque = intent.extras!!.getString("plaque")
        vehicle.brand = intent.extras!!.getString("brand")
        vehicle.model = intent.extras!!.getString("model")
        vehicle.numOfDoors = intent.extras!!.getInt("num_of_doors")
        vehicle.typeOfVehicle = intent.extras!!.getString("type_of_vehicle")
        vehicle.tireColor = colorTire
        vehicle.capoColor = colorCapo
        vehicle.doorColor = colorDoor

        val vehicleDB_helper = VehicleDB_Helper(this, vehicle)
        vehicleDB_helper.openDB()
        vehicleDB_helper.dataRegister()
        val vehi = VehicleDataClass(vehicle.plaque, vehicle.brand, vehicle.model, vehicle.numOfDoors,
                                    vehicle.typeOfVehicle, vehicle.tireColor, vehicle.capoColor, vehicle.doorColor)
        model.insertVehicle(vehi)
        vehicleDB_helper.closeDB()

        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_LONG).show()
        val intent = Intent(this, MainActivityVehiclesList::class.java)
        startActivity(intent)


    }

    internal inner class Panel(application: Application) : View(application) {

        public override fun onDraw(canva: Canvas) {
            super.onDraw(canva)
            canvas = canva
            drawTire()
            drawCapoAndBaul()
            drawableDoor()
            this.invalidate()


        }

        fun drawTire() {

            val pincel = Paint()
            pincel.color = colorTire
            pincel.strokeWidth = 100f
            pincel.typeface = Typeface.MONOSPACE

            val pincel2 = Paint()
            pincel2.color = Color.BLACK
            pincel2.textSize = 20f

            val pincel3 = Paint()
            pincel3.setARGB(150, 0, 100, 70)
            pincel3.strokeWidth = 15f
            pincel3.strokeMiter = 10f


            canvas.drawText("color de llantas", 50f, 130f, pincel2)
            canvas.drawCircle(150f, 200f, 55f, pincel)
            canvas.drawCircle(150f, 200f, 30f, pincel3)

        }

        fun drawCapoAndBaul() {
            val pincel1 = Paint()
            pincel1.color = colorCapo
            pincel1.strokeWidth = 10f

            val pincel2 = Paint()
            pincel2.color = Color.BLACK
            pincel2.textSize = 20f

            canvas.drawText("color de Capó y Baúl", 50f, 350f, pincel2)
            val coordinates = floatArrayOf(100f, 400f, 100f, 420f, 100f, 420f, 250f, 420f, 250f, 420f, 250f, 360f, 250f, 360f, 100f, 400f)
            canvas.drawLines(coordinates, pincel1)

        }

        fun drawableDoor() {
            val pincel1 = Paint()
            pincel1.color = colorDoor
            pincel1.strokeWidth = 10f

            val pincel2 = Paint()
            pincel2.color = Color.BLACK
            pincel2.textSize = 20f

            canvas.drawText("color de Puerta", 50f, 570f, pincel2)
            val coordinates = floatArrayOf(100f, 590f, 100f, 700f, 100f, 700f, 250f, 700f, 250f, 700f, 250f, 580f, 250f, 580f, 120f, 580f, 120f, 580f, 100f, 590f)
            canvas.drawLines(coordinates, pincel1)

        }


    }


}
