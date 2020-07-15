package com.example.mvisample.ui.bind

import androidx.lifecycle.LifecycleOwner
import com.badoo.mvicore.android.lifecycle.StartStopBinderLifecycle
import com.badoo.mvicore.binder.Binder
import com.badoo.mvicore.binder.using
import com.example.mvisample.feature.map.MapFeature
import com.example.mvisample.ui.map.PointsFragment
import com.example.mvisample.ui.event.MapUiEventToWishTransformer
import com.example.mvisample.ui.event.MapViewModelTransformer

class MapBinder constructor(
    view: PointsFragment,
    private val feature: MapFeature
) : StartStopBinding<PointsFragment>(view) {


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


abstract class StartStopBinding<T : Any>(
    lifecycleOwner: LifecycleOwner
) {
    protected val binder = Binder(
        lifecycle = StartStopBinderLifecycle(
            androidLifecycle = lifecycleOwner.lifecycle
        )
    )

    abstract fun setup(view: T)
}
