package com.example.getlocation

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

private var locationManager : LocationManager? = null

class MainActivity : AppCompatActivity() {

    var long: Double = 0.0
    var lat: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_get_location.setOnClickListener {
            getLocation()
        }

    }

    fun getLocation(){
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
        } else {

            val lm = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            var location: Location? = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (location == null) location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            lat = location!!.latitude
            long = location!!.longitude

            Log.d("longtitude", lat.toString()+","+long.toString())
        }
    }
}
