package com.erayucar.biobeo.core.data.di

import com.erayucar.biobeo.core.data.RouteRepository
import com.erayucar.biobeo.core.data.RouteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCharacterRepository(routeRepository: RouteRepositoryImpl): RouteRepository
}