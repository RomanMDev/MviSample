package com.example.mvisample.data

import com.example.mvisample.model.Location
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.random.Random


interface PointsSource {

    fun loadPoints(location: Location): Single<List<Location>>
}

private const val COORDINATES = 0.4

class PointsDataSource @Inject constructor() : PointsSource {

    override fun loadPoints(location: Location): Single<List<Location>> =
        Single.timer(2, TimeUnit.SECONDS)
            .flatMap { Single.just(createPoints(location)) }

    private fun createPoints(location: Location): List<Location> =
        List(10) {
            Location(
                lat = location.lat + Random.nextDouble(-COORDINATES, COORDINATES),
                lon = location.lon + Random.nextDouble(-COORDINATES, COORDINATES)
            )
        }
}
