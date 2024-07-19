package com.example.trailapp

import android.content.Context
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trailapp.databinding.ActivityDetailsBinding

class DActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var sensorManager: SensorManager
    private lateinit var sensor: Sensor
    private var sensorEventListener: SensorEventListener? = null
    private var initialStepCount = 2000
    val TAG = "sendorActivity"
    private var stepOffset = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textViewWikipediaLink.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Amber_Fort"))
            startActivity(intent)
        }

        binding.buttonGoToQuiz.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }

        val places = listOf("Amber Fort 1", "Amber Fort 2", "Amber Fort 3")
      //  val adapter = PlaceAdapter(places)
        binding.recyclerViewPopularPlaces.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
     //   binding.recyclerViewPopularPlaces.adapter = adapter

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
            ?: throw IllegalStateException("Step counter sensor not available on this device")

        checkPermission()
    }
    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACTIVITY_RECOGNITION)
            != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted, request it
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACTIVITY_RECOGNITION),
                101)
        } else {
            // Permission has already been granted, start sensor listener
            startSensorListener()
        }
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            101 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted, start sensor listener
                    startSensorListener()
                } else {
                    // Permission denied, handle accordingly
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }
    private fun startSensorListener() {
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI)
        Log.d(TAG, "Sensor listener registered")
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            if (it.sensor.type == Sensor.TYPE_STEP_COUNTER) {
                val stepsSinceBoot = it.values[0].toLong()
                val stepsSinceReset = stepsSinceBoot + initialStepCount
                Log.d(TAG, "Steps since boot: $stepsSinceBoot, Steps since reset: $stepsSinceReset")

                // Update UI with the computed steps since reset
                runOnUiThread {
                    binding.stepCountTxt.text = "$stepsSinceReset Steps"
                }
            }
        }
    }
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        Log.d(TAG, "Accuracy changed to: $accuracy")
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::sensorManager.isInitialized) {
            sensorManager.unregisterListener(this)
        }
    }
}