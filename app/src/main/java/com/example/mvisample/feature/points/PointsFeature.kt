package com.example.mvisample.feature.points

import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.BaseFeature
import com.badoo.mvicore.feature.ReducerFeature

class PointsFeature :
    BaseFeature<PointsFeature.Wish, PointsFeature.Wish, PointsFeature.Effect, PointsFeature.State, Nothing>(
        initialState = State(),
        reducer = PointsReducer(),
        actor = PointsActor(),
        wishToAction = { wish -> wish },
        postProcessor = null,
        bootstrapper = null

    ) {

    class State

    sealed class Wish {
        object Idle : Wish()
    }

    sealed class Action {
        object Idle : Action()
    }

    sealed class Effect {
        object Idle : Effect()
    }

    class PointsReducer() : Reducer<State, Effect> {
        override fun invoke(state: State, effect: Effect): State {
            return when (effect) {
                Effect.Idle -> state
            }
        }
    }
}