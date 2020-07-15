package com.example.mvisample.feature.map

import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ActorReducerFeature
import com.badoo.mvicore.feature.BaseFeature
import com.example.mvisample.LocationSource
import com.example.mvisample.data.PointsSource
import com.example.mvisample.model.EMPTY_LOCATION
import com.example.mvisample.model.Location
import javax.inject.Inject

class MapFeature @Inject constructor(
    locationSource: LocationSource,
    pointsSource: PointsSource
) : BaseFeature<MapFeature.MapWish, MapFeature.MapAction, MapFeature.MapEffect, MapFeature.MapState, MapFeature.MapNews>(
    initialState = MapState(),
    reducer = MapReducer(),
    actor = MapActor(pointsSource),
    bootstrapper = MapBootstrapper(locationSource),
    postProcessor = MapPostProcessor(),
    wishToAction = { wish ->
        when (wish) {
            is MapWish.LoadPoints -> MapAction.LoadPoints(wish.location)
            is MapWish.MapChanged -> MapAction.MapChanged(wish.isMoving)
        }
    }
) {
    sealed class MapWish {
        data class LoadPoints(val location: Location) : MapWish()
        data class MapChanged(val isMoving: Boolean) : MapWish()
    }

    sealed class MapEffect {
        data class LocationReceived(val location: Location, val isShouldZoom: Boolean) : MapEffect()
        data class PointsLoaded(val location: List<Location>) : MapEffect()

        data class TitleChanged(val title: String) : MapEffect()
        object LoadLocation : MapEffect()
        object ResetZoom : MapEffect()
    }

    sealed class MapAction {
        data class LoadPoints(val location: Location) : MapAction()
        data class MapChanged(val isMoving: Boolean) : MapAction()

        // TODO private action
        data class LocationReceived(val location: Location) : MapAction()
        object ResetZoom : MapAction()
    }

    data class MapState(
        val title: String = "",
        val location: Location = Location(0.0, 0.0),
        val shouldZoom: Boolean = false,
        val points: List<Location> = listOf()
    )

    class MapReducer : Reducer<MapState, MapEffect> {
        override fun invoke(state: MapState, effect: MapEffect): MapState {
            return when (effect) {
                is MapEffect.LocationReceived -> state.copy(
                    location = effect.location,
                    shouldZoom = effect.isShouldZoom
                )
                is MapEffect.PointsLoaded -> state.copy(points = effect.location)
                is MapEffect.TitleChanged -> state.copy(title = effect.title)
                is MapEffect.ResetZoom -> state.copy(shouldZoom = false)
                is MapEffect.LoadLocation -> state
            }
        }
    }

    sealed class MapNews
}