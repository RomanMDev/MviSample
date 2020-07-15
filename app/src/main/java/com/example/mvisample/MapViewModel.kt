package com.example.mvisample

import com.example.mvisample.model.Location
import com.example.mvisample.model.Point

data class MapViewModel(
    val title: String,
    val location: Location,
    val shouldZoom: Boolean,
    val points: List<Point>,
    val viewState: BottomState
)

enum class BottomState {
    HIDDEN,
    EXPANDED,
}
