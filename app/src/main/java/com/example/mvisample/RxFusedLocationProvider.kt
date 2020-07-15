package com.example.mvisample

import android.annotation.SuppressLint
import android.location.Location
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.disposables.Disposables

class RxFusedLocationProvider(
    private val client: FusedLocationProviderClient,
    private val request: LocationRequest
) : ObservableOnSubscribe<Location> {

    companion object {
        fun create(
            client: FusedLocationProviderClient,
            request: LocationRequest
        ): Observable<Location> =
            Observable.defer { Observable.create(RxFusedLocationProvider(client, request)) }
    }

    @SuppressLint("MissingPermission")
    override fun subscribe(emitter: ObservableEmitter<Location>) {
        Log.d("asca897gsc897aghf","asffa $emitter")
        with(client) {
            val callback = object : LocationCallback() {
                override fun onLocationResult(result: LocationResult) {
                    result.lastLocation?.let {
                        emitter.onNext(it)
                    }
                }
            }

            emitter.setDisposable(Disposables.fromRunnable {
                removeLocationUpdates(callback)
            })

            requestLocationUpdates(request, callback, null)
            flushLocations()
        }
    }
}
