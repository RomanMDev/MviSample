package com.example.mvisample

import android.content.Context
import com.example.mvisample.model.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

interface LocationSource {

//    fun getLocation(): Single<Location>

    fun getDefaultLocation(): Single<Location>

    fun observeLocationUpdates(): Observable<Location>
}

private const val UPDATE_INTERVAL = 20L
private const val FASTEST_INTERVAL = 10L
private val DEFAULT_LOCATION = Location(
    lat = 45.0,
    lon = 45.6
)

class LocationDataSource @Inject constructor(context: Context) : LocationSource {

    private val client: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    private val defaultRequest: LocationRequest = LocationRequest.create()
        .apply {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = UPDATE_INTERVAL
            fastestInterval = FASTEST_INTERVAL
        }

//    override fun getLocation(): Single<Location> {
//        TODO("Not yet implemented")
//    }

    override fun getDefaultLocation(): Single<Location> =
        Single.just(DEFAULT_LOCATION)


    override fun observeLocationUpdates(): Observable<Location> =
        RxFusedLocationProvider.create(client, defaultRequest)
            .map { Location(it.latitude, it.longitude) }
}
