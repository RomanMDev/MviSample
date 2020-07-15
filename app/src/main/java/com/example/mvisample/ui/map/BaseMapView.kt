package com.example.mvisample.ui.map

import android.content.Context
import android.util.AttributeSet
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView

class BaseMapView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MapView(context) {

    protected var googleMap: GoogleMap? = null


}
