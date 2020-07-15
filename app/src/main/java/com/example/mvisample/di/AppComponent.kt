package com.example.mvisample.di

import android.content.Context
import com.example.mvisample.LocationDataSource
import com.example.mvisample.LocationSource
import com.example.mvisample.ui.map.PointsFragment
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Component(modules = [AppModule::class, MapModule::class])
@Singleton
interface AppComponent {


    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context, @BindsInstance fragment: PointsFragment): AppComponent
    }

    fun inject(activity: PointsFragment)

//    fun splashComponent(): MapComponent
}

@Module
interface AppModule {

    @Binds
    @Singleton
    fun bindLocationSource(locationSourDataSource: LocationDataSource): LocationSource
}