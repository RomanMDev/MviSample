package com.example.mvisample.feature.points

import com.badoo.mvicore.element.Actor
import io.reactivex.Observable

class PointsActor : Actor<PointsFeature.State, PointsFeature.Wish, PointsFeature.Effect> {

    override fun invoke(
        state: PointsFeature.State,
        action: PointsFeature.Wish
    ): Observable<out PointsFeature.Effect> {

    }
}