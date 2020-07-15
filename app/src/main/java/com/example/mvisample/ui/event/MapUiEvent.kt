package com.example.mvisample.ui.event

import com.example.mvisample.MapViewModel
import com.example.mvisample.feature.map.MapFeature
import com.example.mvisample.model.Location

sealed class MapUiEvent() {
    object MapStartMoving : MapUiEvent()
    data class MapStopMoving(val location: Location) : MapUiEvent()
}

object MapUiEventToWishTransformer : (MapUiEvent) -> MapFeature.MapWish? {
    override fun invoke(event: MapUiEvent): MapFeature.MapWish? =
        when (event) {
            is MapUiEvent.MapStartMoving -> MapFeature.MapWish.MapChanged(true)
            is MapUiEvent.MapStopMoving -> MapFeature.MapWish.LoadPoints(event.location)
        }
}

object MapViewModelTransformer : (MapFeature.MapState) -> MapViewModel {

    override fun invoke(state: MapFeature.MapState): MapViewModel =
        state.run {
            MapViewModel(
                location = location,
                shouldZoom = shouldZoom,
                points = points,
                title = title
            )
        }
}