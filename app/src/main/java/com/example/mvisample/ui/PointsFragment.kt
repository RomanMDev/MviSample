package com.example.mvisample.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.mvisample.MapViewModel
import com.example.mvisample.R
import com.example.mvisample.di.DaggerAppComponent
import com.example.mvisample.model.Location
import com.example.mvisample.ui.bind.MapBinder
import com.example.mvisample.ui.event.MapUiEvent
import com.example.mvisample.ui.map.BaseMapFragment
import com.example.mvisample.ui.map.addMarker
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.fragment_point.*
import kotlinx.android.synthetic.main.layout_bottom.*
import javax.inject.Inject

class PointsFragment : BaseMapFragment<MapUiEvent>(R.layout.fragment_point),
    Consumer<MapViewModel> {

    companion object {
        fun newInstance(): PointsFragment = PointsFragment()
    }

    @Inject
    lateinit var binder: MapBinder

    private var markers: MutableList<Marker> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerAppComponent.factory()
            .create(requireContext().applicationContext, this)
            .inject(this)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binder.setup(this)

        mapView.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        super.onMapReady(map)

        map.setOnCameraIdleListener {
            val location = map.cameraPosition.target.run {
                Location(latitude, longitude)
            }
             Log.d("asdv9agsc897agbsd","asfag  idle $location")
            onNext(MapUiEvent.MapStopMoving(location))
        }

        map.setOnCameraMoveStartedListener {
            if (it == GoogleMap.OnCameraMoveStartedListener.REASON_GESTURE) {
                onNext(MapUiEvent.MapStartMoving)
            }
        }
    }

    override fun accept(model: MapViewModel) {
        Log.d("svc9auhv9absf", "fag $model")

        googleMap?.run {
            if (model.shouldZoom) {
                animateCamera(
                    CameraUpdateFactory.newLatLngZoom(
                        LatLng(
                            model.location.lat,
                            model.location.lon
                        ),
                        DEFAULT_ZOOM_LEVEL
                    )
                )
            }
            processMarkers(this, model)
        }

        titlePointsTextView.text = model.title
    }

    private fun processMarkers(googleMap: GoogleMap, model: MapViewModel) {
        markers.filter { marker ->
            model.points.none { it == marker.tag }
        }.forEach {
            markers.remove(it)
            it.remove()
        }

        model.points.forEach {
            markers.find {  }
        }

        model.points.forEach { point ->
            markers.find {
                it.tag == it
            }?.let {
                it.position = LatLng(point.lat, point.lon)
            } ?: googleMap.run {
                val marker =
                    addMarker(requireContext(), point, R.drawable.ic_baseline_person_pin_24, point)
                markers.add(marker)
            }
        }
    }
}

const val DEFAULT_ZOOM_LEVEL = 8.28f
