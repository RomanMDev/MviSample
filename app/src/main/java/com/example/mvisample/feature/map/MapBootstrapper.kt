package com.example.mvisample.feature.map

import com.badoo.mvicore.element.Bootstrapper
import com.example.mvisample.LocationSource
import io.reactivex.Observable

class MapBootstrapper(
    private val locationSource: LocationSource
) : Bootstrapper<MapFeature.MapAction> {

    override fun invoke(): Observable<MapFeature.MapAction> {
        return locationSource.observeLocationUpdates()
            .map { MapFeature.MapAction.LocationReceived(it) }
    }
}
