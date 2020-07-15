package com.example.mvisample.di

import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.feature.ActorReducerFeature
import com.example.mvisample.*
import com.example.mvisample.data.PointsDataSource
import com.example.mvisample.data.PointsSource
import com.example.mvisample.feature.map.MapFeature
import com.example.mvisample.ui.PointsFragment
import com.example.mvisample.ui.bind.MapBinder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import javax.inject.Singleton

//@Subcomponent(modules = [MapModule::class])
//@ScreenScope
//interface MapComponent {
//
//    fun inject(activity: MainActivity)
//}

@Module
class MapModule {

//    @Provides
//    @Singleton
//    fun bindMapFeature(
//        mapFeature: MapFeature
//    ): MapFeature = mapFeature

    @Provides
    @Singleton
    fun provideMapBinder(
        view: PointsFragment,
        mapFeature: MapFeature
    ): MapBinder = MapBinder(view = view, feature = mapFeature)

    @Provides
    @Singleton
    fun providePointsSource(
        pointSource: PointsDataSource
    ): PointsSource = pointSource
}