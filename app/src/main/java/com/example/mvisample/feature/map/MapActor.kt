package com.example.mvisample.feature.map

import android.util.Log
import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.PostProcessor
import com.example.mvisample.BottomState
import com.example.mvisample.data.PointsSource
import com.example.mvisample.feature.map.MapFeature.MapAction
import com.example.mvisample.feature.map.MapFeature.MapAction.LoadPoints
import com.example.mvisample.feature.map.MapFeature.MapAction.MapChanged
import com.example.mvisample.feature.map.MapFeature.MapEffect
import com.example.mvisample.feature.map.MapFeature.MapEffect.*
import com.example.mvisample.model.EMPTY_LOCATION
import com.example.mvisample.toObservable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject

class MapActor(
    private val pointsSource: PointsSource
) : Actor<MapFeature.MapState, MapAction, MapEffect> {

    private val cancelSubject = PublishSubject.create<Unit>()

    override fun invoke(
        state: MapFeature.MapState,
        action: MapAction
    ): Observable<MapEffect> {
        Log.d("ASca9vsdga9ubsnc", "sfa $action   $state")
        return when (action) {
            MapAction.ResetZoom -> ResetZoom.toObservable()

            is MapAction.LocationReceived ->
                LocationReceived(
                    action.location,
                    isShouldZoom = state.location == EMPTY_LOCATION
                ).toObservable()

            is LoadPoints -> {
                cancelSubject.onNext(Unit)
                return pointsSource.loadPoints(action.location)
                    .toObservable()
                    .takeUntil(cancelSubject)
                    .map { PointsLoaded(it) as MapEffect }
                    .startWith(
                        Observable.fromIterable(
                            listOf(
                                TitleChanged("Загрузка"),
                                ChangeBottomViewState(BottomState.EXPANDED)
                            )
                        )
                    )
                    .concatWith(TitleChanged("").toObservable())
                    .observeOn(AndroidSchedulers.mainThread())
            }

            is MapChanged -> MapMoving("Определяем местоположение").toObservable()
            is MapAction.ChangeBottomViewState -> ChangeBottomViewState(action.newState)
                .toObservable()
        }
    }
}

class MapPostProcessor() :
    PostProcessor<MapAction, MapEffect, MapFeature.MapState> {
    override fun invoke(
        action: MapAction,
        effect: MapEffect,
        state: MapFeature.MapState
    ): MapAction? {
        Log.d("Asc98ahsvc90ahsvjca", "Sfdafg $action  $effect  $state")
        if (state.shouldZoom) {
            return MapAction.ResetZoom
        }
        return null
    }
}

