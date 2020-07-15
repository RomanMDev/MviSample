package com.example.mvisample.ui.bind

import com.badoo.mvicore.android.AndroidBindings
import com.badoo.mvicore.binder.using
import com.badoo.mvicore.connector.Connector
import com.badoo.mvicore.extension.mapNotNull
import com.example.mvisample.feature.map.MapFeature
import com.example.mvisample.ui.PointsFragment
import com.example.mvisample.ui.event.MapUiEvent
import com.example.mvisample.ui.event.MapUiEventToWishTransformer
import com.example.mvisample.ui.event.MapViewModelTransformer
import io.reactivex.Observable
import io.reactivex.ObservableSource

class MapBinder constructor(
    view: PointsFragment,
    private val feature: MapFeature
) : AndroidBindings<PointsFragment>(view) {


    override fun setup(view: PointsFragment) {
        with(binder) {
            bind(feature to view using MapViewModelTransformer)
            bind(view to feature using MapUiEventToWishTransformer)
        }

//        binder.bind(view to feature using object : Connector<MapUiEvent, MapFeature.MapWish> {
//            override fun invoke(actions: ObservableSource<out MapUiEvent>): ObservableSource<MapFeature.MapWish> {
//                return Observable.wrap(actions)
//                    .mapNotNull { action ->
//                        if (action is MapUiEvent.MapStopMoving) {
//                            action
//                        } else null
//                    }
//                    .switchMap { Observable.just(MapFeature.MapWish.LoadPoints(it.location)) }
//            }
//        })
    }
}
