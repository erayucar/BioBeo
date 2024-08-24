package com.erayucar.biobeo.core.network.di

import com.erayucar.biobeo.core.network.source.RestDataSource
import com.erayucar.biobeo.core.network.source.RestDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class SourceModule {


    @Binds
    @Singleton
    abstract fun bindRestDataSource(restDataSourceImpl: RestDataSourceImpl): RestDataSource
}