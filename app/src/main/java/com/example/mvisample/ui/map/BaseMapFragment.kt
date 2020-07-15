package com.example.mvisample.ui.map

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import com.example.mvisample.ObservableSourceFragment
import com.example.mvisample.model.Location
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.fragment_point.*


open class BaseMapFragment<T>(@LayoutRes id: Int) : ObservableSourceFragment<T>(id),
    OnMapReadyCallback {

    protected var googleMap: GoogleMap? = null

    @CallSuper
    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView.onCreate(savedInstanceState)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    @CallSuper
    override fun onPause() {
        mapView.onPause()
        super.onPause()
    }

    @CallSuper
    override fun onStop() {
        mapView.onStop()
        super.onStop()
    }

    @CallSuper
    override fun onDestroyView() {
        mapView.onDestroy()
        super.onDestroyView()
    }

    @CallSuper
    override fun onLowMemory() {
        mapView.onLowMemory()
        super.onLowMemory()
    }
}

fun GoogleMap.addMarker(
    context: Context,
    location: Location,
    @DrawableRes resId: Int,
    tag: Any? = null
): Marker {
    return addMarker(MarkerOptions().apply {
        position(LatLng(location.lat, location.lon))
        icon(bitmapDescriptorFromVector(context, resId))
    }).also { it.tag = tag }
}

fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
    val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)
    vectorDrawable!!.setBounds(
        0,
        0,
        vectorDrawable.intrinsicWidth,
        vectorDrawable.intrinsicHeight
    )
    val bitmap = Bitmap.createBitmap(
        vectorDrawable.intrinsicWidth,
        vectorDrawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    vectorDrawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bitmap)
}
