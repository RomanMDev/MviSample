package com.example.mvisample.feature.map

import android.util.Log
import com.badoo.mvicore.element.Bootstrapper
import com.example.mvisample.LocationSource
import io.reactivex.Observable

class MapBootstrapper(
    private val locationSource: LocationSource
) : Bootstrapper<MapFeature.MapAction> {

    override fun invoke(): Observable<MapFeature.MapAction> {
        return locationSource.observeLocationUpdates()
            .map {
                Log.d("fa89sfub9asfd0a8sf","sfas it")
                MapFeature.MapAction.LocationReceived(it) }
    }
}
