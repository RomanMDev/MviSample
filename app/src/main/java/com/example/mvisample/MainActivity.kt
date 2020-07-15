package com.example.mvisample

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.mvisample.di.DaggerAppComponent
import com.example.mvisample.ui.PointsFragment

class MainActivity : AppCompatActivity() {


//    @Inject
//    lateinit var feature: MapFeature

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ), 1
        )
        supportFragmentManager.beginTransaction()
            .replace(R.id.content, PointsFragment.newInstance())
            .commit()
    }
}
