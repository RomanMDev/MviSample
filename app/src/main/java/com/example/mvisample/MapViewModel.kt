package com.example.mvisample

import com.example.mvisample.model.Location

data class MapViewModel(
    val title: String,
    val location: Location,
    val shouldZoom: Boolean,
    val points: List<Location>
)